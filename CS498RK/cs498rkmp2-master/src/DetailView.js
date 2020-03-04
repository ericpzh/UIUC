import React, { Component } from 'react';
import { Button, Modal, ModalHeader, ModalBody, ModalFooter, Container, Badge, Media, Row, Col } from 'reactstrap';
import './App.scss'
import './App.css';
import PropTypes from 'prop-types';

class DetailView extends Component {
  constructor(props) {
    super(props);
    this.state = {
      moviesDetails: [{ID:"0",Name:"",Img:"",Genres:"",Popularity:"0",Release_date:"",Vote_average:"0",Vote_count:"0",Overview:""}],
      set: false,
    };
  }

  render() {
    if (typeof this.props.data !== "undefined" && this.props.data.length === 500 && this.state.set === false){
      this.setState({moviesDetails:this.props.data,set:true});
    }
    var data = this.state.moviesDetails[this.props.ID];
    return (
      <div className="DetailViewView">
        <Modal isOpen={this.props.modal} toggle={this.props.toggleModal} className={this.props.className} size='lg'>
          <ModalHeader toggle={this.props.toggleModal}>{data['Name']}</ModalHeader>
          <ModalBody>
            <Media>
              <Media left href="#">
                <Media object src={data['Img']} alt="404" />
              </Media>
              <Media body>
                <Container>
                  <Media heading>
                  Score: {data['Vote_average']} <Badge color="secondary">{data['Vote_count']} Reviews</Badge>
                  </Media>
                  <p>Genres: {data['Genres']}</p>
                  <p>Release Date: {data['Release_date']}</p>
                  <p>Popularity: {data['Popularity']}</p>
                  <div className="detailscroll">
                    <p>{data['Overview']}</p>
                  </div>
                </Container>
              </Media>
            </Media>
          </ModalBody>
          <ModalFooter>
            <Button color="secondary" onClick={() => this.props.prev()}>Prev</Button>
            <Button color="secondary" onClick={() => this.props.next()}>Next</Button>
          </ModalFooter>
        </Modal>
      </div>
    );
  }
};

DetailView.propTypes = {
  data: PropTypes.array,
  prev: PropTypes.func,
  next: PropTypes.func,
  toggleModal: PropTypes.func,
};

export default DetailView;
