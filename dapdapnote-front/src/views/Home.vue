<template>
  <div class="chat-container">
    <div class="header">
      <div class="back-button">←</div>
      <div class="user-info">
        <div class="avatar">K</div>
        <div>
          <div class="user-name">나</div>
          <div class="status">오늘의 질문 : 23개 | 오늘의 답변 : 12개</div>
        </div>
      </div>
      <div class="menu-button">⋮</div>

    </div>

    <div class="chat-messages">
<!--      TODO 날짜구분선 구현-->
<!--      <div class="date-divider">-->
<!--        <span>2025년 3월 16일</span>-->
<!--      </div>-->

      <template v-for="question in questions" :key="question.seq">
        <div class="message-container sent-container">
          <div class="message sent">
            {{ question.content }}
          </div>
          <div class="message-time">오전 10:30</div>
        </div>

<!--        TODO 답변 달기 & 조회 구현-->
<!--        <div class="message-container received-container">-->
<!--          <div class="message received">-->
<!--            get scared-->
<!--          </div>-->
<!--          <div class="message-time">오전 10:30</div>-->
<!--        </div>-->
      </template>

    </div>

    <div class="chat-input">
      <button class="attachment-button">#</button>
      <input type="text" class="message-input" placeholder="잊기전에 빨리 적자" v-model="newQuestion">
      <button class="send-button" @click="addQuestion">+</button>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import api from "@/api/api";

const questions = ref([]);
const newQuestion = ref('');

onMounted(() => {
  fetchQuestions();
})

function addQuestion(){
  if (!newQuestion.value || !newQuestion.value.toString().trim()) {
    alert("내용을 입력해주세요. ");
    return;
  }

  api.post(`/api/question`, { content: newQuestion.value })
      .then(response => {
          newQuestion.value = '';
          fetchQuestions();
      })
      .catch(error => {
        console.log(error);
      });
}

function fetchQuestions(){
  api.get('/api/question/my')
    .then(response => {
      questions.value = response.data
      console.log(response)
    })
    .catch(error => {
      console.log(error);
    });
}
</script>

<style>
.chat-container {
  display: flex;
  flex-direction: column;
  height: 100vh; /* 변경: 전체 뷰포트 높이를 사용 */
  max-width: 480px;
  margin: 0 auto;
  width: 100%;
  background-color: white;
  box-shadow: 0 0 10px rgba(0,0,0,0.1);
  position: relative;
  overflow: hidden; /* 추가: 내부 스크롤을 위해 */
}

.header {
  background-color: #4e63d7;
  color: white;
  padding: 15px;
  display: flex;
  align-items: center;
  position: sticky;
  top: 0;
  z-index: 10;
}

.back-button {
  margin-right: 15px;
  font-size: 18px;
}

.user-info {
  display: flex;
  align-items: center;
}

.avatar {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  background-color: #ddd;
  margin-right: 10px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-weight: bold;
  color: #555;
}

.user-name {
  font-weight: bold;
  font-size: 16px;
}

.status {
  font-size: 12px;
  opacity: 0.8;
}

.menu-button {
  position: absolute;
  right: 15px;
  font-size: 18px;
}

.chat-messages {
  flex: 1;
  overflow-y: auto;
  padding: 15px;
  display: flex;
  flex-direction: column;
  background-color: #f8f8f8;
  padding-bottom: 65px;
}

.message-container {
  display: flex;
  flex-direction: column;
  margin-bottom: 16px;
  max-width: 80%;
}

.received-container {
  align-self: flex-start;
}

.sent-container {
  align-self: flex-end;
}

.message {
  padding: 12px 16px;
  border-radius: 20px;
  position: relative;
  box-shadow: 0 1px 2px rgba(0,0,0,0.1);
  font-size: 15px;
  line-height: 1.4;
}

.received {
  background-color: #ffffff;
  color: #333;
  border: 1px solid #e0e0e0;
  border-bottom-left-radius: 4px;
}

.sent {
  background-color: #4e63d7;
  color: white;
  border-bottom-right-radius: 4px;
}

.message-time {
  font-size: 11px;
  margin-top: 4px;
  color: #888;
}

.received-container .message-time {
  margin-left: 12px;
}

.sent-container .message-time {
  margin-right: 12px;
  text-align: right;
}

.chat-input {
  display: flex;
  padding: 12px;
  border-top: 1px solid #eee;
  background-color: white;
  align-items: center;
  position: absolute;
  bottom: 0;
  left: 0;
  right: 0;
  z-index: 10;
  max-width: 480px;
  margin: 0 auto;
}

.attachment-button {
  background: none;
  border: none;
  font-size: 22px;
  color: #555;
  cursor: pointer;
  padding: 0 10px;
}

.message-input {
  flex: 1;
  border: 1px solid #ddd;
  border-radius: 20px;
  padding: 10px 15px;
  outline: none;
  margin: 0 10px;
  font-size: 15px;
}

.send-button {
  background-color: #4e63d7;
  color: white;
  border: none;
  border-radius: 50%;
  width: 40px;
  height: 40px;
  font-size: 18px;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
}

.date-divider {
  text-align: center;
  margin: 20px 0;
  position: relative;
}

.date-divider span {
  background-color: #f8f8f8;
  padding: 0 10px;
  font-size: 12px;
  color: #888;
  position: relative;
  z-index: 1;
}

.date-divider:before {
  content: "";
  position: absolute;
  top: 50%;
  left: 0;
  right: 0;
  height: 1px;
  background-color: #e0e0e0;
  z-index: 0;
}
</style>