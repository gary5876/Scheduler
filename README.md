# Scheduler lv 6

## 기능 요약

- 일정 등록
- 일정 단건 조회
- 일정 전체 조회 (조건 및 페이지네이션 지원)
- 일정 수정 (비밀번호 확인 필요)
- 일정 삭제 (비밀번호 확인 필요)

### 1. 일정 생성

- URL: `/schedules`
- Method: `POST`
- Request Body:

```json
{
  "task": "과제하기",
  "writer": "김길동",
  "password": "1234",
  "email": "road@test.com"
}
```

- Response:

```json
{
  "id": 1,
  "task": "과제하기",
  "writer": "김길동",
  "createdAt": "2025-05-31T10:00:00",
  "updatedAt": "2025-05-31T10:00:00"
}
```

---

### 2. 일정 단건 조회

- URL: `/schedules/{id}`
- Method: `GET`
- Response:

```json
{
  "id": 1,
  "task": "운동하기",
  "writer": "홍길동",
  "createdAt": "2024-05-01T10:00:00",
  "updatedAt": "2024-05-01T10:00:00"
}
```

---

### 3. 일정 전체 조회 (조건 + 페이지네이션)

- URL: `/schedules`
- Method: `GET`
- Query Parameters:

    - `writer` (optional): 작성자 이름
    - `date` (optional): 수정일 (형식: `YYYY-MM-DD`)
    - `page` (optional): 페이지 번호 (0부터 시작)
    - `size` (optional): 페이지당 개수
- Response:

```json
{
  "id": 1,
  "task": "운동하기",
  "writer": "홍길동",
  "createdAt": "2024-05-01T10:00:00",
  "updatedAt": "2024-05-01T10:00:00"
}
```

---

### 4. 일정 수정

- URL: `/schedules/{id}`
- Method: `PUT`
- Request Body:

```json
{
  "task": "헬스하기",
  "writer": "김길동",
  "password": "1234"
}
```

- Response:

```json
{
  "id": 1,
  "task": "헬스하기",
  "writer": "김길동",
  "createdAt": "2025-05-31T10:00:00",
  "updatedAt": "2025-06-02T09:00:00"
}
```

---

### 5. 일정 삭제

- URL: `/schedules/{id}`
- Method: `DELETE`
- Request Body:

```json
{
  "password": "1234"
}
```

- Response:

```json
{
  "message": "삭제 완료"
}
```

---

### 6. 유효성 검사 조건

- `task`: 200자 이내, 비어 있을 수 없음
- `password`: 필수 입력
- `email`: 형식에 맞아야 함

---

### 작성자 및 연관관계

- 일정은 내부적으로 `writer_id`로 작성자와 연결됨
- 작성자(`writer`)는 이름, 이메일, 생성/수정일을 보유함
- 클라이언트는 이름/이메일만 입력하면 됨 (ID는 내부 관리)

---



