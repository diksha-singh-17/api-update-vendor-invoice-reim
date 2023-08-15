FROM openjdk:17-slim as builder
## Comment  Below till line number 19 for developement purpose, PLEASE UNCOMMENT IT BEFORE CHECK-IN
ARG API_URL
ARG API_TOKEN
ARG REPO_NAME
ARG CLOUD_DEPLOY_NAME
ARG _CLOUD_DEPLOY_NAME=$CLOUD_DEPLOY_NAME
ARG DT_REPO_NAME=$REPO_NAME
ARG DT_API_URL=$API_URL
ARG DT_API_TOKEN=$API_TOKEN
ARG DT_ONEAGENT_OPTIONS="flavor=default&include=java"
ENV DT_HOME="/opt/dynatrace/oneagent"
RUN apt-get update && \
    apt-get install -y wget && \
    apt-get install unzip && \
    mkdir -p "$DT_HOME" && \
    wget -O "$DT_HOME/oneagent.zip" "$DT_API_URL/v1/deployment/installer/agent/unix/paas/latest?Api-Token=$DT_API_TOKEN&$DT_ONEAGENT_OPTIONS" && \
    unzip -d "$DT_HOME" "$DT_HOME/oneagent.zip" && \
    rm "$DT_HOME/oneagent.zip"
ENV LD_PRELOAD /opt/dynatrace/oneagent/agent/lib64/liboneagentproc.so


# # Copy the jar to the production image from the builder stage.
ADD target/$_CLOUD_DEPLOY_NAME-0.0.1-SNAPSHOT.jar app.jar

# # Run the web service on container startup.
CMD ["java", "-jar", "app.jar"]

#WORKDIR /app
# COPY pom.xml .
# # Use this optimization to cache the local dependencies. Works as long as the POM doesn't change
# RUN mvn dependency:go-offline

# COPY src/ /app/src/
# RUN mvn package

# # Use AdoptOpenJDK for base image.
# FROM adoptopenjdk/openjdk11:jre-11.0.8_10-alpine

