import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import FileUpload from './components/FileUpload';
import UploadReturn from './components/UploadReturn';

class App extends Component {
  render() {
    return (
      <div>
        <h2>EXCEL</h2>
        <h6>Upload</h6>
        <FileUpload apiEndpoint={"excel/read"} />
        <p>----------------------</p>
        <h6>Upload and Return</h6>
        <UploadReturn apiEndpoint={"excel/return"} format={"application/vnd.ms-excel"} />
        <p>----------------------</p>
        <h2>PDF</h2>
        <h6>Upload</h6>
        <UploadReturn apiEndpoint={"pdf/merge"} format={"application/pdf"} />
      </div>
    );
  }
}

export default App;
