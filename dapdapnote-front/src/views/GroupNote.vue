<template>
  <div>
    <h1>Group Note</h1>
    <p>seq : {{ groupSeq }}</p>
  </div>
</template>

<script setup>
import { useRoute } from "vue-router";
import { computed } from "vue";

const route = useRoute();
const groupSeq = computed(() => route.params.groupSeq);
</script>