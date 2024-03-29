FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY build/libs/vivid-web-app-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
ENTRYPOINT ["java", "-Djasypt.encryptor.password=goSecretsForLife!", "-Dlogging.level.com.vivid=DEBUG", "-Dspring.flyway.enabled=false", "-jar", "/app/vivid-web-app-0.0.1-SNAPSHOT.jar"]
CMD ["--spring.profiles.active=prod"]