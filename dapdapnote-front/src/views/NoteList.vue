<script setup>
import {computed, onMounted, ref} from "vue";
import router from "@/router";
import api from "@/api/api";

const noteList = ref([]);
const searchType = ref('KOREAN');
const searchKeyword = ref('');
const filter = ref({ onlyUnsolved: false, onlyToday: false });
const isAll = computed(() => Object.values(filter.value).every(value => value === false));

onMounted(() => {
  fetchNoteList();
})

function fetchNoteList(){
  const onlyUnsolved = filter.value.onlyUnsolved;
  const onlyToday = filter.value.onlyToday;

  let url = `/api/note/list?onlyUnsolved=${onlyUnsolved}&onlyToday=${onlyToday}`;
  if (searchKeyword.value) {
    url += `&searchType=${searchType.value}&searchKeyword=${searchKeyword.value}`;
  }

  api.get(url)
      .then(response => {
        if (response.status == 200){
          noteList.value = response.data;
        }
      })
      .catch(error => {
        console.log(error);
        alert("api 오류");
      });
}

function goToDetail(noteSeq){
  router.push({ path: '/note/edit/'+noteSeq });
}

function setFilter(filterType){
  if (filterType == 'all') {
    Object.entries(filter.value).forEach(([key, value]) => {
      filter.value[key] = false;
    });
  }else{
    filter.value[filterType] = !filter.value[filterType];
  }

  fetchNoteList();
}

</script>

<template>
  <header>
    <div class="header-content">
      <h1></h1>

    </div>
    <div class="search-bar">
      <select class="search-select" v-model="searchType">
        <option value="KOREAN">한국어</option>
        <option value="ENGLISH">영어</option>
      </select>
      <input type="text" placeholder="검색어를 입력하세요" v-model="searchKeyword">
      <span class="search-icon" @click="fetchNoteList">🔍</span>
    </div>

    <div class="category-tabs">
      <div class="category-tab" :class="isAll ? 'active' : ''" @click="setFilter('all')">전체</div>
      <div class="category-tab" :class="filter.onlyUnsolved ? 'active': ''" @click="setFilter('onlyUnsolved')">미해결</div>
      <div class="category-tab" :class="filter.onlyToday ? 'active': ''" @click="setFilter('onlyToday')">TODAY</div>
    </div>
  </header>

  <div class="board-list">
    <div class="board-row" v-for="note in noteList" :key="note.seq">
      <div class="post-title-icon">{{ note.status == 'SOLVED' ? '✅' : '❎' }}</div>
      <div class="board-item">
        <div class="post-title" @click="goToDetail(note.seq)">
          {{ note.korean }}
        </div>

        <div class="post-info">
          <div class="post-comments">어휘 {{ note.expressionCnt }}</div>
          <div class="post-date">{{ note.regDateTimeStr }}</div>
        </div>
      </div>
    </div>
  </div>


  <router-link class="create-link" to="/note/create">+</router-link>
</template>

<style scoped>
header {
  background-color: #3b7edd;
  color: white;
  padding: 1rem;
  position: sticky;
  top: 0;
  z-index: 100;
  box-shadow: 0 2px 5px rgba(0,0,0,0.1);
}

.header-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

h1 {
  font-size: 1.2rem;
  font-weight: 600;
}

.search-bar {
  background-color: white;
  border-radius: 20px;
  margin: 0.5rem 0;
  padding: 0.5rem 1rem;
  display: flex;
  align-items: center;
}

.search-bar select {
  padding: 5px;
  border-radius: 10px;
  border: 1px solid #e0e0e0;
  background-color: white;
  font-size: 0.8rem;
  cursor: pointer;
}
.search-bar input {
  width: 100%;
  border: none;
  outline: none;
  padding: 0.3rem;
  font-size: 0.9rem;
}

.search-icon {
  color: #999;
  margin-right: 0.5rem;
  cursor: pointer;
}

.board-list{
  overflow-y: scroll;
  height: 100%;
}

.board-row {
  background-color: white;
  border-radius: 10px;
  margin: 1rem;
  overflow: hidden;
  box-shadow: 0 1px 3px rgba(0,0,0,0.1);
  display: flex;
}

.board-item {
  padding: 0.5rem;
  border-bottom: 1px solid #eee;
  width: 90%;
}

.board-item:last-child {
  border-bottom: none;
}

.post-title {
  font-size: 1.2rem;
  font-weight: 500;
  margin-bottom: 0.3rem;
  color: #222;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  display: block;
  align-items: center;
  width: 100%;
  cursor: pointer;
}

.post-title-icon {
  margin-right: 0.1rem;
  font-size: 1.8rem;
  display: flex;
  align-items: center;
}

.post-title-content{
  width: 90%;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.post-meta {
  display: flex;
  justify-content: space-between;
  font-size: 0.8rem;
  color: #888;
}

.post-info {
  display: flex;
  margin-left: auto;
  align-items: center;
  justify-content: space-between;
}

.post-date {
  margin-right: 0.5rem;
  font-size: 0.8rem;
  color: #888;
}

.post-comments {
  color: #3b7edd;
  font-weight: bold;
  font-size: 0.9rem;
}

.create-link {
  position: fixed;
  bottom: 5rem;
  right: 1.3rem;
  width: 3.5rem;
  height: 3.5rem;
  background-color: #3b7edd;
  color: white;
  border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
  box-shadow: 0 2px 10px rgba(0,0,0,0.2);
  font-size: 2rem;
  text-decoration: none;
}

.category-tabs {
  display: flex;
  overflow-x: auto;
  padding-top: 5px;
  -webkit-overflow-scrolling: touch;
  scrollbar-width: none;
}

.category-tabs::-webkit-scrollbar {
  display: none;
}

.category-tab {
  white-space: nowrap;
  padding: 0.5rem 1rem;
  margin-right: 0.5rem;
  border-radius: 20px;
  font-size: 0.9rem;
  background-color: #f0f0f0;
  color: #666;
}

.category-tab.active {
  background-color: #1b538a;
  color: white;
  font-weight: bold;
}
</style>