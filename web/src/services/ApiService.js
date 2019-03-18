import Request from './Request'

const ApiService = {
  /**
   * Function to login user
   *
   * @param email {string}
   * @param password {string}
   * @return {*}
   */
  userLogin(email, password) {
    return Request({
      url: '/user/login',
      method: 'POST',
      body: {
        email: email,
        password: password
      }
    })
  }
};

export default ApiService
