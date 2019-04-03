import React, {Component} from "react";
import {Carousel} from "react-bootstrap";
import CardCollection from '../components/CardCollection';
import basketBallImage from '../assets/basketball.jpg'
import divingImage from '../assets/diving.jpg'
import photographyImage from '../assets/photography.jpg'
import './Home.css';


export default class Home extends Component {
  render() {
    return (
      <div className="Home mt-3">
        <Carousel>
          <Carousel.Item>
            <img
              className="d-block w-100"
              src={basketBallImage}
              alt="Basketball"
            />
          </Carousel.Item>
          <Carousel.Item>
            <img
              className="d-block w-100"
              src={divingImage}
              alt="Diving"
            />
          </Carousel.Item>
          <Carousel.Item>
            <img
              className="d-block w-100"
              src={photographyImage}
              alt="Photography"
            />
          </Carousel.Item>
        </Carousel>

        <CardCollection/>
      </div>
    );
  }
}
