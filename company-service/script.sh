#!/bin/bash
docker-compose --compatibility down
docker rmi company-service
mvn clean package -P docker
docker build -t company-service .
docker-compose --compatibility up -d

