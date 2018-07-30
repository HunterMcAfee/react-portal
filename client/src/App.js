import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import FileUpload from './components/FileUpload';
import UploadReturn from './components/UploadReturn';

class App extends Component {
  render() {
    return (
      <div>
        <FileUpload />
        <p>----------------------</p>
        <UploadReturn />
      </div>
    );
  }
}

export default App;
