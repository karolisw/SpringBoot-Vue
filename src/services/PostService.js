import axios from 'axios'

export default {
  index (params) {

  },

  create (data) {
    return axios.post('http://localhost:8080/calculator/expressions', { equation: data })
  }
}
