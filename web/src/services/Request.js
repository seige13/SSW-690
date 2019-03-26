import axios from 'axios'

/**
 * Initialize an Axios Client
 */
const client = axios.create({
  baseURL: process.env.REACT_APP_API_URL || 'http://hobbymatcher.com'
});

/**
 * Request wrapper--handles success and error conditions
 */
const Request = options => {
  const onSuccess = response => {
    return response.data
  };

  const onError = error => {
    if (error.response) {
      console.error('Status:', error.response.status)
    } else {
      console.error(error.message)
    }
    return Promise.reject(error.response || error.message)
  };

  return client(options)
    .then(onSuccess)
    .catch(onError)
};

export default Request
