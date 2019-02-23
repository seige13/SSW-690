import React, {Component} from "react";

import "./Home.css";

export default class Home extends Component {
  render() {
    return (
      <div className="Home">
        <div className="lander">
          <h1>Hobby Matcher</h1>
          <p>An application for matching users with similar hobbies</p>
        </div>
      </div>
    );
  }
}
