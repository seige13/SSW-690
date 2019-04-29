import React, {Component} from "react";
import Form from "react-bootstrap/Form";
import ApiService from "../services/ApiService";
import LoaderButton from "../components/LoaderButton";

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

  addComment = () => {
    this.setState({isSubmitting: true});

    ApiService.addCommentToBlog(this.props.match.params.blogId, this.state.userComment)
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

  render() {
    return (
      <div className="row clearfix mt-4">
        <div className="col-md-12 column">
          <div className="card card-default">
            <div className="card-header">
              <section className="card-title">
                <div className={'row justify-content-end'}>
                  <div className={'col-9 pull-right'}>
                    <h2>Blog Post Title Here </h2>
                  </div>
                </div>
              </section>
            </div>
            <section className="row card-body">
              <section id="user-description" className="col-md-3 ">
                <section className="well">
                  <div className="dropdown">
                    MOHAMMAD SHARIFI
                  </div>
                  <figure>
                    <img className="img-rounded img-responsive" src="http://www.webdesignforums.net/img/wdf_avatar.jpg"
                         alt="Mohammad Sharifi's avatar" />
                  </figure>

                  <section className="pull-left" id="id1">
                    <abbr title="count of posts in this topic">#1</abbr>
                  </section>
                </section>
              </section>

              <section className="col-md-9">

                  Hi, I'm wondering whats the name of the element that appaers in this website: Startupbus

                  Im talking about the images that remain static in the back and change everytime a new section is
                  reached.

                  Is there a framework that does this?

                  Thank you for helping

                <hr />
                <div className={'row'}>
                  <div className={'col post-footer'}>
                    <time className="pull-right">
                      <i className={"fa fa-calendar"}/> 2014-09-15 , <i className={"fa fa-clock-o"}/> 1:35 pm
                    </time>
                  </div>
                </div>
              </section>
            </section>

            <ul className="list-group list-group-flush">
              <li className="list-group-item">
                <section className="row card-body">
                  <section id="user-description" className="col-md-3 ">
                    <section className="well">
                      <div className="dropdown">
                        MOHAMMAD SHARIFI
                      </div>
                      <figure>
                        <img className="img-rounded img-responsive" src="http://www.webdesignforums.net/img/wdf_avatar.jpg"
                             alt="Mohammad Sharifi's avatar" />
                      </figure>
                    </section>
                  </section>

                  <section className="col-md-9">
                    <h2><i className={"fa fa-smile-o"} /> Whats the name of the element of this website?</h2>
                    <hr />
                    Hi, I'm wondering whats the name of the element that appaers in this website: Startupbus

                    Im talking about the images that remain static in the back and change everytime a new section is
                    reached.

                    Is there a framework that does this?

                    Thank you for helping
                  </section>
                </section>


              </li>
              <li className="list-group-item">Dapibus ac facilisis in</li>
              <li className="list-group-item">Vestibulum at eros</li>
            </ul>
          </div>

          <Form.Group controlId="userComment" className={'mt-4'}>
            <Form.Label>Add Comment</Form.Label>
            <Form.Control as="textarea" rows="3" />
          </Form.Group>

          <LoaderButton
            block
            disabled={this.state.isSubmitting}
            type="submit"
            isLoading={this.state.isSubmitting}
            text="Add Comment"
            loadingText="Adding Comment..."
          />
        </div>
      </div>
    )
  }
}
