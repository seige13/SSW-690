import React, {Component} from 'react';
import {Card, FormControl, InputGroup} from 'react-bootstrap';
import {Link, withRouter} from "react-router-dom";
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
import defaultHobbyImage from '../assets/default-hobby.jpg';

class HobbiesList extends Component {

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

    ApiService.getAllHobbies()
      .then(function (response) {
        if (response.list !== 'undefined' && response.list.length !== 0) {
          this.setState({
            isLoading: false,
            rows: response.list
          });
        } else {
          console.warn('No hobbies in database');
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

  onSearchHandler(e) {
    e.persist();
    let newRows = this.state.rows.filter(this.searchingFor(e.target.value));

    this.setState(prevState => {
      return {
        rows: e.target.value !== '' ? newRows : this.state.cardData,
        searchTerm: e.target.value,
      }
    });
  }

  searchingFor(searchingTerm) {
    return function (x) {
      return x.name.toLowerCase().includes(searchingTerm.toLowerCase()) || false;
    }
  }

  render() {
    return (
      <div className="container">
        <div className="row justify-content-md-center">
          <div className="col-6">
            <InputGroup className="mb-3 mt-3">
              <FormControl
                placeholder="Search"
                aria-label="Search"
                aria-describedby="basic-addon2"
                value={this.state.searchTerm}
                onChange={this.onSearchHandler.bind(this)}
              />
              <InputGroup.Append>
                <InputGroup.Text id="basic-addon2">
                  Find your hobby community
                </InputGroup.Text>
              </InputGroup.Append>
            </InputGroup>
          </div>
        </div>
        <div className={'row'}>
          {this.state.rows.map((item, index) =>
            <div key={index} className={'col-3 mb-3'}>
              <Link to={`/hobby/${item.hobbyId}`}>
                <Card style={{width: "16rem"}}>
                  <Card.Img
                    variant="top"
                    src={typeof item.img !== "undefined" && item.img !== '' ? item.img : defaultHobbyImage}
                    className={'card-img'}
                  />
                  <Card.Body>
                    <Card.Title>{item.name}</Card.Title>
                    <Card.Text>
                      {item.description}
                    </Card.Text>
                  </Card.Body>
                </Card>
              </Link>
            </div>)
          }

          <div className={'col-3 mb-3'}>
            <Link to={`/add/hobby`}>
              <Card style={{width: "16rem", height: '100%'}}>
                <Card.Img
                  variant="top"
                  src={addHobby}
                  className={'card-img'}
                />
                <Card.Body>
                  <Card.Title>Create a Hobby</Card.Title>
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

export default withRouter(HobbiesList)

