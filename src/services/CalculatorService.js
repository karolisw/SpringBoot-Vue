import axios from 'axios'

const CALCULATOR_EXPRESSIONS_BASE_URL = 'http://localhost:8080/calculator/expressions'

class CalculatorService {
  getCalculations () {
    return axios.get(CALCULATOR_EXPRESSIONS_BASE_URL)
  }

  /**
   * Post will post a string expression and perform calculation + create MathExpression
   * @param equation
   * @returns {Promise<AxiosResponse<any>>}
   */
  postExpression (equation) {
    axios.post(CALCULATOR_EXPRESSIONS_BASE_URL, { equation: equation })
      .then((response) => {
        console.log('answer in calcService: ' + response.data.answer)
      },
      (error) => {
        console.log(error)
      })
  }
}

export default new CalculatorService()
