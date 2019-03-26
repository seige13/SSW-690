import React, {Component} from 'react';
import {Button, FormGroup, FormControl, FormLabel, Alert} from 'react-bootstrap';
import './ForgotPassword.css';

export default class ForgotPassword extends Component {
  constructor(props) {
    super(props);

    this.state = {
      email: '',
      emailSent: false,
      alertMessage: ''
    };
  }

  validateForm() {
    return this.state.email.length > 0;
  }

  handleChange = event => {
    this.setState({
      [event.target.id]: event.target.value
    });
  };

  handleSubmit = event => {
    event.preventDefault();

    // @TODO Send forgot password request to API

    this.setState({
      emailSent: true,
      alertMessage: `An email with a link to reset your password has been sent to ${this.state.email}`
    })
  };

  render() {
    if (this.state.emailSent) {
      var alertMessage = <Alert variant={'info'} onClose={this.onAlertClose} className={'mb-3'}
                                dismissible>{this.state.alertMessage}</Alert>
    }

    return (
      <div className="ForgotPassword">
        <h4 className={'text-center mb-3'}>
          Please enter your email address and we will send you a code to reset your password
        </h4>
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
          <Button
            block
            size="large"
            disabled={!this.validateForm()}
            type="submit"
          >
            Forgot Password
          </Button>
        </form>
      </div>
    );
  }
}
