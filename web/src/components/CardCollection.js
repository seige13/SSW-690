import React, {Component} from 'react';
import {Card} from 'react-bootstrap';
import {Link} from "react-router-dom";
import './CardCollection.css';

import badminton from '../assets/event-cards/badminton.png';
import basketball from '../assets/event-cards/basketball.png'
import bodybuilding from '../assets/event-cards/bodybuilding.png'
import cat from '../assets/event-cards/cat.png'
import diving from '../assets/event-cards/diving.png'
import drawing from '../assets/event-cards/drawing.png'
import orchid from '../assets/event-cards/orchid.jpg'
import photography from '../assets/event-cards/photography.png'

export default class CardCollection extends Component {

  constructor(props) {
    super(props);

    // @TODO get this data from backend
    this.cardData = [
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
      }, {
        name: 'Photography',
        description: 'Learn more about photography',
        img: photography
      }];
  }

  render() {
    return (
      <div className={'row'}>
        {this.cardData.map((item, index) =>
          <div key={index} className={'col-3 mb-3'}>
            <Link to={`hobby/${index}`}>
              <Card style={{width: "16rem"}}>
                <Card.Img
                  variant="top"
                  src={item.img}
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
      </div>
    );
  }

}

