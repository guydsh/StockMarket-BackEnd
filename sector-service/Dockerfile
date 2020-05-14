FROM java:8

RUN echo "Asia/Shanghai" > /etc/timezone && mkdir /app

COPY target/sector-service.jar /app/app.jar

WORKDIR /app

EXPOSE 8084

CMD ["java", "-jar", "app.jar"]
