import React, {Component} from "react";
import ApiService from "../services/ApiService";

export default class ViewEvent extends Component {
  constructor(props) {
    super(props);
    this.state = {
      isLoading: false,
      event: {},
      isEventJoined: false
    }

  }

  async isEventJoined() {

  }

  joinEvent = event => {
    event.preventDefault();

    ApiService.joinEvent(this.props.match.params.eventId, this.props.loggedInUser.id)
      .then(function (response) {
        if (response) {
          console.log(response);
          this.setState({
            isLoading: false,
            isEventJoined: true
          });
        } else {
          console.warn('No events in database');
          this.setState({
            isLoading: false,
            rows: this.state.cardData
          });
        }

      }.bind(this))
      .catch(function (error) {
        console.log(error.statusText);

        this.setState({
          hasErrors: true,
          isLoading: false,
          errorMessage: 'There has been an error processing your request.',
        });
      }.bind(this));

  };

  checkIfUserJoinedEvent = () => {
    let userId = this.props.loggedInUser.id;
    let eventId = this.props.match.params.eventId;
    ApiService.getEventsUserJoinedByUserId(userId)
      .then(function (response) {
        if (response) {
          let events = response.events;

          console.log(events);

          let eventJoined = false;
          for(let i = 0; i < events.length - 1; i++) {
            if (events[i].eventsId === eventId) {
              eventJoined = true;
              break;
            }
          }
          console.log(eventJoined);
          if (eventJoined) {
            this.setState({
              isEventJoined: true
            });
          }
        }

      }.bind(this))
      .catch(function (error) {

      });
  };

  componentDidMount() {
    this.setState({isLoading: true});

    ApiService.getEventsById(this.props.match.params.eventId)
      .then(function (response) {
        if (response) {
          this.setState({
            isLoading: false,
            event: response.events
          });
        } else {
          console.warn('No events in database');
          this.setState({
            isLoading: false,
            rows: this.state.cardData
          });
        }

      }.bind(this))
      .catch(function (error) {
        console.log(error.statusText);

        this.setState({
          hasErrors: true,
          isLoading: false,
          errorMessage: 'There has been an error processing your request.',
        });
      }.bind(this));

    this.checkIfUserJoinedEvent();
  }

  render() {
    let date = new Date(Date.parse(this.state.event.eventsTime));
    return <div className={'ViewEvent mt-4'}>
      <div className={'row'}>
        <div className={'col'}>
          <h2 className={'text-center pt-3 mt-3'}>Welcome to {this.state.event.eventsTitle}</h2>
        </div>
      </div>
      <div className={'row mt-4'}>
        <div className={'col'}>
          <img src={'https://via.placeholder.com/250'} alt={'placeholder'}/>
        </div>
        <div className={'col-9'}>
          <ul>
            <li>Who: {this.state.event.holder}</li>
            <li>Where: {this.state.event.location}</li>
            <li>When: {date.toLocaleDateString('en-US', {
              weekday: 'long',
              year: 'numeric',
              month: 'long',
              day: 'numeric',
              hour: 'numeric',
              minute: 'numeric'
            })}</li>
            <li>How Much: ${this.state.event.fee}</li>
          </ul>

          <button className={'btn btn-primary'} onClick={this.joinEvent} disabled={this.state.isEventJoined}>
            {this.state.isEventJoined ? 'Joined Event Already!' : 'Join Event!'}
          </button>
        </div>
      </div>
      <div className={'row mt-4'}>
        <div className={'col'}>
          {this.state.event.description}
        </div>
      </div>
    </div>
  }

}
