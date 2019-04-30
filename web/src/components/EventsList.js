import React, {Component} from 'react';
import {Card} from 'react-bootstrap';
import {Link} from "react-router-dom";
import ApiService from '../services/ApiService';
import './HobbiesList.css';

import badminton from '../assets/event-cards/badminton.png'
import basketball from '../assets/event-cards/basketball.png'
import bodybuilding from '../assets/event-cards/bodybuilding.png'
import cat from '../assets/event-cards/cat.png'
import diving from '../assets/event-cards/diving.png'
import drawing from '../assets/event-cards/drawing.png'
import orchid from '../assets/event-cards/orchid.jpg'
import addHobby from '../assets/add.png'

export default class EventsList extends Component {

  constructor(props) {
    super(props);
    this.state = {
      searchTerm: '',
      cardData: [
        {
          name: 'Basketball',
          description: 'Learn more about basketball',
          img: basketball
        }, {
          name: 'Badminton',
          description: 'Learn more about Badminton',
          img: badminton
        }, {
          name: 'Body building',
          description: 'Learn more about body building',
          img: bodybuilding
        }, {
          name: 'Cat',
          description: 'Learn more about owning a cat',
          img: cat
        }, {
          name: 'Diving',
          description: 'Learn more about diving',
          img: diving
        }, {
          name: 'Drawing',
          description: 'Learn more about drawing',
          img: drawing
        }, {
          name: 'Orchid',
          description: 'Learn more about growing orchids',
          img: orchid
        }],
      rows: []
    };
  }

  componentDidMount() {
    this.setState({isLoading: true});

    ApiService.getAllEvents()
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
        <div className="container">
          <div className={'row'}>
            {this.state.rows.map((item, index) =>
              <div key={index} className={'col-3 mb-3'}>
                <Link to={`event/${index}`}>
                  <Card style={{width: "16rem"}}>
                    <Card.Img
                      variant="top"
                      src={item.img}
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
              </div>)
            }
            <Link to={`/hobby/${this.props.hobby}/event/add`}>
              <Card style={{width: "16rem"}}>
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

