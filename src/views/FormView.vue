<template>
  <CalculatorForm @submit="addMessage"></CalculatorForm>
  <ReceivedForms :messages="messages"></ReceivedForms>
</template>

<script setup>

import { ref } from 'vue'
import CalculatorForm from '../components/CalculatorForm'
import ReceivedForms from '../components/ReceivedForms'

const messages = ref([])

function addMessage (message) {
  console.log(messages.value)
  messages.value.push(message)
  console.log(messages.value)
}

</script>

<style scoped>

</style>
