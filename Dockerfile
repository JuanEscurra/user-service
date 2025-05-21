FROM eclipse-temurin:11-jre-alpine

# Directorio de trabajo dentro del contenedor
WORKDIR /app

COPY target/tu-app.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
