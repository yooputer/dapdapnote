<script setup>
import {defineProps, defineEmits, ref} from 'vue';

const props = defineProps({
  word: {
    type: Object,
    required: false
  },
  index: {
    type: Number,
    required: false
  }
});

const emits = defineEmits([
    'saveWord',
    'closeModal'
]);

const koreanWordInput = ref(props.word.korean || '');
const englishWordListInput = ref(JSON.parse(JSON.stringify(props.word.englishList || [''])));

function deleteWord(index){
  englishWordListInput.value.splice(index, 1);
}

function saveWord(){
  const ki = koreanWordInput.value;
  const eil = [...englishWordListInput.value];

  // 유효성 체크
  if (!ki || !ki.trim()){
    alert('내용을 입력해주세요');
    return;
  } else if (!eil || eil.length == 0){
    alert('내용을 입력해주세요');
    return;
  } else if(eil.some(v => (!v || !v.trim()))){
    alert('내용을 입력해주세요');
    return;
  }

  const newWord = {
    korean: ki,
    englishList: eil
  }

  emits('saveWord', newWord, props.index);
  emits('closeModal');
}
</script>

<template>
  <div class="modal-overlay">
    <div class="modal">
      <button class="close-button" @click="emits('closeModal')">×</button>
      <h2>어휘 {{ props.index ? '수정' : '추가' }}</h2>

      <div class="korean-section">
        <div class="label-section">
          <div class="section-label">한국어</div>
        </div>

        <input class="korean-input" v-model="koreanWordInput"/>
      </div>

      <div class="english-section">
        <div class="label-section">
          <div class="section-label">영어</div>
          <div class="add-row-btn" @click="englishWordListInput.push('')">+</div>
        </div>

        <div class="english-row" v-for="(english, index) in englishWordListInput" :key="index">
          <button class="delete-word-btn" @click="deleteWord(index)">×</button>
          <input v-model="englishWordListInput[index]"/>
        </div>
      </div>

      <div class="modal-actions">
        <button class="save-button" @click="saveWord">저장</button>
      </div>
    </div>
  </div>
</template>

<style scoped>
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

.add-row-btn {
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

.english-section{
  margin-top: 10px;
}

.english-row {
  display: flex;
  align-items: center;
  margin-bottom: 5px;
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
  margin: auto 5px;
}

.korean-input{
  width: 98%;
}

.english-row input{
  width: 100%;
}

.modal-actions .save-button{
  padding: 5px 10px; /* 여백 조정 */
  border: none;
  cursor: pointer;
  border-radius: 5px; /* 둥근 모서리 */
  margin-left: auto;
  font-size: 20px;
}
</style>