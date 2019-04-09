import React, {Component} from 'react';
import {FormGroup, FormControl, FormLabel, Alert} from 'react-bootstrap';
import ApiService from '../services/ApiService'
import FileUpload from '../components/FileUpload';
import LoaderButton from '../components/LoaderButton';
import './AddHobby.css';

export default class AddHobby extends Component {
  constructor(props) {
    super(props);
    this.categories = [
      {id: 0, name: 'Select Hobby Category'},
      {id: 1, name: 'Sports'},
      {id: 2, name: 'Music'},
      {id: 3, name: 'Art'},
      {id: 4, name: 'Adventure'},
      {id: 5, name: 'Other'},
    ];

    this.state = {
      isLoading: false,
      name: '',
      description: '',
      category: this.categories[0].name,
      picture: '',
      hasErrors: false,
      errorMessage: ''
    };
  }

  validateForm() {
    return (
      this.state.name !== '' && this.state.description !== '' && this.isValidCategory()
    );
  }

  onCategoryChange = (event) => {
    if (event) {
      this.setState({
        category: event.target.value
      })
    }
  };

  isValidCategory() {
    return this.state.category !== '' && this.state.category !== this.categories[0].name;
  }

  handleChange = event => {
    this.setState({
      [event.target.id]: event.target.value
    });
  };

  handleSubmit = async event => {
    event.preventDefault();

    this.setState({isLoading: true});

    ApiService.createHobby(this.state.name, this.state.description, this.state.categories)
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
            <FormControl as="select" defaultValue={this.categories[0].name} onChange={this.onCategoryChange}>
              {
                this.categories.map(category => {
                  return <option value={category.name} key={category.id}>{category.name}</option>
                })
              }
            </FormControl>
          </FormGroup>

          <FormLabel>Hobby Photo</FormLabel>
          <FileUpload/>
          <LoaderButton
            block
            disabled={!this.validateForm()}
            type="submit"
            isLoading={this.state.isLoading}
            text="Create Hobby"
            loadingText="Creating Hobby..."
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
