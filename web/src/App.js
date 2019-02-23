import React, { Component } from "react";
import { Nav, Navbar } from "react-bootstrap";
import NavLink from "react-bootstrap/NavLink";
import { LinkContainer } from "react-router-bootstrap";
import Routes from "./Routes";
import "./App.css";


class App extends Component {
  render() {
    return (
      <div className="App container">
        <Navbar bg="light" expand="lg">
          <LinkContainer to="/">
            <Navbar.Brand >Hobby Matcher</Navbar.Brand>
          </LinkContainer>
          <Navbar.Toggle aria-controls="basic-navbar-nav" />
          <Navbar.Collapse id="basic-navbar-nav" className={"pullRight"}>
            <Nav className="ml-auto">
              <LinkContainer to="/sign-up">
                <NavLink>
                  Sign Up
                </NavLink>
              </LinkContainer>
              <LinkContainer to="/login">
                <NavLink>
                  Login
                </NavLink>
              </LinkContainer>
            </Nav>
          </Navbar.Collapse>
        </Navbar>
        <Routes />
      </div>
    );
  }
}

export default App;
