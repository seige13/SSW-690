import React, {Component} from "react";
import Form from "react-bootstrap/Form";
import ApiService from "../services/ApiService";
import LoaderButton from "../components/LoaderButton";
import {Link} from "react-router-dom";

export default class ViewBlog extends Component {
  constructor(props) {
    super(props);
    this.state = {
      isSubmitting: false,
      isLoading: true,
      hasErrors: false,
      blog: {},
      comments: [],
      userComment: ''
    }
  }

  handleChange = event => {
    this.setState({[event.target.id]: event.target.value});
  };

  addComment = event => {
    event.preventDefault();
    this.setState({isSubmitting: true});

    ApiService.addComment(this.state.userComment, this.props.match.params.blogId, this.props.loggedInUser.id)
      .then(success => {
        this.setState({
          userComment: '',
          isSubmitting: false
        });

        this.getBlogWithComments();
      }).catch(error => {
      this.setState({
        hasErrors: true,
        isSubmitting: false,
        userComment: '',
      });
    })
  };

  getBlogWithComments = () => {
    this.setState({isLoading: true});
    ApiService.getBlogById(this.props.match.params.blogId)
      .then(success => {
        this.setState({
          blog: success.blog,
          comments: success.comments,
          isLoading: false
        });
      }).catch(error => {
      this.setState({
        hasErrors: true,
        isLoading: false
      });
    })
  };

  componentDidMount() {
    this.getBlogWithComments();
  }

  createBlogView = () => {
    let date = new Date(Date.parse(this.state.blog.createDate));

    return (
      <div className="card card-default">
        <div className="card-header">
          <section className="card-title">
            <div className={'row justify-content-end'}>
              <div className={'col-9 pull-right'}>
                <h2>{this.state.blog.title}</h2>
              </div>
            </div>
          </section>
        </div>
        <ul className="list-group list-group-flush">
          <div className={'list-group-item'}>
            <section className="row card-body">
              <section id="user-description" className="col-md-3 ">
                <section className="well">

                  <h4>BLOG USERNAME</h4>
                  <section className="pull-left" id="id1">
                    <abbr title="count of posts in this topic">#1</abbr>
                  </section>
                </section>
              </section>

              <section className="col-md-9">

                <div dangerouslySetInnerHTML={{__html: this.state.blog.content}}></div>

                <hr/>
                <div className={'row'}>
                  <div className={'col post-footer'}>
                    <time className="pull-right">
                      <i className={"fa fa-calendar"}/> {date.toLocaleDateString('en-US', {
                      weekday: 'long',
                      year: 'numeric',
                      month: 'long',
                      day: 'numeric',
                      hour: 'numeric',
                      minute: 'numeric'
                    })}
                    </time>
                  </div>
                </div>
              </section>
            </section>
          </div>
          {this.state.comments.map((comment, index) => {
            let date = new Date(Date.parse(comment.time));
            return (

              <li className="list-group-item" key={index}>
                <section className="row card-body">
                  <section id="user-description" className="col-md-3 ">
                    <section className="well">

                      <h4>COMMENT USERNAME</h4>
                      <section className="pull-left" id={`id${index + 2}`}>
                        <abbr title="count of posts in this topic">#{index + 2}</abbr>
                      </section>
                    </section>
                  </section>

                  <section className="col-md-9">

                    <div>{comment.content}</div>

                    <hr/>
                    <div className={'row'}>
                      <div className={'col post-footer'}>
                        <time className="pull-right">
                          <i className={"fa fa-calendar"}/> {date.toLocaleDateString('en-US', {
                          weekday: 'long',
                          year: 'numeric',
                          month: 'long',
                          day: 'numeric',
                          hour: 'numeric',
                          minute: 'numeric'
                        })}
                        </time>
                      </div>
                    </div>
                  </section>
                </section>
              </li>
            )
          })}
        </ul>


      </div>
    )
  };

  render() {
    return (
      <div className="row clearfix mt-4">
        <div className="col-md-12 column">
          <Link to={`/hobby/${this.props.match.params.id}`} className={'btn btn-primary mb-3'}>&lsaquo; Back to Hobby</Link>

          {this.state.isLoading ? 'Loading' : this.createBlogView()}
          <Form.Group controlId="userComment" className={'mt-4'}>
            <Form.Label>Add Comment</Form.Label>
            <Form.Control
              as="textarea"
              rows="3"
              value={this.state.userComment}
              onChange={this.handleChange}/>
          </Form.Group>

          <LoaderButton
            block
            disabled={this.state.isSubmitting && this.state.userComment !== ''}
            type="submit"
            isLoading={this.state.isSubmitting}
            text="Add Comment"
            loadingText="Adding Comment..."
            onClick={this.addComment}
          />
        </div>
      </div>
    )
  }
}
