import React, {Component} from 'react';
import {FormGroup, FormControl, FormLabel, Alert} from 'react-bootstrap';
import ApiService from '../services/ApiService'
import FileUpload from '../components/FileUpload';
import LoaderButton from '../components/LoaderButton';
import 'react-datepicker/dist/react-datepicker.css';
import DatePicker from 'react-datepicker';
import './AddEvent.css';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';

export default class AddHobby extends Component {
  constructor(props) {
    super(props);

    this.state = {
      isLoading: true,
      hobby: null,
      event: {
        eventsTitle: '',
        eventsTime: new Date(),
        location: '',
        description: '',
        fee: '',
        holder: '',
        hobby_id: this.props.match.params.id,
      },
      picture: '',
      hasErrors: false,
      errorMessage: ''
    };
  }

  // @TODO add form validation
  validateForm() {
    return (
      true
    );
  }

  getHobby = () => {
    ApiService.getHobbyById(this.props.match.params.id)
      .then(function (response) {
        this.setState({
          hobby: response.hobby,
          isLoading: false
        })
      }.bind(this))
      .catch(function (error) {
        console.log(error.statusText);

        this.setState({
          hasErrors: true,
          isLoading: false,
          errorMessage: 'There has been an error processing your request.',
        });
      }.bind(this));
  };

  componentDidMount() {
    this.getHobby();
  }

  handleChange = event => {
    this.setState({ event: { ...this.state.event, [event.target.id]: event.target.value} });
  };

  handleDateChange = date => {
    this.setState({ event: { ...this.state.event, eventsTime: date} });
  };

  onUploadChange = event => {
    this.setState({
      picture: event.files[0]
    });
  };

  handleSubmit = async event => {
    event.preventDefault();

    this.setState({isLoading: true});

    let bodyFormData = new FormData();

    let currEvent = new Blob([JSON.stringify(this.state.event)], {type : 'application/json'});

    bodyFormData.append('events', currEvent);
    bodyFormData.append('file', this.state.picture);

    ApiService.createEvent(bodyFormData)
      .then(function (response) {
        console.log(response);
        if (response) {
          this.props.history.push(`/hobby/${this.props.match.params.id}`);
        } else {
          this.setState({
            hasErrors: true,
            isLoading: false,
            errorMessage: 'There has been an error processing your request.',
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
  };

  renderForm() {
    return (
      <div>
        <h2 className={'text-center mb-4'}>Add Your Event to {this.state.hobby.name} Community!</h2>
        <form onSubmit={this.handleSubmit}>
          <FormGroup controlId='eventsTitle'>
            <FormLabel>Event Title</FormLabel>
            <FormControl
              autoFocus
              type='text'
              value={this.state.event.eventsTitle}
              onChange={this.handleChange}
            />
          </FormGroup>
          <FormGroup controlId='description'>
            <FormLabel>Event Description</FormLabel>
            <FormControl
              autoFocus
              as={'textarea'}
              type='text'
              value={this.state.event.description}
              onChange={this.handleChange}
              className={'category-select'}
            />
          </FormGroup>

          <FormGroup controlId='location'>
            <FormLabel>Event Date</FormLabel>
            <DatePicker
              selected={this.state.event.eventsTime}
              onChange={this.handleDateChange}
              showTimeSelect
              timeFormat='HH:mm'
              timeIntervals={15}
              dateFormat='MMMM d, yyyy h:mm aa'
              timeCaption='time'
              minDate={new Date()}
              dropdownMode='select'
            />
          </FormGroup>

          <FormGroup controlId='location'>
            <FormLabel>Event Location</FormLabel>
            <FormControl
              autoFocus
              type='text'
              value={this.state.event.location}
              onChange={this.handleChange}
            />
          </FormGroup>

          <FormGroup controlId='holder'>
            <FormLabel>Event Host</FormLabel>
            <FormControl
              autoFocus
              type='text'
              value={this.state.event.holder}
              onChange={this.handleChange}
            />
          </FormGroup>

          <FormGroup controlId='fee'>
            <FormLabel>Event Fee</FormLabel>
            <FormControl
              autoFocus
              type='number'
              value={this.state.event.fee}
              onChange={this.handleChange}
            />
          </FormGroup>

          <FormGroup controlId='fee'>
            <FormLabel>Event Image</FormLabel>
            <FileUpload value={this.state.picture} onChange={this.onUploadChange}/>
          </FormGroup>

          <LoaderButton
            block
            disabled={!this.validateForm()}
            type='submit'
            isLoading={this.state.isLoading}
            text='Create Event'
            loadingText='Creating Event...'
          />
        </form>
      </div>
    );
  }

  render() {
    if (this.state.hasErrors) {
      var alertMessage = <Alert variant={'danger'} onClose={this.onAlertClose} className={'mb-3'}
                                dismissible>{this.state.errorMessage}</Alert>
    }

    return (
      <div className='AddEvent'>
        {this.state.isLoading ? (
          <FontAwesomeIcon icon='sync' className='fa-spin spinning'/>) : (
          this.state.hasErrors ? (alertMessage) :
            this.renderForm()
        )}
      </div>
    );
  }
}
