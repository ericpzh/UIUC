import React, { Component } from 'react';
import {BootstrapTable, TableHeaderColumn} from 'react-bootstrap-table';
import 'react-bootstrap-table/dist/react-bootstrap-table-all.min.css';
import './App.scss'
import './App.css';
import PropTypes from 'prop-types';

function dateFormatter(cell, row) {
  return `${('0' + cell.getDate()).slice(-2)}/${('0' + (cell.getMonth() + 1)).slice(-2)}/${cell.getFullYear()}`;
}

class ListView extends Component {
  render() {
    var data = this.props.data;
    var options = {
      defaultSortName: 'Vote_average',
      defaultSortOrder: 'desc',
      onRowClick: (row) => {
        this.props.setID(String(row.ID));
      },
    };
    return (
      <div className="ListView">
        <div className="Placeholder"><p>*Click on the headers to toggle sorting. Click on the row to view details.</p></div>
        <BootstrapTable data={data} options={ options }>
            <TableHeaderColumn dataField='ID' hidden> ID </TableHeaderColumn>
            <TableHeaderColumn dataField='Name' isKey dataSort filter={ { type: 'TextFilter', delay: 100 } } tdStyle={ { whiteSpace: 'normal' } } thStyle={ { whiteSpace: 'normal' } }> Name </TableHeaderColumn>
            <TableHeaderColumn dataField='Vote_average' dataSort filter={ { type: 'NumberFilter', delay: 100, numberComparators: ['>','=','<'],defaultValue: { number: 0, comparator: '>=' } }} tdStyle={ { whiteSpace: 'normal' } } thStyle={ { whiteSpace: 'normal' } }>Score</TableHeaderColumn>
            <TableHeaderColumn dataField='Popularity' dataSort filter={ { type: 'NumberFilter', delay: 100, numberComparators: ['>','=','<'],defaultValue: { number: 0, comparator: '>=' }}} tdStyle={ { whiteSpace: 'normal' } } thStyle={ { whiteSpace: 'normal' } }>Popularity</TableHeaderColumn>
            <TableHeaderColumn dataField='Release_date' dataSort dataFormat={ dateFormatter } tdStyle={ { whiteSpace: 'normal' } } thStyle={ { whiteSpace: 'normal' } } >Release Date</TableHeaderColumn>
        </BootstrapTable>
      </div>
    );
  }
};

ListView.propTypes = {
  data: PropTypes.array,
  setID: PropTypes.func,
};

export default ListView;
