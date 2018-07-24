import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import FileUpload from './components/FileUpload';

class App extends Component {
  render() {
    return (
      <div>
        <FileUpload />
      </div>
    );
  }
}

export default App;
