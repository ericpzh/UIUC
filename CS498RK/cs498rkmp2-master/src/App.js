import React, { Component } from 'react';
import {Navbar, NavbarBrand, Jumbotron, Button, Container } from 'reactstrap';
import { Link } from 'react-router-dom';
import './App.scss';
import './App.css';

class App extends Component {
  componentDidMount() {
     document.title = "Animation World";
  }
  render() {
      return (
          <div className = 'my_app'>
            <Navbar color="dark" light expand="md" sticky="top">
              <NavbarBrand href="/cs498rkmp2"><h2 className="header">Animation World</h2></NavbarBrand>
            </Navbar>
              <div className = "background">

              <Jumbotron>
                <Container>
                  <h1 className="display-3">TOP500 Animation Movies</h1>
                  <p className="lead">This project includes top-500 most popular animation movies accroding to TMDB.</p>
                  <hr className="my-2" />
                  <p className="lead">
                    <Link to="/cs498rkmp2/Home">Get Started!</Link>
                  </p>
                </Container>
              </Jumbotron>


              </div>
          </div>
      );
    };
};

export default App;
