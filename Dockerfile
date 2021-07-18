FROM adoptopenjdk/openjdk11:latest

ARG JAR_FILE=build/libs/luxtronic-*.jar

#RUN addgroup -S spring && adduser -S spring -G spring
#USER spring:spring

COPY  ${JAR_FILE} /opt/luxtronic.jar
WORKDIR /opt

ENV HEATPUMP_IP 192.168.178.6
ENV HEATPUMP_PORT 8888
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "luxtronic.jar"]

# 
# Example usage:
# docker build -t luxtonic_rest_service .
# docker run -dit --restart=always --log-opt max-size=10m --log-opt max-file=5 -e HEATPUMP_IP=<your heatpump ip> -e HEATPUMP_PORT=8889 -p 9090:8080 luxtonic_rest_service:latest
#
# N.B. HEATPUMP_PORT defaults to 8888
