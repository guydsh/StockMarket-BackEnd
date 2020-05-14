FROM java:8

RUN echo "Asia/Shanghai" > /etc/timezone && mkdir /app

COPY target/registry-service.jar /app/app.jar

WORKDIR /app

EXPOSE 8761

CMD ["java", "-jar", "app.jar"]
