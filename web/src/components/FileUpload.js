import React, {Component} from 'react';
import Dropzone from 'react-dropzone';
import {FormLabel} from 'react-bootstrap';
import './FileUpload.css';

export default class FileUpload extends Component {
  constructor(props) {
    super(props);
    this.onDrop = (files) => {
      this.setState({files})
    };
    this.state = {
      files: []
    };
  }

  render() {
    const files = this.state.files.map(file => (
      <li key={file.name}>
        {file.name} - {(file.size / 100000).toFixed(2)} MB
      </li>
    ));

    return (
      <Dropzone onDrop={this.onDrop}>
        {({getRootProps, getInputProps}) => (
          <section className="">
            <div {...getRootProps({className: 'dropzone'})}>
              <input {...getInputProps()} />
              <p>Drag 'n' drop some files here, or click to select a file</p>
            </div>
            <aside>
              <FormLabel>Files</FormLabel>
              <ul>{files}</ul>
            </aside>
          </section>
        )}
      </Dropzone>
    );
  }
}
