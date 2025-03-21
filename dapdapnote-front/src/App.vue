<template>
  <div class="container">
    <router-view></router-view>
  </div>
  <!-- 하단 네비게이션 바 -->
  <div class="bottom-navbar">
    <div class="nav-item" @click="goTo('home')">
      <div class="nav-icon">🏠</div>
      <div class="nav-text">홈</div>
    </div>
    <div class="nav-item" @click="goTo('note')">
      <div class="nav-icon">📒</div>
      <div class="nav-text">노트</div>
    </div>
    <div class="nav-item" @click="goTo('expression')">
      <div class="nav-icon">🔤</div>
      <div class="nav-text">어휘</div>
    </div>
    <div class="nav-item" @click="goTo('mypage')">
      <div class="nav-icon">👤</div>
      <div class="nav-text">마이페이지</div>
    </div>
  </div>
</template>

<script setup>
import router from "@/router";

function goTo(menuName){
  switch (menuName){
    case 'home':
      router.push({ path: '/' });
      break;
    case 'note':
      router.push({ path: '/note/list' });
      break;
    case 'expression':
      router.push({ path: '/expression/list' });
      break;
    case 'mypage':
      router.push({ path: '/mypage' });
      break;
  }
}
</script>

<style>
.container {
  display: flex;
  flex-direction: column;
  height: 100vh; /* 변경: 전체 뷰포트 높이를 사용 */
  width: 100%;
  position: relative;
  overflow: hidden; /* 추가: 내부 스크롤을 위해 */
  padding-bottom: 60px;
}

/* 하단 네비게이션 바 스타일 */
.bottom-navbar {
  position: fixed;
  bottom: 0;
  left: 0;
  width: 100%;
  height: 60px;
  background-color: white;
  display: flex;
  justify-content: space-around;
  align-items: center;
  box-shadow: 0 -2px 5px rgba(0,0,0,0.1);
  z-index: 99;
}

.nav-item {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100%;
  padding: 0 10px;
}

.nav-icon {
  font-size: 1.5rem;
  margin-bottom: 4px;
  color: #999;
}

.nav-text {
  font-size: 0.7rem;
  color: #999;
}

.nav-item.active .nav-icon,
.nav-item.active .nav-text {
  color: #3b7edd;
}
</style>