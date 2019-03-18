import React, {Component} from 'react';
import {FormGroup, FormControl, FormLabel, Alert} from 'react-bootstrap';
import {Link} from "react-router-dom";
import ApiService from '../services/ApiService';
import LoaderButton from '../components/LoaderButton'
import './Login.css';

export default class Login extends Component {
  constructor(props) {
    super(props);

    this.state = {
      email: '',
      password: '',
      isLoading: false,
      hasErrors: false,
      errorMessage: ''
    };
  }

  validateForm() {
    return this.state.email.length > 0 && this.state.password.length > 0;
  }

  handleChange = event => {
    this.setState({
      [event.target.id]: event.target.value
    });
  };

  onAlertClose = () => {
    this.setState({
      hasErrors: false,
      errorMessage: ''
    });
  };

  handleSubmit = async event => {
    event.preventDefault();

    this.setState({isLoading: true});

    ApiService.userLogin(this.state.email, this.state.password)
      .then(function (success) {
        this.props.userHasAuthenticated(true);
        this.props.history.push("/");
      }.bind(this))
      .catch(function (error) {
        this.setState({
          hasErrors: true,
          isLoading: false,
          errorMessage: 'Invalid Login Credentials!'
        });

        console.log(error.statusText);
      }.bind(this));
  };

  render() {
    if (this.state.hasErrors) {
      var alertMessage = <Alert variant={'danger'} onClose={this.onAlertClose} className={'mb-3'}
                                dismissible>{this.state.errorMessage}</Alert>
    }

    return (
      <div className="Login">
        {alertMessage}
        <form onSubmit={this.handleSubmit}>
          <FormGroup controlId="email" size="large">
            <FormLabel>Email</FormLabel>
            <FormControl
              autoFocus
              type="email"
              value={this.state.email}
              onChange={this.handleChange}
            />
          </FormGroup>
          <FormGroup controlId="password" size="large">
            <FormLabel>Password</FormLabel>
            <FormControl
              value={this.state.password}
              onChange={this.handleChange}
              type="password"
            />
          </FormGroup>
          <Link to={'/forgot-password'} className={'float-right mb-3'}>
            Forgot Password?
          </Link>
          <LoaderButton
            block
            disabled={!this.validateForm()}
            type="submit"
            isLoading={this.state.isLoading}
            text="Login"
            loadingText="Logging in…"
          />
        </form>
      </div>
    );
  }
}
