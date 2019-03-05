import React, {Component} from 'react';
import {Button, FormGroup, FormControl, FormLabel} from 'react-bootstrap';
import './ForgotPassword.css';

export default class ForgotPassword extends Component {
  constructor(props) {
    super(props);

    this.state = {
      email: '',
      password: ''
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
  };

  render() {
    return (
      <div className="ForgotPassword">
        <h4 className={'text-center mb-3'}> Please enter your email address and we will send you a code to reset your password </h4>
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
