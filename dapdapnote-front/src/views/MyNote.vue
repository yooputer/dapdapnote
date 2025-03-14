<template>
  <div>
    <h1>My Note</h1>
    <p> note cnt : {{ noteCnt }}</p>

    <div v-for="ddNote in noteList" :key="ddNote.seq">
      <b> {{ ddNote }} </b>
    </div>
  </div>
</template>

<script setup>
import {computed, onMounted, ref} from "vue";
import api from "@/api/api";

const noteList = ref([]);
const noteCnt = computed(() => noteList.value.length)

onMounted(() => {
  api.get(`/api/ddnote/my`)
      .then(response => {
        noteList.value = response.data;
      })
      .catch(error => {
        console.log(error);
      });
})

</script>