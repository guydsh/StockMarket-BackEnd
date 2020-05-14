#!/bin/bash
docker-compose down
docker rmi gateway-service
mvn clean package -P docker
docker build -t gateway-service .
docker-compose up -d

