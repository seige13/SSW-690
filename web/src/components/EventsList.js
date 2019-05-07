import React, {Component} from 'react';
import {Card} from 'react-bootstrap';
import {Link, withRouter} from "react-router-dom";
import ApiService from '../services/ApiService';
import './HobbiesList.css';
import addHobby from '../assets/add.png'
import defaultHobbyImage from '../assets/default-hobby.jpg';

class EventsList extends Component {

  constructor(props) {
    super(props);
    this.state = {
      rows: []
    };
  }

  componentDidMount() {
    this.setState({isLoading: true});

    ApiService.getAllEventsByHobbyId(this.props.hobby)
      .then(function (response) {
        if (response.list) {
          this.setState({
            isLoading: false,
            rows: response.list
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
  }

  render() {
    return (
      <div className={'mt-4'}>
        <h4>Events</h4>
        <div className={'row'}>
          {this.state.rows.map((item, index) => {
              var img = item.eventsImage !== null ? item.eventsImage : defaultHobbyImage;
              return (
                <div key={index} className={'col-3 mb-3'}>
                  <Link to={`/hobby/${this.props.hobby}/event/${item.eventsId}`}>
                    <Card style={{width: "16rem"}}>
                      <Card.Img
                        variant="top"
                        src={img}
                        className={'card-img'}
                      />
                      <Card.Body>
                        <Card.Title>{item.eventsTitle}</Card.Title>
                        <Card.Text>
                          {item.description}
                        </Card.Text>
                      </Card.Body>
                    </Card>
                  </Link>
                </div>
              )
            })
          }
          <div className={'col-3 mb-3'}>
            <Link to={`/hobby/${this.props.hobby}/event/add`}>
              <Card style={{width: "16rem", height: '100%'}}>
                <Card.Img
                  variant="top"
                  src={addHobby}
                  className={'card-img'}
                />
                <Card.Body>
                  <Card.Title>Create an Event</Card.Title>
                  <Card.Text>

                  </Card.Text>
                </Card.Body>
              </Card>
            </Link>
          </div>
        </div>
      </div>
    );
  }
}

export default withRouter(EventsList)

