import React, {Component} from "react";
import ApiService from '../services/ApiService';
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import EventList from "../components/EventsList";
import {Alert} from "react-bootstrap";
import Bloglist from '../components/BlogList';

export default class ViewHobby extends Component {
  constructor(props) {
    super(props);

    this.state = {
      hobby: null,
      isLoading: true
    };
  }

  componentDidMount() {
    ApiService.getHobbyById(this.props.match.params.id)
      .then(function (response) {
        this.setState({
          hobby: response.hobby,
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

  renderPage() {
    return (
      <div className="ViewHobby">
        <div className={'row'}>
          <div className={'col'}>
            <h2 className={'text-center pt-3 mt-3'}>Welcome to {this.state.hobby.name}</h2>
          </div>
        </div>
        <div className={'row mt-4'}>
          <div className={'col'}>
            <img src={'https://via.placeholder.com/250'} alt={'placeholder'} />
          </div>
          <div className={'col-9'}>
            {this.state.hobby.description}
          </div>
        </div>
        <EventList hobby={this.props.match.params.id}/>
        <Bloglist hobby={this.props.match.params.id} />
      </div>
    )
  }

  render() {
    if (this.state.hasErrors) {
      var alertMessage = <Alert variant={'danger'} onClose={this.onAlertClose} className={'mt-3 mb-3'}
                                dismissible>{this.state.errorMessage}</Alert>
    }

    return (
      <div className="ViewHobby">
        {this.state.isLoading ? (
          <FontAwesomeIcon icon="sync" className="fa-spin spinning"/>) : (
          this.state.hasErrors ? (alertMessage) :
           this.renderPage()
        )}
      </div>
    );
  }
}
