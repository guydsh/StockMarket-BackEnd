#!/bin/bash
docker-compose down
docker rmi sector-service
mvn clean package
docker build -t sector-service .
docker-compose up -d

