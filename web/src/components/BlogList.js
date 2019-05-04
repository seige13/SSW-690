import React, {Component} from 'react';
import Table from "react-bootstrap/Table";
import ApiService from "../services/ApiService";
import {Alert} from "react-bootstrap";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {Link, withRouter} from "react-router-dom";
import './BlogList.css'

class BlogList extends Component {
  constructor(props) {
    super(props);
    this.state = {
      isLoading: true,
      hasErrors: false,
      errorMessage: '',
      blogs: []
    };

    this.getBlogRows = this.getBlogRows.bind(this);
  }

  navigateToBlogView = event => {
    let blogId = event.currentTarget.getAttribute('data-item');

    this.props.history.push(`/hobby/${this.props.hobby}/blog/${blogId}`);
  };

  componentDidMount() {
    ApiService.getAllBlogsByHobbyId(this.props.hobby).then(function (response) {
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

  getBlogRows(blogs) {
    return blogs.map((blog, index) => {
      let date = new Date(Date.parse(blog.createDate));
      return (
        <tr key={index} onClick={this.navigateToBlogView} data-item={blog.blogId}>
          <td data-item={blog.blogId}>{blog.blogId}</td>
          <td data-item={blog.blogId}>{blog.title}</td>
          <td data-item={blog.blogId} dangerouslySetInnerHTML={{__html: blog.content}} className='ellipsis'/>
          <td data-item={blog.blogId}>
            {date.toLocaleDateString('en-US', {
              weekday: 'long',
              year: 'numeric',
              month: 'long',
              day: 'numeric',
              hour: 'numeric',
              minute: 'numeric'
            })}
          </td>
        </tr>
      )
    }, this)
  }

  renderBlogList() {
    return <div>
      <h4>Blog List</h4>
      <Link to={`/hobby/${this.props.hobby}/blog/add`} className={'text-right btn btn-primary mt-4 mb-4'}>Create My Post</Link>

      {this.state.blogs !== null ?
        <Table striped bordered hover>
        <thead>
        <tr>
          <th>Id</th>
          <th>Title</th>
          <th>Content</th>
          <th>Created Date</th>
        </tr>
        </thead>
        <tbody>
        {this.getBlogRows(this.state.blogs)}
        </tbody>
      </Table> :
        <div className="card bg-light">
          <div className="card-body text-center">
            There are no blogs created for this hobby yet...
          </div>
        </div>
      }
      <br/>

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

export default withRouter(BlogList);
