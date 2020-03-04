import React, { Component } from 'react';
import { Card, CardImg, CardText, CardBody, CardTitle, CardSubtitle, Button , CardColumns, Container, Row, Col} from 'reactstrap';
import './App.scss'
import './App.css';
import PropTypes from 'prop-types';

class Cards extends Component{
  render(){
    var datalist = this.props.data;
    var retlist = [];
    for (var data in datalist){
        retlist.push(<ACard data = {datalist[data]} setID = {this.props.setID}></ACard>)
    }
    return  retlist;
  }
};

class ACard extends Component{
  render(){
    var data = this.props.data;
    return (
      <Card>
        <CardImg top width="100%" src={data.Img} alt="404" />
        <CardBody>
          <CardTitle>{data.Name}</CardTitle>
          <CardSubtitle>{data.Types}</CardSubtitle>
          <CardText>{data.Body}</CardText>
          <Button onClick = {() => this.props.setID(data.ID)}>DetailView</Button>
        </CardBody>
      </Card>
    )
  }
};

class GalleryView extends Component {
  render() {
    return(
      <div className="GalleryView">
        <div className="nonscroll">
          <div className="Placeholder"><p></p></div>
          <Container>
            <div className = "buttoncontainer">
              <div className="filterbutton"><Button color="secondary" onClick={() => this.props.set(28)} active={this.props.selected.includes(28)}>Action</Button></div>
              <div className="filterbutton"><Button color="secondary" onClick={() => this.props.set(12)} active={this.props.selected.includes(12)}>Adventure</Button></div>
              <div className="filterbutton"><Button color="secondary" onClick={() => this.props.set(35)} active={this.props.selected.includes(35)}>Comedy</Button></div>
              <div className="filterbutton"><Button color="secondary" onClick={() => this.props.set(80)} active={this.props.selected.includes(80)}>Crime</Button></div>
              <div className="filterbutton"><Button color="secondary" onClick={() => this.props.set(18)} active={this.props.selected.includes(18)}>Drama</Button></div>
              <div className="filterbutton"><Button color="secondary" onClick={() => this.props.set(10751)} active={this.props.selected.includes(10751)}>Family</Button></div>
              <div className="filterbutton"><Button color="secondary" onClick={() => this.props.set(14)} active={this.props.selected.includes(14)}>Fantasy</Button></div>
              <div className="filterbutton"><Button color="secondary" onClick={() => this.props.set(27)} active={this.props.selected.includes(27)}>Horror</Button></div>
              <div className="filterbutton"><Button color="secondary" onClick={() => this.props.set(10402)} active={this.props.selected.includes(10402)}>Music</Button></div>
              <div className="filterbutton"><Button color="secondary" onClick={() => this.props.set(9648)} active={this.props.selected.includes(9648)}>Mystery</Button></div>
              <div className="filterbutton"><Button color="secondary" onClick={() => this.props.set(10749)} active={this.props.selected.includes(10749)}>Romance</Button></div>
              <div className="filterbutton"><Button color="secondary" onClick={() => this.props.set(878)} active={this.props.selected.includes(878)}>Science Fiction</Button></div>
              <div className="filterbutton"><Button color="secondary" onClick={() => this.props.set(10770)} active={this.props.selected.includes(10770)}>TV Movie</Button></div>
              <div className="filterbutton"><Button color="secondary" onClick={() => this.props.set(53)} active={this.props.selected.includes(53)}>Thriller</Button></div>
              <div className="filterbutton"><Button color="secondary" onClick={() => this.props.set(10752)} active={this.props.selected.includes(10752)}>War</Button></div>
              <div className="filterbutton"><Button color="secondary" onClick={() => this.props.set(37)} active={this.props.selected.includes(37)}>Western</Button></div>
              <div className="filterbutton"><Button color="link" onClick={() => this.props.set(-1)}>Clear</Button></div>
            </div>
          </Container>
          <hr/>
        </div>
        <div className="scroll">
          <Container>
            <CardColumns>
              <Cards data={this.props.data} setID = {this.props.setID}></Cards>
            </CardColumns>
          </Container>
        </div>
      </div>
    );
  }
};

GalleryView.propTypes = {
  data: PropTypes.array,
  set: PropTypes.func,
};

export default GalleryView;
