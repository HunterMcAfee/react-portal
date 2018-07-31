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
            responseType: 'arraybuffer'
        }
        axios.post(`http://localhost:8080/file/return`, formData, config)
            .then((res) => {
                // fileDownload(res.data, 'demo.xls')
                let json = JSON.stringify(res.data);
                console.log(res);
                let blob = new Blob([res.data],{ type: "application/vnd.ms-excel" });
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
                <form onSubmit={this._submitFiles}>
                    <input ref={this.uploadInput} type="file" multiple />
                    <button type="submit">Submit</button>
                </form>
            </div>
        );
    }
}

export default UploadReturn;