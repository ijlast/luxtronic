FROM openjdk:8-jre-alpine

# ALternative, however libtcnative-1 is not currently available for this version of springBoot (also, this is not intended to be a high volume server). 
#FROM openjdk:8-jre-stretch
#RUN apt-get update
#RUN apt-get install -y libtcnative-1

ADD build/libs/luxtronic-0.2.0.jar /opt/luxtronic-0.2.0.jar

ENV HEATPUMP_IP 192.168.178.6
ENV HEATPUMP_PORT 8888
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/opt/luxtronic-0.2.0.jar"]

# 
# Example usage:
# docker build -t luxtonic_rest_service .
# docker run -dit --restart=always -e HEATPUMP_IP=<your heatpump ip> -e HEATPUMP_PORT=8889 -p 9090:8080 luxtonic_rest_service:latest
#
# N.B. HEATPUMP_PORT defaults to 8888
