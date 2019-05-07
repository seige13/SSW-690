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
  createHobby(bodyFormData) {
    return Request({
      url: '/hobby/addhobby',
      method: 'post',
      data: bodyFormData,
      config: {headers: {'Content-Type': `multipart/form-data;`}}
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
  },
  createEvent(bodyFormData) {
    return Request({
        method: 'post',
        url: '/events/addevents',
        data: bodyFormData,
        config: {headers: {'Content-Type': `multipart/form-data;`}}
      }
    )
  },
  getAllBlogsByHobbyId(id) {
    return Request({
      url: '/blog/listblog',
      method: 'get',
      params: {
        id: id
      }
    })
  },
  createBlog(title, content, hobbyId, userId) {
    return Request({
      url: '/blog/createblog',
      method: 'post',
      data: {
        title: title,
        content: content,
        hobbyId: hobbyId,
        userId: userId
      },
    })
  },
  getBlogById(id) {
    return Request({
      url: '/blog/findblogandcommentsbyid',
      method: 'get',
      params: {
        blogId: id
      }
    })
  },
  addComment(content, blogId, userId) {
    return Request({
      url: '/blog/addcomment',
      method: 'post',
      data: {
        content: content,
        blogId: blogId,
        userId: userId
      }
    })
  },
  getEventsById(id) {
    return Request({
      url: '/events/getevents',
      method: 'get',
      params: {
        events_id: id
      }
    })
  },
  getEventsUserJoinedByUserId(userId) {
    return Request({
      url: '/events/geteventsforuser',
      method: 'get',
      params: {
        id: userId
      }
    })
  },
  joinEvent(eventId, userId) {
    return Request({
      url: '/events/joinevents',
      method: 'post',
      params: {
        events_id: eventId,
        id: userId
      }
    })
  }
};

export default ApiService
