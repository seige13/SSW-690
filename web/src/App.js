import React, { Component, Fragment } from 'react';
import { Nav, Navbar } from 'react-bootstrap';
import NavLink from 'react-bootstrap/NavLink';
import { LinkContainer } from 'react-router-bootstrap';
import Routes from './Routes';
import './App.css';

class App extends Component {
  constructor(props) {
    super(props);

    this.state = {
      isAuthenticated: false
    };
  }

  userHasAuthenticated = authenticated => {
    this.setState({ isAuthenticated: authenticated });
  };

  handleLogout = event => {
    this.userHasAuthenticated(false);
  };

  render() {
    const childProps = {
      isAuthenticated: this.state.isAuthenticated,
      userHasAuthenticated: this.userHasAuthenticated
    };

    return (
      <div className="App container">
        <Navbar bg="light" expand="lg">
          <LinkContainer to="/">
            <Navbar.Brand>Hobby Matcher</Navbar.Brand>
          </LinkContainer>
          <Navbar.Toggle aria-controls="basic-navbar-nav" />
          <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="ml-auto">
              {this.state.isAuthenticated ? (
                <NavLink onClick={this.handleLogout}>Logout</NavLink>
              ) : (
                <Fragment>
                  <LinkContainer to="/signup">
                    <NavLink>Signup</NavLink>
                  </LinkContainer>
                  <LinkContainer to="/login">
                    <NavLink>Login</NavLink>
                  </LinkContainer>
                </Fragment>
              )}
            </Nav>
          </Navbar.Collapse>
        </Navbar>
        <Routes childProps={childProps} />
      </div>
    );
  }
}

export default App;
