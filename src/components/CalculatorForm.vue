<template>
  <!-- the submit event will no longer reload the page -->
  <Form
    @submit="onSubmit"
    @invalid-submit="onInvalidSubmit"
    :validation-schema="validationRules">
    <h3>Leave a message!</h3>

    <div class="input-field">
      <Field name="name" type="text" placeholder="your name"/>
      <ErrorMessage name="name" />
    </div>

    <div class="input-field">
      <Field name="email" type="email" placeholder="your email"/>
      <ErrorMessage name="email" />
    </div>

    <div class="input-field">
      <Field name="userMessage" type="text" placeholder="your message"/>
      <ErrorMessage name="userMessage" />
    </div>

    <div>
      <button type="submit" >Submit</button>
      <button @click="resetForm()">Reset</button>
    </div>
  </Form>
</template>

<script setup>

import { onMounted } from 'vue'
// eslint-disable-next-line no-unused-vars
import * as yup from 'yup'
import { ErrorMessage, useForm, Form, Field } from 'vee-validate'

// eslint-disable-next-line no-unused-vars,no-undef
const emit = defineEmits(['submit'])
// eslint-disable-next-line no-unused-vars
const { isSubmitting, submitCount, setErrors } = useForm()

// eslint-disable-next-line no-unused-vars
const validationRules = yup.object({
  name: yup.string().required().min(2),
  email: yup.string().required().email(),
  userMessage: yup.string().required().min(10)
})

/**
 * Values are automatically passed in by vee-validate
 * @param values are the Field-components on the template (name, email, userMessage)
 */
function onSubmit (values, { resetForm }) {
  alert(JSON.stringify(values, null, 2))
  return new Promise(resolve => {
    window.alert('Sending message: ' + JSON.stringify(values.userMessage))
    setTimeout(() => {
      resolve(JSON.stringify(values, null, 2))
    }, 2000)
    emit('submit', values)
    resetForm()
  })
}

// eslint-disable-next-line no-unused-vars
function onInvalidSubmit ({ values, errors, results }) {
  console.log(values) // current form values
  console.log(errors) // a map of field names and their first error message
  console.log(results) // a detailed map of field names and their validation results
}

// lifecycle hooks
onMounted(() => {
  console.log('The application is now mounted.')
})

/**
 * Since with vee-validate you don't have to use v-model to track your values,
 * the useForm function allows you to define the starting values for your fields.
 * By default all fields start with undefined as a value.
 *
 * Using the initialValues option you can send an object that contains
 * the field names as keys and their values:
 */
useForm({
  validationSchema: validationRules
})
</script>

<style scoped>
.input-field {
  padding: 1em
}

</style>
