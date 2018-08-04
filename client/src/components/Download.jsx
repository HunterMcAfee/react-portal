import React, { Component } from 'react';
import axios from 'axios';
import fileDownload from 'js-file-download';

class Download extends Component {
    constructor(props) {
        super(props);
        this.state = {
            selection: ""
        }
    }

    _handleChange = (event) => {
        event.preventDefault();
        let newState = { ...this.state };
        newState.selection = event.target.value;
        this.setState(newState);
    }

    _getFile = (event) => {
        event.preventDefault();
        axios({
            method: 'get',
            url: `http://localhost:8080/${this.props.apiEndpoint}/${this.state.selection}`,
            responseType: 'arraybuffer'
            })
            .then((res) => {
                let json = JSON.stringify(res.data);
                console.log(res);
                let blob = new Blob([res.data], { type: `${this.props.format}` });
                let url = window.URL.createObjectURL(blob);
                window.open(url);
            })
            .catch((error) => {
                console.log(error);
            })
    }

    render() {
        return (
            <div>
                <form onSubmit={this._getFile}>
                    <input type="text" onChange={this._handleChange} placeholder={`Place ${this.props.fileType} File Id Here`} />
                    <button type="submit">Submit</button>
                </form>
            </div>
        );
    }
}

export default Download;