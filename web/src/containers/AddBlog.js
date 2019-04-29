import React, {Component} from 'react';
import {FormGroup, FormControl, FormLabel, Alert} from 'react-bootstrap';
import {EditorState, convertToRaw} from 'draft-js';
import ApiService from '../services/ApiService'
import LoaderButton from '../components/LoaderButton';
import './AddBlog.css';
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import 'react-draft-wysiwyg/dist/react-draft-wysiwyg.css';
import draftToHtml from 'draftjs-to-html';
import {Editor} from 'react-draft-wysiwyg';

export default class AddBlog extends Component {
  constructor(props) {
    super(props);

    this.state = {
      isLoading: true,
      isSubmitting: false,
      hobby: null,
      blog: {
        title: "",
        content: EditorState.createEmpty(),
      },
      hasErrors: false,
      errorMessage: ''
    };
  }

  onEditorStateChange = (editorState) => {
    this.setState({blog: {...this.state.blog, content: editorState}});
  };

  validateForm() {
    return this.state.blog.title !== '' && this.getHtmlFromRawContent() !== '';
  }

  getHobby = () => {
    ApiService.getHobbyById(this.props.match.params.id)
      .then(function (response) {
        this.setState({
          hobby: response.hobby,
          isLoading: false
        })
      }.bind(this))
      .catch(function (error) {
        console.log(error.statusText);

        this.setState({
          hasErrors: true,
          isLoading: false,
          errorMessage: 'There has been an error processing your request.',
        });
      }.bind(this));
  };

  getHtmlFromRawContent() {
    return draftToHtml(convertToRaw(this.state.blog.content.getCurrentContent()));
  }

  componentDidMount() {
    this.getHobby();
  }

  handleChange = event => {
    this.setState({blog: {...this.state.blog, [event.target.id]: event.target.value}});
  };

  handleSubmit = async event => {
    event.preventDefault();

    let rawContent = this.getHtmlFromRawContent();

    this.setState({isSubmitting: true});

    ApiService.createBlog(this.state.blog.title, rawContent, this.state.hobby.hobbyId, this.props.loggedInUser.id)
      .then(function (response) {
        console.log(response);
        if (response) {
          this.props.history.push(`/hobby/${this.state.hobby.hobbyId}`);
        } else {
          this.setState({
            hasErrors: true,
            isLoading: false,
            errorMessage: 'There has been an error processing your request.',
          });
        }
      }.bind(this))
      .catch(function (error) {
        console.log(error.statusText);

        this.setState({
          hasErrors: true,
          isLoading: false,
          errorMessage: 'There has been an error processing your request.',
        });
      }.bind(this));
  };

  renderForm() {
    return (
      <div>
        <h2 className={'text-center mb-4'}>Add Your Blog to {this.state.hobby.name} Community!</h2>
        <form onSubmit={this.handleSubmit}>
          <FormGroup controlId="title">
            <FormLabel>Title</FormLabel>
            <FormControl
              autoFocus
              type="text"
              value={this.state.blog.title}
              onChange={this.handleChange}
            />
          </FormGroup>

          <Editor
            editorState={this.state.blog.content}
            wrapperClassName="blog-wrapper"
            editorClassName="blog-editor"
            onEditorStateChange={this.onEditorStateChange}
          />

          <LoaderButton
            block
            disabled={!this.validateForm()}
            type="submit"
            isLoading={this.state.isSubmitting}
            text="Create Blog"
            loadingText="Creating Blog..."
          />
        </form>
      </div>
    );
  }

  render() {
    if (this.state.hasErrors) {
      var alertMessage = <Alert variant={'danger'} onClose={this.onAlertClose} className={'mb-3'}
                                dismissible>{this.state.errorMessage}</Alert>
    }

    return (
      <div className="AddBlog">
        {this.state.isLoading ? (
          <FontAwesomeIcon icon="sync" className="fa-spin spinning"/>) : (
          this.state.hasErrors ? (alertMessage) :
            this.renderForm()
        )}
      </div>
    );
  }
}
