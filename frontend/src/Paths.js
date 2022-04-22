import React, { Component } from 'react'
import {
    Route,
    BrowserRouter as Router,
    Switch,
    Redirect,
} from "react-router-dom";

import Login from './Pages/Login/Login';
import Register from './Pages/Login/Register';
import Home from './Pages/Home/Home';
import HomeFaculty from './Pages/Home/HomeFaculty';

export default class Paths extends Component {
    constructor(props) {
        super(props);
        this.state = {
            "url": `http://localhost:9090/`
        }
    }
    render(){
        return (
            <Router>
                <Switch>
                    <Route path="/login">
                        <Login {...this}{...this.state}/>
                    </Route>

                    <Route path="/register">
                        <Register {...this}{...this.state}/>
                    </Route>

                    <Route path="/home">
                        <Home {...this}/>
                    </Route>

                    <Route path="/home_faculty">
                        <HomeFaculty {...this}/>
                    </Route>
                    
                    <Route exact path="/">
                        <Redirect to="/login" /> 
                    </Route>
                </Switch>
            </Router>
        )
    }
}