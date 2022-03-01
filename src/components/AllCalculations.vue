<template>
  <div class="container">
    <h1 class="text-center"> Calculations list </h1>
    <table class="table table-striped">
      <thead>
      <tr>
        <th> Calculation ID </th>
        <th> Expression </th>
        <th> Answer </th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="calculation in calculations" :key="calculation.id">
        <td> {{ calculation.id }}</td>
        <td> {{ calculation.expression }}</td>
        <td> {{ calculation.answer }}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import CalculatorService from '../services/CalculatorService'
export default {
  name: 'AllCalculations',
  data () {
    return {
      calculations: []
    }
  },
  methods: {
    getCalculations () {
      CalculatorService.getCalculations().then((response) => {
        // We will push the response-data into our calculations-array upon mount of current component (see created() hook)
        this.calculations = response.data
      })
    }
  },
  created () { // lifecycle method will call getCalculations() upon component creation! :-)
    this.getCalculations()
  }
}
</script>

<style scoped>

</style>
