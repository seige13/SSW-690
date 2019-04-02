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
      method: 'post',
      data: {
        email: email,
        passWord: password
      },
    })
  },
  registerUser(username, password, email, firstname, lastname) {
    return Request({
      url: '/user/adduser',
      method: 'post',
      data: {
        nickName: username,
        passWord: password,
        email: email,
        firstName: firstname,
        lastName: lastname
      },
    })
  },
  createHobby(name, description, category) {
    return Request({
      url: '/hobby/addhobby',
      method: 'post',
      data: {
        name: name,
        description: description,
        classification: category,
      },
    })
  }
};

export default ApiService
