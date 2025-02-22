UserTaskManager  
├── src/main/java/com/bos/usertaskmanager  
│   ├── config/        # 설정 파일 (Database, Scheduler 등)  
│   ├── controller/    # API 요청을 처리하는 레이어  
│   ├── service/       # 비즈니스 로직을 처리하는 레이어  
│   ├── repository/    # DB 접근을 담당하는 레이어  
│   ├── model/         # Entity 클래스 정의  
│   ├── scheduler/     # 스케줄링 관련 클래스 (Task 처리용)  
│   └── TaskManagerApplication.java  # Spring Boot 메인 클래스  
├── src/main/resources  
│   ├── application.yml  # 환경설정 파일  
├── build.gradle  
├── settings.gradle  
└── README.md  
