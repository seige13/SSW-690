import React, {Component} from 'react';
import Table from "react-bootstrap/Table";
import ApiService from "../services/ApiService";
import {Alert} from "react-bootstrap";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";

export default class EventsList extends Component {
  constructor(props) {
    super(props);
    this.state = {
      isLoading: true,
      hasErrors: false,
      errorMessage: '',
      blogs: []
    };
  }

  componentDidMount() {
    ApiService.getAllBlogs().then(function (response) {
      this.setState({
        blogs: response.list,
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
  }

  renderBlogList() {
    return <div>
      <h4>Blog List</h4>
      <Table striped bordered hover>
        <thead>
        <tr>
          <th>Id</th>
          <th>Title</th>
          <th>Content</th>
        </tr>
        </thead>
        <tbody>
        {this.state.blogs.map((blog, index) => {
          return <tr key={index}>
            <td>{blog.blogId}</td>
            <td>{blog.title}</td>
            <td>{blog.content}</td>
          </tr>
        })
        }
        </tbody>
      </Table>
    </div>
  }

  render() {
    if (this.state.hasErrors) {
      var alertMessage = <Alert variant={'danger'} onClose={this.onAlertClose} className={'mb-3'}
                                dismissible>{this.state.errorMessage}</Alert>
    }

    return (
      <div className="BlogList mt-4">
        {this.state.isLoading ? (
          <FontAwesomeIcon icon="sync" className="fa-spin spinning"/>) : (
          this.state.hasErrors ? (alertMessage) :
            this.renderBlogList()
        )}
      </div>
    );
  }
}
