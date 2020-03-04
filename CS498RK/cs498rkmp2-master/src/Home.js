import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import {Navbar, NavbarBrand, TabContent, TabPane, Nav, NavItem, NavLink, Container} from 'reactstrap';
import classnames from 'classnames';
import 'bootstrap/dist/css/bootstrap.min.css';
import './App.scss';
import GalleryView from './GalleryView.js'
import ListView from './ListView.js'
import DetailView from './DetailView.js'
import axios from 'axios';

export function ArrayPhaser(array){
  var ret = "";
  for (var i in array){
    ret += (String(array[i]) + ", ");
  }
  return ret.substring(0, ret.length - 2);
}

class Home extends Component {
  constructor(props) {
    super(props);
    this.toggle = this.toggle.bind(this);
    this.GetAll = this.GetAll.bind(this);
    this.GalleryPhaser = this.GalleryPhaser.bind(this);
    this.ListPhaser = this.ListPhaser.bind(this);
    this.DetailPhaser = this.DetailPhaser.bind(this);
    this.setselected = this.setselected.bind(this);
    this.setID = this.setID.bind(this);
    this.prev = this.prev.bind(this);
    this.next = this.next.bind(this);
    this.toggleModal = this.toggleModal.bind(this);
    this.state = {
      activeTab: 'GalleryView',
      movies: [],
      genres: {},
      selected: [],
      ID : '0',
      modal: false,
    };
  }

  componentDidMount() {
     document.title = "Animation World";
     this.GetAll();
  }

  toggle(tab) {
    if (this.state.activeTab !== tab) {
      this.setState({
        activeTab: tab
      });
    }
  }

  GetAll() {
    axios.get("https://api.themoviedb.org/3/genre/movie/list?api_key=85723da756ee8b840b03507adea58c08")
    .then((response)=>{
      var newgenres = {};
      for (var i in response["data"]["genres"]){
        newgenres[response["data"]["genres"][i]['id']] = response["data"]["genres"][i]['name'];
      }
      this.setState({
        genres: newgenres
      });
    })
    .catch(function (error) {
      //console.log(error);
    });

    this.setState({
      movies: []
    });
    for (var page = 1; page <= 25; page++){
      const url = "https://api.themoviedb.org/3/discover/movie?api_key=85723da756ee8b840b03507adea58c08&with_genres=16&original_language=JP&sort_by=popularity.desc&page=" + String(page);
      axios.get(url)
      .then((response)=>{
        var oldmovies = this.state.movies;
        var newmovies = [];
        for (var movie in oldmovies){
          newmovies.push(oldmovies[movie]);
        }

        for (var i = 0; i <= 19; i ++){
          newmovies.push(response['data']['results'][i]);
        }
        this.setState({
          movies: newmovies
        });
      })
      .catch(function (error) {
        //console.log(error);
      });
    }
  }

  GalleryPhaser() {
    var data = [];
    if(this.state.movies.length === 500){
      const baseurl = "https://image.tmdb.org/t/p/w500";
      var movies = this.state.movies;
      for (var i in movies){
        var genres = [];
        for (var genre in movies[i]['genre_ids']){
          genres.push(this.state.genres[movies[i]['genre_ids'][genre]]);
        }
        var selectedstr = [];
        for (var select in this.state.selected){
          selectedstr.push(this.state.genres[String(this.state.selected[select])]);
        }
        if (genres.filter(x => selectedstr.includes(x)).length === this.state.selected.length){
          data.push({ID:i,Name:movies[i]['title'],Types:"Score:" + movies[i]['vote_average'],Body:ArrayPhaser(genres),Img:baseurl + movies[i]['poster_path']});
        }
      }
    }
    return data;
  }

  ListPhaser() {
    var data = [];
    if(this.state.movies.length === 500){
      var movies = this.state.movies;
      for (var i in movies){
        data.push({ID:i,Name:movies[i]['title'],Popularity:movies[i]['popularity'],Release_date:new Date(movies[i]['release_date']),Vote_average:movies[i]['vote_average'],Vote_count:movies[i]['vote_count']});
      }
    }
    return data;
  }

  DetailPhaser() {
    var data = [];
    if(this.state.movies.length === 500){
      const baseurl = "https://image.tmdb.org/t/p/w342";
      var movies = this.state.movies;
      for (var i in movies){
        var genres = [];
        for (var genre in movies[i]['genre_ids']){
          genres.push(this.state.genres[movies[i]['genre_ids'][genre]]);
        }
        data.push({ID:i,Name:movies[i]['title'],Img:baseurl + movies[i]['poster_path'],Genres:ArrayPhaser(genres),Popularity:movies[i]['popularity'],Release_date:movies[i]['release_date'],Vote_average:movies[i]['vote_average'],Vote_count:movies[i]['vote_count'],Overview:movies[i]['overview']});
      }
    }
    return data;
  }

  setselected(id) {
    if(id === -1){
      this.setState({ selected: [] });
    }else{
      const index = this.state.selected.indexOf(id);
      if (index < 0) {
        this.state.selected.push(id);
      } else {
        this.state.selected.splice(index, 1);
      }
      this.setState({ selected: [...this.state.selected] });
    }
  }

  setID(id) {
    this.setState({ ID: String(id) });
    this.setState(prevState => ({
      modal: !prevState.modal
    }));
  }

  prev(){
    var currId = this.state.ID;
    this.setState({ID:String((parseInt(currId)-1+500)%500)});
  }

  next(){
    var currId = this.state.ID;
    this.setState({ID:String((parseInt(currId)+1)%500)});
  }

  toggleModal(){
    this.setState(prevState => ({
      modal: !prevState.modal
    }));
  }

  render() {
    return (
      <div className="Home">
        <Navbar color="dark" light expand="md" sticky="top">
          <NavbarBrand href="/cs498rkmp2"><h2 className="header">Animation World</h2></NavbarBrand>
        </Navbar>
        <div className="fix">
          <Nav tabs>
            <NavItem>
              <NavLink
                className={classnames({ active: this.state.activeTab === 'GalleryView' })}
                onClick={() => { this.toggle('GalleryView'); }}
              >
                Gallery View
              </NavLink>
            </NavItem>
            <NavItem>
              <NavLink
                className={classnames({ active: this.state.activeTab === 'ListView' })}
                onClick={() => { this.toggle('ListView'); }}
              >
                List View
              </NavLink>
            </NavItem>
          </Nav>
          </div>
          <TabContent activeTab={this.state.activeTab}>
            <TabPane tabId="GalleryView">
              <Container fluid>
                <DetailView data = {this.DetailPhaser()} ID = {this.state.ID} prev = {this.prev} next = {this.next} modal = {this.state.modal} toggleModal = {this.toggleModal}></DetailView>
                <GalleryView data = {this.GalleryPhaser()} set = {this.setselected} selected = {this.state.selected} setID = {this.setID} toggleModal = {this.toggleModal}></GalleryView>
              </Container>
            </TabPane>
            <TabPane tabId="ListView">
              <Container fluid>
                <ListView data = {this.ListPhaser()} setID = {this.setID} toggleModal = {this.toggleModal}></ListView>
              </Container>
            </TabPane>
          </TabContent>

      </div>
    );
  }
}

export default Home;
