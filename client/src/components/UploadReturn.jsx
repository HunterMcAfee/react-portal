import React, { Component } from 'react';
import axios from 'axios';
import fileDownload from 'js-file-download';

class UploadReturn extends Component {
    constructor() {
        super();
        this.uploadInput = React.createRef();
        this.state = {

        }
    }

    _submitFiles = (event) => {
        event.preventDefault();
        const files = this.uploadInput.current.files;
        let formData = new FormData();
        for (let i = 0; i < files.length; i++) {
            formData.append(`files`, files[i]);
        }
        let config = {
            headers: {
                "Accept": "application/vnd.ms-excel"
            }
        }
        axios.post(`http://localhost:8080/file/return`, formData, config)
            .then((res) => {
                console.log(res);
                console.log(res.data);
                fileDownload(res.data, 'demo.csv')
            })
            .catch((error) => {
                console.log(error);
            })
    }

    render() {
        return (
            <div>
                <form onSubmit={this._submitFiles}>
                    <input ref={this.uploadInput} type="file" multiple />
                    <button type="submit">Submit</button>
                </form>
            </div>
        );
    }
}

export default UploadReturn;