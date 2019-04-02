import React, {Component} from 'react';
import {FormGroup, FormControl, FormLabel, Alert, Form} from 'react-bootstrap';
import ApiService from '../services/ApiService'
import FileUpload from '../components/FileUpload';
import LoaderButton from '../components/LoaderButton';
import './AddHobby.css';

export default class AddHobby extends Component {
  constructor(props) {
    super(props);

    this.state = {
      isLoading: false,
      name: '',
      description: '',
      categories: [
        {id: 0, name: 'Select Hobby Category'},
        {id: 1, name: 'Sports'},
        {id: 2, name: 'Music'},
        {id: 3, name: 'Art'},
        {id: 4, name: 'Adventure'},
        {id: 5, name: 'Other'},
      ],
      picture: '',
      hasErrors: false,
      errorMessage: ''
    };
  }

  validateForm() {
    return (
      true
    );
  }

  handleChange = event => {
    this.setState({
      [event.target.id]: event.target.value
    });
  };

  handleSubmit = async event => {
    event.preventDefault();

    this.setState({isLoading: true});

    ApiService.registerUser(this.state.username, this.state.password, this.state.email, this.state.firstname, this.state.lastname)
      .then(function (response) {
        if (response) {
          this.props.userHasAuthenticated(true);
          this.props.history.push("/");
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
    if (this.state.hasErrors) {
      var alertMessage = <Alert variant={'danger'} onClose={this.onAlertClose} className={'mb-3'}
                                dismissible>{this.state.errorMessage}</Alert>
    }

    return (
      <div>
        <h2 className={'text-center'}>Create Your Hobby!</h2>
        {alertMessage}
        <form onSubmit={this.handleSubmit}>
          <FormGroup controlId="name">
            <FormLabel>Hobby Name</FormLabel>
            <FormControl
              autoFocus
              type="text"
              value={this.state.name}
              onChange={this.handleChange}
            />
          </FormGroup>
          <FormGroup controlId="description">
            <FormLabel>Hobby Description</FormLabel>
            <FormControl
              autoFocus
              as={'textarea'}
              type="text"
              value={this.state.description}
              onChange={this.handleChange}
              className={'category-select'}
            />
          </FormGroup>
          <FormGroup controlId="category">
            <FormLabel>Hobby Category</FormLabel>
            <Form.Control as="select">
              {
                this.state.categories.map(category => {
                  return <option value={category.id} key={category.id}>{category.name}</option>
                })
              }
            </Form.Control>
          </FormGroup>

          <FormLabel>Hobby Photo</FormLabel>
          <FileUpload/>
          <LoaderButton
            block
            disabled={!this.validateForm()}
            type="submit"
            isLoading={this.state.isLoading}
            text="Create Hobby"
            loadingText="Signing up…"
          />
        </form>
      </div>
    );
  }

  render() {
    return (
      <div className="AddHobby">
        {this.renderForm()}
      </div>
    );
  }
}
