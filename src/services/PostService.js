import axios from 'axios'

export default {
  create (expressionFromCalculator) {
    return axios.post('http://localhost:8080/calculator/expressions', { equation: expressionFromCalculator })
  }
}
