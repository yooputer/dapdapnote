<script setup>
import {ref} from "vue";
import EditWordModal from "@/components/EditWordModal.vue";

const showModal = ref(false);
const modalType = ref('');
const koreanInput = ref('최근에 운전면허를 땄어. \n차를 살 생각이 전혀 없었는데, \n운전면허를 따니까 차를 사고 싶어지더라. \n그래서 중고차 가격을 알아보는 중이야');
const englishInput = ref('');
const wordList = ref([
    {korean: '최근', englishList: ['Recently', 'Nowadays']},
    {korean: '운전면허', englishList: ['Driver\'s License']},
    {korean: '따다', englishList: ['optain', 'get']},
    {korean: '전혀 ', englishList: ['at all', 'completely']},
    {korean: '중고차 ', englishList: ['at all', 'completely']},
]);
const selectedWordIndex = ref(null);

function saveWord(word, index){
  if (!index) {
    wordList.value.push(word);
  }else{
    wordList.value[index] = word;
  }
}

function deleteWord(){
  wordList.value.splice(selectedWordIndex.value, 1);
  selectedWordIndex.value = null;
}

function openEditWordModal(isNew = false){
  if (isNew) {
    selectedWordIndex.value = null;
  }
  modalType.value = 'editWord';
  showModal.value = true;
}
</script>

<template>
  <EditWordModal
      v-if="showModal && modalType === 'editWord'"
      :word="selectedWordIndex ? wordList[selectedWordIndex] : {}"
      :index="selectedWordIndex"
      @saveWord="saveWord"
      @closeModal="showModal = false; modalType = ''"
  />

  <div class="app-header">
    영어작문 마스터
  </div>

  <div class="korean-section">
    <div class="label-section">
      <div class="section-label">내가 표현하고 싶은 문장(한국어)</div>
    </div>
    <textarea class="korean-textarea" v-model="koreanInput"/>
  </div>

  <div class="options-section">
    <div class="label-section">
      <div class="section-label">문장에 필요한 어휘</div>
      <div class="add-word-btn" @click="openEditWordModal(true)">+</div>
<!--        <div class="ai-word-btn">AI 어휘 추천</div>--> <!-- TODO AI 어휘 추천 기능 구현 -->
    </div>

    <div class="words">
      <div class="word-item" v-for="(word, index) in wordList" :key="word.korean" @click="selectedWordIndex = index">
        {{ word.korean }}
      </div>
    </div>

    <div class="option-item" v-if="selectedWordIndex != null">
      <button class="delete-word-btn" @click="selectedWordIndex = null">×</button>
      <div class="option-info">
        <div class="option-text">{{ wordList[selectedWordIndex].korean }}</div>
        <div class="option-english" v-for="english in wordList[selectedWordIndex].englishList" :key="english">- {{ english }}</div>
      </div>
      <div class="option-actions">
        <button class="option-btn delete-btn" @click="deleteWord">삭제</button>
        <button class="option-btn edit-btn" @click="openEditWordModal">수정</button>
      </div>
    </div>
  </div>
  <div class="result-section">
    <div class="label-section">
      <div class="section-label">내가 표현한 문장(영어)</div>
    </div>
    <textarea class="english-textarea" v-model="englishInput"/>
  </div>

  <div class="buttons-container">
    <select class="status-select">
      <option value="unsolved">미해결</option>
      <option value="solved">해결</option>
    </select>
    <button class="btn btn-save">저장</button>
  </div>
</template>

<style scoped>

.app-header {
  background: linear-gradient(135deg, var(--primary-color), var(--secondary-color));
  color: white;
  padding: 20px;
  text-align: center;
  font-size: 1.2rem;
  font-weight: bold;
  border-radius: var(--border-radius) var(--border-radius) 0 0;
}

.korean-section {
  padding: 20px;
  border-bottom: 1px solid #e0e0e0;
}

.label-section {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}

.section-label {
  font-size: 1rem;
  color: #555;
  font-weight: 500;
}

textarea {
  width: 100%;
  min-height: 120px;
  padding: 15px;
  border-radius: 12px;
  border: 1px solid #e0e0e0;
  resize: vertical;
  font-size: 0.95rem;
  line-height: 1.5;
  transition: border-color 0.3s ease;
}

textarea:focus {
  outline: none;
  border-color: var(--primary-color);
  box-shadow: 0 0 0 2px rgba(58, 134, 255, 0.2);
}

.korean-textarea {
  background-color: #f8f9fa;
  overflow: hidden;
}

.english-textarea {
  background-color: #e3f2fd;
  overflow: hidden;
}

.options-section {
  padding: 20px;
  background-color: #f9f9f9;
}

.section-title {
  font-size: 0.9rem;
  color: #666;
  margin-bottom: 15px;
  display: flex;
  align-items: center;
}

.section-title::after {
  content: '';
  flex-grow: 1;
  height: 1px;
  background-color: #e0e0e0;
  margin-left: 10px;
}

.option-item {
  margin-bottom: 12px;
  display: block;
  align-items: center;
  justify-content: space-between;
  padding: 10px 15px;
  border-radius: var(--border-radius);
  border: 1px solid #e0e0e0;
  background-color: white;
  margin-top: 15px;
  position: relative;
}

.option-info {
  flex-direction: column;
  margin-left: 15px;
  margin-right: auto;
}

.option-text {
  font-size: 0.95rem;
}

.option-english {
  font-size: 0.8rem;
  color: #666;
  margin-top: 3px;
}

.add-word-btn {
  background-color: #2da562;
  color: rgba(255, 255, 255, 0.81);
  border: none;
  width: 26px;
  height: 26px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  font-size: 20px;
  line-height: 1;
  font-weight: bold;
  margin-left: 5px;
}

.ai-word-btn{
  border: 1px solid;
  background-color: white;
  border-radius: 5px;
  padding: 3px 5px;
  font-size: 15px;
  border-right: 5px;
  margin-left: auto;
  margin-right: 5px;
  cursor: pointer;
}

.delete-word-btn {
  background-color: #efefef;
  color: rgba(92, 92, 92, 0.81);
  border: none;
  width: 20px;
  height: 20px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  transition: background-color 0.3s ease;
  font-size: 12px;
  line-height: 1;

  position: absolute;
  right: -9px;
  top: -9px;
}

.result-section {
  padding: 20px;
  border-top: 1px solid #e0e0e0;
}

.buttons-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 15px 20px 20px;
}

.status-select {
  padding: 10px 15px;
  border-radius: var(--border-radius);
  border: 1px solid #e0e0e0;
  background-color: white;
  font-size: 0.95rem;
  cursor: pointer;
  transition: border-color 0.3s ease;
}

.status-select:focus {
  outline: none;
  border-color: var(--primary-color);
}

.btn {
  padding: 12px 25px;
  border: none;
  border-radius: var(--border-radius);
  font-weight: 500;
  cursor: pointer;
  transition: all 0.3s ease;
  font-size: 1rem;
}

.btn-save {
  background-color: var(--primary-color);
  color: white;
}

.words {
  display: flex;
  flex-wrap: wrap;
  gap: 5px;
}

.word-item {
  background-color: #f0f0f0; /* 배경색 */
  border-radius: 20px; /* 둥근 모서리 */
  padding: 4px 8px; /* 여백 조정 */
  font-size: 16px; /* 글자 크기 */
  display: flex; /* Flexbox를 사용하여 수평 정렬 */
  align-items: center; /* 수직으로 중앙 정렬 */
  cursor: pointer;
}

.option-actions{
  display: flex;
  align-items: center;
  margin-top: 5px;
  margin-bottom: 5px;
}

.option-btn{
  padding: 3px 6px; /* 여백 조정 */
  border: none;
  cursor: pointer;
  border-radius: 5px; /* 둥근 모서리 */
}

.option-btn.edit-btn{
  margin-left: 10px;
}
.option-btn.delete-btn{
  margin-left: auto;
  color: darkred;
}
</style>