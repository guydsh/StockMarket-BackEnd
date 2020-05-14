#!/bin/bash
docker-compose down
docker rmi stock-exchange-service
mvn clean package
docker build -t stock-exchange-service .
docker-compose up -d

