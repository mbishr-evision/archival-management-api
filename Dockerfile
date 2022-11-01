FROM maven:3.8.6-openjdk-18 AS builder

# Build service
WORKDIR /build
COPY . . 
RUN mvn clean install

# app
FROM amd64/openjdk:18-jdk
WORKDIR /app
COPY --from=builder /build/target/archival-management-ms-api-0.0.1-SNAPSHOT.jar /app/
COPY ./archival-management.sh /app/archival-management.sh
RUN chmod +x /app/archival-management.sh
ENTRYPOINT [ "/app/archival-management.sh" ]