FROM maven:3-openjdk-17 AS builder
WORKDIR /maven-repo
ADD pom.xml ./
ADD src ./src
RUN mvn dependency:go-offline -B
RUN mvn clean package -B -Dpmd.skip=true -Dcpd.skip=true -DskipTests

FROM eclipse-temurin:17-jre-focal AS final
COPY --from=builder /maven-repo/target .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/lanchonete-0.0.1-SNAPSHOT.jar"]
