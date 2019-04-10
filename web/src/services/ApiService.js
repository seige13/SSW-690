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
  },
  getAllHobbies() {
    return Request({
      url: '/hobby/listhobby',
      method: 'get'
    })
  },
  getHobbyById(id) {
    return Request({
      url: `/hobby/gethobby`,
      method: 'get',
      params: {
        id: id
      }
    })
  },
  getAllEvents() {
    return Request({
      url: '/events/listevents',
      method: 'get'
    })
  }
};

export default ApiService
