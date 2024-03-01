#!/bin/bash

Green='\033[0;32m'
Yellow='\033[0;33m'
NC='\033[0m' # No Color

imageNames=(
    'application'
    'grafana'
)

for name in "${imageNames[@]}"
do
    CONTAINER_NAME=$(docker container ls -a --format "{{.Names}}" | grep ${name})
    if [[ ! -z "${CONTAINER_NAME}" ]]; then
        printf "${Green}Removing existing container for ${Yellow}${name}${NC}\n"
        docker rm -f "${CONTAINER_NAME}"
    fi

    DOCKER_IMAGE_ID=$(docker images -aq ovp-kaas-tools_${name})
    if [[ ! -z "${DOCKER_IMAGE_ID}" ]]; then
        printf "${Green}Removing existing docker image for ${Yellow}${name}${Green} with id: ${Yellow}${DOCKER_IMAGE_ID}${NC}\n"
        docker rmi -f "${DOCKER_IMAGE_ID}"
    fi
done

./gradlew clean build

echo "Starting Docker Compose"
docker-compose up --build -d

printf "${Green} Containers started successfully and running in detached mode${NC}\n"