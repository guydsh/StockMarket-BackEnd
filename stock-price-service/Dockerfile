FROM java:8

RUN echo "Asia/Shanghai" > /etc/timezone && mkdir /app

COPY target/stock-price-service.jar /app/app.jar

WORKDIR /app

EXPOSE 8085

CMD ["java", "-jar", "app.jar"]
