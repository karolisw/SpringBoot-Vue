<template>
  <div class="review-container">
    <h3>Messages:</h3>
    <ul>
      <li v-for="(message, index) in messages" :key="index">
        {{ message.name }} said:
        <br />
        "{{ message.userMessage }}"
      </li>
    </ul>
  </div>
</template>

<script setup>

// eslint-disable-next-line no-undef,no-unused-vars
const props = defineProps({
  messages: {
    type: Array,
    required: true
  }
})

</script>

<style scoped></style>
