#!/bin/bash
docker-compose down
docker rmi stock-price-service
mvn clean package
docker build -t stock-price-service .
docker-compose up -d

