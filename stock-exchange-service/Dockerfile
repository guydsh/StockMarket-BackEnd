FROM java:8

RUN echo "Asia/Shanghai" > /etc/timezone && mkdir /app

COPY target/stock-exchange-service.jar /app/app.jar

WORKDIR /app

EXPOSE 8082

CMD ["java", "-jar", "app.jar"]
