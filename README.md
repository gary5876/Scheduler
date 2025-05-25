# Scheduler
Scheduler

기능목록
일정생성,전체 일정조회, 단건 일정조회, 일정수정, 일정 삭제

1. 일정생성
   메서드 POST
   url : /schedules
   request body :
   {
   "task": "공부",
   "writer":철수",
   "password";"1234"
   }
   response :
   {
  "id": 1,
  "task": "스터디 준비",
  "writer": "철수",
  "createdAt": "2025-05-25T14:30:00",
  "updatedAt": "2025-05-25T14:30:00"
   }
2. 전체 일정조회
   메서드 : GET
   url : /schedules
   query parameters : writer, date
   response :
   {
    "id": 1,
    "task": "스터디 준비",
    "writer": "철수",
    "createdAt": "2025-05-25T14:30:00",
    "updatedAt": "2025-05-25T15:30:00"
   }
3. 단건 일정조회
   메서드 : GET
   url : /schedules/{id}
   response:
   {
  "id": 1,
  "task": "스터디 준비",
  "writer": "철수",
  "createdAt": "2025-05-25T14:30:00",
  "updatedAt": "2025-05-25T15:30:00"
  }
4. 일정 수정
   메서드 : PUT
   url : /schedules/{id}
   request body :
   {
  "task": "스터디 정리",
  "writer": "영희",
  "password": "1234"
   }
   response(success):
   {
  "id": 1,
  "task": "스터디 정리",
  "writer": "영희",
  "createdAt": "2025-05-25T14:30:00",
  "updatedAt": "2025-05-25T16:00:00"
   }
   response(fail):
   {
  "error": "비밀번호가 일치하지 않습니다."
   }
5. 일정 삭제
   메서드 : DELETE
   url : /schedules/{id}
   request body:
   {
  "password": "1234"
   }
   response(success):
   {
  "message": "일정이 삭제되었습니다."
   }
   response(fail):
   {
  "error": "비밀번호가 일치하지 않습니다."
   }

ERD
![image](https://github.com/user-attachments/assets/470b3e78-7fb3-4a3e-87b4-36d44b5eeae6)


