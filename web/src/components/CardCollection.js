import React, {Component} from 'react';
import {Card} from 'react-bootstrap';
import {Link} from "react-router-dom";


export default class CardCollection extends Component {

  constructor(props) {
    super(props);

    // @TODO get this data from backend
    this.cardData = [
      {
        name: 'Basketball',
        description: 'basketball description',
        img: 'https://via.placeholder.com/250.png'
      }, {
        name: 'Basketball2',
        description: 'basketball2 description',
        img: 'https://via.placeholder.com/250.png'
      }, {
        name: 'Basketball3',
        description: 'basketball3 description',
        img: 'https://via.placeholder.com/250.png'
      }, {
        name: 'Basketball4',
        description: 'basketball4 description',
        img: 'https://via.placeholder.com/250.png'
      }, {
        name: 'Basketball5',
        description: 'basketball5 description',
        img: 'https://via.placeholder.com/250.png'
      }, {
        name: 'Basketball6',
        description: 'basketball6 description',
        img: 'https://via.placeholder.com/250.png'
      }, {
        name: 'Basketball7',
        description: 'basketball7 description',
        img: 'https://via.placeholder.com/250.png'
      }, {
        name: 'Basketball8',
        description: 'basketball8 description',
        img: 'https://via.placeholder.com/250.png'
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

