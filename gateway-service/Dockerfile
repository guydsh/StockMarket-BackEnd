FROM java:8

RUN echo "Asia/Shanghai" > /etc/timezone && mkdir /app

COPY target/gateway-service.jar /app/app.jar

WORKDIR /app

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
