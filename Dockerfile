FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/MHC-0.0.1-SNAPSHOT.jar MHC-0.0.1-SNAPSHOT.jar
EXPOSE 3000
ENTRYPOINT ["java","-jar","MHC-0.0.1-SNAPSHOT.jar"]