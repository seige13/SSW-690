import React, {Component} from 'react';
import {FormGroup, FormControl, FormLabel, Alert} from 'react-bootstrap';
import ApiService from '../services/ApiService'
import LoaderButton from '../components/LoaderButton';
import './Signup.css';

export default class Signup extends Component {
  constructor(props) {
    super(props);

    this.state = {
      isLoading: false,
      email: '',
      username: '',
      firstname: '',
      lastname: '',
      password: '',
      confirmPassword: '',
      confirmationCode: '',
      newUser: null,
      hasErrors: false,
      errorMessage: ''
    };
  }

  validateForm() {
    return (
      this.state.email.length > 0 &&
      this.state.password.length > 0 &&
      this.state.password === this.state.confirmPassword
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

    this.setState({newUser: 'test'});

    this.setState({isLoading: false});

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
      <div className={'SignUp'}>
        {alertMessage}
        <form onSubmit={this.handleSubmit}>
          <FormGroup controlId="username">
            <FormLabel>User Name</FormLabel>
            <FormControl
              autoFocus
              type="text"
              value={this.state.username}
              onChange={this.handleChange}
            />
          </FormGroup>
          <FormGroup controlId="firstname">
            <FormLabel>First Name</FormLabel>
            <FormControl
              autoFocus
              type="text"
              value={this.state.firstname}
              onChange={this.handleChange}
            />
          </FormGroup>
          <FormGroup controlId="lastname">
            <FormLabel>Last Name</FormLabel>
            <FormControl
              autoFocus
              type="text"
              value={this.state.lastname}
              onChange={this.handleChange}
            />
          </FormGroup>
          <FormGroup controlId="email">
            <FormLabel>Email</FormLabel>
            <FormControl
              autoFocus
              type="email"
              value={this.state.email}
              onChange={this.handleChange}
            />
          </FormGroup>
          <FormGroup controlId="password">
            <FormLabel>Password</FormLabel>
            <FormControl
              value={this.state.password}
              onChange={this.handleChange}
              type="password"
            />
          </FormGroup>
          <FormGroup controlId="confirmPassword">
            <FormLabel>Confirm Password</FormLabel>
            <FormControl
              value={this.state.confirmPassword}
              onChange={this.handleChange}
              type="password"
            />
          </FormGroup>
          <LoaderButton
            block
            disabled={!this.validateForm()}
            type="submit"
            isLoading={this.state.isLoading}
            text="Signup"
            loadingText="Signing up…"
          />
        </form>
      </div>
    );
  }

  render() {
    return (
      <div className="Signup">
        {this.renderForm()}
      </div>
    );
  }
}
