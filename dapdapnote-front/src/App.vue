<template>
  <h2>{{ str }}</h2>
</template>

<script setup>
import { ref } from 'vue'
import api from "@/api/api";

const str = ref('');

api.get(`/api/`)
    .then(response => {
      console.log(response)
      str.value = response.data;
    })
    .catch(error => {
      console.log(error)
      str.value = 'api 오류';
    });
</script>