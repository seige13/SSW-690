import React, {Component} from 'react';
import {Card} from 'react-bootstrap';
import {Link, withRouter} from "react-router-dom";
import ApiService from '../services/ApiService';
import './HobbiesList.css';
import addHobby from '../assets/add.png'

class EventsList extends Component {

  constructor(props) {
    super(props);
    this.state = {
      searchTerm: '',
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
          <div className={'row'}>
            {this.state.rows.map((item, index) =>
              <div key={index} className={'col-3 mb-3'}>
                <Link to={`/hobby/${this.props.hobby}/event/${item.eventsId}`}>
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
    );
  }
}

export default withRouter(EventsList)

