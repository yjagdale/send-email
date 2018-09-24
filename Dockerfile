FROM openjdk:8-jdk-alpine
MAINTAINER alerting-ui UI Team "alerting-ui-ui@qualys.com"
ADD ./target/maven-sample.jar /etc/maven-sample-service/maven-sample.jar
ADD run_service.sh /etc/maven-sample-service/

RUN chmod +x /etc/maven-sample-service/run_service.sh
ENTRYPOINT "/etc/maven-sample-service/run_service.sh"
