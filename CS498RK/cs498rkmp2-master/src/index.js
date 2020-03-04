import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import './index.css';
import registerServiceWorker from './registerServiceWorker';
import Home from './Home.js';
import App from './App';

ReactDOM.render((
    <Router>
        <div>
            <Route exact path="/cs498rkmp2" component={App}/>
            <Route exact path="/cs498rkmp2/Home" component={Home}/>

        </div>
    </Router>
    ),
    document.getElementById('root')
);

registerServiceWorker();
