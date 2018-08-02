import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import { Column, Table } from 'react-virtualized';
import 'react-virtualized/styles.css'; // only needs to be imported once

class ReactTable extends Component {
    constructor() {
        super();
        this.state = {
            list: [
                { name: 'Brian Vaughn', description: 'Software engineer' },
                { name: 'Brian Vaughn', description: 'Software engineer' },
                { name: 'Brian Vaughn', description: 'Software engineer' },
                { name: 'Brian Vaughn', description: 'Software engineer' },
                { name: 'Brian Vaughn', description: 'Software engineer' },
                { name: 'Brian Vaughn', description: 'Software engineer' },
                { name: 'Brian Vaughn', description: 'Software engineer' },
                { name: 'Brian Vaughn', description: 'Software engineer' }
            ],
            cart: []
        }
    }

    _changeCartStatus = ({ event: Event, index: number, rowData: any }) => {
        if (this.state.cart.includes(number)) {
            let newState = {...this.state};
            newState.cart.splice(this.state.cart.indexOf(number), 1);
            this.setState(newState);
        } else {
            let newState = {...this.state};
            newState.cart.push(number);
            this.setState(newState);
        }
    }

    render() {
        return (
            <div>
                <Table
                    width={window.innerWidth}
                    height={300}
                    headerHeight={20}
                    rowHeight={30}
                    onRowClick={this._changeCartStatus}
                    rowCount={this.state.list.length}
                    rowGetter={({ index }) => this.state.list[index]}
                >
                    <Column
                        label='Name'
                        dataKey='name'
                        width={200}
                    />
                    <Column
                        width={200}
                        label='Description'
                        dataKey='description'
                    />
                </Table>
            </div>
        );
    }
}

export default ReactTable;
