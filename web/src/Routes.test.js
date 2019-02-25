import React from 'react';
import { mount } from 'enzyme';
import { MemoryRouter } from 'react-router-dom';

import Routes from './Routes';
import Home from './containers/Home';
import Login from './containers/Login';
import SignUp from './containers/Signup';

const renderRoutes = path =>
  mount(
    <MemoryRouter initialEntries={[path]}>
      <Routes />
    </MemoryRouter>
  );

describe('#routes', () => {
  it('renders home page on initial route', () => {
    const component = renderRoutes('/');

    expect(component.find(Home)).toHaveLength(1);
  });
  it('renders Login page on initial route', () => {
    const component = renderRoutes('/login');

    expect(component.find(Login)).toHaveLength(1);
  });

  it('renders Signup page on initial route', () => {
    const component = renderRoutes('/signup');

    expect(component.find(SignUp)).toHaveLength(1);
  });
});
