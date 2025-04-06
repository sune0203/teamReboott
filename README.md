#  Linguatech Feature Management System

##  프로젝트 소개

Linguatech Feature Management System은 SaaS 기업에서 요금제 기반의 기능 사용 관리를 할 수 있도록 설계된 시스템입니다.  
기업 고객은 다양한 기능(feature)을 사용하고, 사용량에 따라 크레딧을 차감하며, 통계를 조회할 수 있습니다.

## 
---

##  기술 스택

- Kotlin, Spring Boot 3.2.4 (swagger 충돌로 3.5.0 에서 다운그레이드)
- Spring Data JPA (Hibernate)
- Spring Security (기본 인증)
- MySQL (Docker Compose) [user:root pw:root123456]
- Swagger (SpringDoc OpenAPI)
- Gradle + Docker

---

##  프로젝트 구조

```
src
└── main
    ├── kotlin
    │   └── com.reboott.linguatech
    │       ├── common         // 이전 프로젝트들에서 사용해오던 공통 응답 구조 로직을 들고옴.
    │       ├── config         // 보안 설정, 초기화 {테스트라 모든 권한}
    │       ├── controller     // REST API 컨트롤러
    │       ├── dto            // 요청/응답 DTO
    │       ├── entity         // JPA Entity
    │       ├── repository     // JPA 인터페이스
    │       └── service        // 비즈니스 로직
    └── resources
        ├── application.yaml   // 환경 설정
        └── data.sql           // 초기 데이터
```

---

##  실행 방법

1. 빌드
```bash
./gradlew clean build
```

2. 도커 실행
```bash
docker compose down -v && docker compose up --build -d
```

3. 확인
- API 서버: [http://localhost:8080]
- Swagger UI: [http://localhost:8080/swagger-ui/index.html]

---

##  Swagger 사용법

###  인증 없이 사용 가능

- `SecurityConfig`를 통해 Swagger 관련 경로는 인증 없이 호출 가능하도록 설정되어 있음.

###  API 테스트 예시

#### 요금제 생성
```http
POST /plans
{
  "name": "올인원 플랜"
  "featureIds": [
    1,2,3,4
  ]
}
```

#### 회사 생성
```http
POST /companies
{
  "name": "팀리부뜨",
  "credit": 20000,
  "currentPlanId":1
}
```

#### 기능 생성
```http
POST /features
{
  "name": "AI OA서비스",
  "creditPerUse": 10,
  "limitAmount": 100
}
```

#### 요금제 할당
```http
POST /companies/1/assign-plan
{
  "planId": 1
}
```

#### 기능 사용
```http
POST /features/use
{
  "companyId": 1,
  "featureId": 1,
  "usageAmount": 5
}
```

#### 사용 통계 조회
```http
GET /features/usage/statistics
```

---

## 테스트 요약

1. `/plans` → 요금제 등록 ( 필요 기능들[features:PK]을 묶어서 등록 )
2. `/companies` → 기업 등록 (초기 크레딧 입력)
3. `/features` → 기능 등록 (기능별 단가 및 사용량 제한)
4. `/companies/{id}/assign-plan` → 해당 기업에 요금제 할당
5. `/features/use` → 특정 기능 사용 (사용량 기준 크레딧 차감)
6. `/features/usage/statistics` → 통계 대시보드용 데이터 조회