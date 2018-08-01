import React, { Component } from 'react';
import axios from 'axios';

class FileUpload extends Component {
    constructor(props) {
        super(props);
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
        axios.post(`http://localhost:8080/${this.props.apiEndpoint}`, formData)
            .then((res) => {
                console.log(res);
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

export default FileUpload;