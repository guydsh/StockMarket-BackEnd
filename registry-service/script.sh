#!/bin/bash
docker-compose down
docker rmi registry-service
mvn clean package
docker build -t registry-service .
docker-compose up -d

