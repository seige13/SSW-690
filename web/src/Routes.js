import React from 'react';
import {Route, Switch} from 'react-router-dom';
import AuthenticatedRoute from "./components/AuthenticatedRoute";
import UnauthenticatedRoute from "./components/UnauthenticatedRoute";
import AppliedRoute from './components/AppliedRoute';
import NotFound from './containers/NotFound';
import Signup from './containers/Signup';
import Home from './containers/Home';
import Login from './containers/Login';
import ForgotPassword from "./containers/ForgotPassword";
import AddHobby from './containers/AddHobby';

export default ({childProps}) => (
  <Switch>
    <AppliedRoute path="/" exact component={Home} props={childProps}/>
    <UnauthenticatedRoute path="/login" exact component={Login} props={childProps}/>
    <UnauthenticatedRoute path="/signup" exact component={Signup} props={childProps}/>
    <UnauthenticatedRoute path="/forgot-password" exact component={ForgotPassword} props={childProps}/>
    <AuthenticatedRoute path="/hobby/add" exact component={AddHobby} props={childProps}/>
    {/* Finally, catch all unmatched routes */}
    <Route component={NotFound}/>
  </Switch>
);
