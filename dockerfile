# Maven 이미지를 사용하여 애플리케이션을 빌드합니다.
FROM maven:3.8.3-openjdk-17 AS build

# 작업 디렉토리를 설정합니다.
WORKDIR /app

# Maven pom.xml과 소스 코드를 복사합니다.
COPY pom.xml /app
COPY src /app/src

# 애플리케이션을 빌드합니다.
RUN mvn clean package

# Tomcat 기본 이미지를 사용하여 배포합니다.
FROM tomcat:10-jdk17

# 기본 ROOT 애플리케이션을 제거합니다.
RUN rm -rf /usr/local/tomcat/webapps/ROOT

# 빌드 단계에서 생성된 WAR 파일을 Tomcat의 webapps 디렉토리로 복사합니다.
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

# Tomcat의 포트를 노출합니다.
EXPOSE 8080

# Tomcat을 시작합니다.
CMD ["catalina.sh", "run"]