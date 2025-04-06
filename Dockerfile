# 베이스 이미지 (Java 21)
FROM eclipse-temurin:21-jdk

# 작업 디렉터리 생성
WORKDIR /app

# build/libs 디렉토리에서 jar 파일 복사
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

# 컨테이너 실행 시 jar 실행
ENTRYPOINT ["java", "-jar", "app.jar"]
