FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -B dependency:go-offline
COPY src ./src
RUN mvn -B clean package -DskipTests

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
# Los contenedores corren en UTC por defecto; el negocio opera en hora de Perú, así que se
# fija la zona horaria de la JVM para que LocalDateTime.now() (usado en las lecturas de
# telemetría) coincida con la hora local en vez de estar 5 horas adelantada.
ENV TZ=America/Lima
ENV JAVA_TOOL_OPTIONS="-Duser.timezone=America/Lima"
ENTRYPOINT ["java", "-jar", "app.jar"]
