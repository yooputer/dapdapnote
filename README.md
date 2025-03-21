# 답답노트
> 영어공부를 할 때 답답했던 문장들을 기록하고 공유하는 서비스
---
### 기술 스택
- DB
  - PostgreSQL 13
- Backend
  - Java17
  - SpringBoot 3.1.5
  - Jpa
- Frontend
  - Vue3
---
### 개발환경 세팅 방법
##### 1. 디비 실행(원격으로 접속할 수 있는 DB서버 구축 전까지는 로컬에서 디비 컨테이너 실행하여 접속)
```
cd <프로젝트 경로>/dapdapnote-postgresql

# 이미지 빌드
docker build -t dapdapnote-postgres:1.0.0 .

# 컨테이너 실행
docker run -d --name dapdapnote-postgres -p 5432:5432 dapdapnote-postgres:1.0.0

# DB서버 접속
docker exec -it dapdapnote-postgres su postgres bash -c "psql --username=dapdapnote --dbname=dapdapnote"
```
##### 2. 백엔드 실행
- 프로젝트 열기
- DapdapnoteBackendApplication 실행

##### 3. 프론트엔드 실행
```
cd <프로젝트 경로>/dapdapnote-frontend

# 라이브러리 설치
npm install

# 실행
npm run serve
```
---
### 커밋 컨벤션
##### 커밋메시지 구조
```
<타입>: 제목

본문
```
##### 커밋 타입
| 타입명    | 내용          |
|--------|-------------|
| feat   | 새로운 기능 구현   |
| fix    | 버그 수정       |
| build  | 빌드 관련 파일 수정 |
| chore  | 자잘한 수정      |
| cicd   | CI/CD 관련 수정 |
| docs   | 문서 관련 수정    |
| css    | 퍼블리싱 관련 수정  |
| refact | 리팩토링        |
| test   | 테스트코드 수정    |
---
### 경로

| 설명    | 경로             |
|-------|----------------|
| 홈     | /              |
| 노트 생성 | /create        |
| 노트 수정 | /edit/:noteSeq |
---
### API 목록
| 설명          | 메소드  | url                      |
|-------------|------|--------------------------|
| 노트 목록 조회    | GET  | /api/note/list           |
| 노트 저장       | POST | /api/note/save           |
| 노트 수정 정보 조회 | GET  | /api/note/edit/{noteSeq} |