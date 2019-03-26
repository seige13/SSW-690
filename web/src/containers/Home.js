import React, {Component} from "react";
import {Carousel, InputGroup, FormControl} from "react-bootstrap";
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

        <div className="row justify-content-md-center">
          <div className="col-6">
            <InputGroup className="mb-3 mt-3">
              <FormControl
                placeholder="Search"
                aria-label="Search"
                aria-describedby="basic-addon2"
              />
              <InputGroup.Append>
                <InputGroup.Text id="basic-addon2">
                  Find / Create your hobby community
                </InputGroup.Text>
              </InputGroup.Append>
            </InputGroup>
          </div>
        </div>

        <div className="container">
          <CardCollection/>
        </div>
      </div>
    );
  }
}
