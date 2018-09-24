#!/bin/bash

: ${CONSUL_HOST:="qcs_ui_consul"}
: ${CONSUL_PORT_ADDR:=80}
: ${CONSUL_PREFIX:="maven-sample-service/master"}
: ${SPRING_ACTIVE_PROFILE:="override"}

JAVAOPTS="-Dspring.cloud.consul.host=$CONSUL_HOST \
	-Dspring.cloud.consul.port=$CONSUL_PORT_ADDR \
	-Dspring.profiles.active=$SPRING_ACTIVE_PROFILE \
	-Dspring.cloud.consul.config.prefix=$CONSUL_PREFIX"

echo $JAVAOPTS

exec /usr/bin/java $JAVAOPTS -jar /etc/maven-sample-service/maven-sample.jar
