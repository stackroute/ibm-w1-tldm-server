FROM java:8-jre
WORKDIR usr/src
ADD ./target/GatewayService-0.0.1-SNAPSHOT.jar /usr/src/GatewayService-0.0.1-SNAPSHOT.jar
RUN chmod +x /usr/src/GatewayService-0.0.1-SNAPSHOT.jar
EXPOSE 8089
ENTRYPOINT ["java","-Xmx200m", "-jar", "/usr/src/GatewayService-0.0.1-SNAPSHOT.jar"]
