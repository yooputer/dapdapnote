<script setup>

</script>

<template>
  <div class="app-container">
    <div class="app-header">
      영어작문 마스터
    </div>

    <div class="korean-section">
      <div class="section-label">내가 표현하고 싶은 문장</div>
      <textarea class="korean-textarea">최근에 운전면허를 땄어.
차를 살 실력이 전혀 없었는데,
운전면허를 따니까 차를 사고 싶어지더라.
그래서 중고차 가격을 알아보는 중이야.</textarea>
    </div>

    <div class="options-section">
      <div class="section-label">문장에 필요한 어휘</div>
      <ul class="option-list">
        <li class="option-item">
          <button class="delete-btn">×</button>
          <div class="option-info">
            <div class="option-text">최근</div>
            <div class="option-english">- Recently</div>
            <div class="option-english">- Nowadays</div>
          </div>
        </li>

      </ul>
    </div>

    <div class="result-section">
      <div class="section-label">내가 표현한 문장</div>
      <textarea class="english-textarea">Recently, I got a Driver's License.
I have no idea about buying a car at all but I am feeling like wanting car.
So I'm finding out used car.</textarea>
    </div>

    <div class="buttons-container">
      <select class="status-select">
        <option value="unsolved">미해결</option>
        <option value="solved">해결</option>
      </select>
      <button class="btn btn-save">저장</button>
    </div>
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

.section-label {
  font-size: 0.95rem;
  color: #555;
  margin-bottom: 10px;
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

.option-list {
  list-style-type: none;
}

.option-item {
  margin-bottom: 12px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 10px 15px;
  border-radius: var(--border-radius);
  border: 1px solid #e0e0e0;
  background-color: white;
}

.option-item:hover {
  background-color: #f0f8ff;
}

.option-info {
  display: flex;
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

.delete-btn {
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
</style>