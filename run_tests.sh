#!/bin/bash

CONTAINER_NAME="swaggerapi-petstore3"

# Check if the container is running
if [ "$(docker ps -q -f name=$CONTAINER_NAME)" ]; then
    echo "Container $CONTAINER_NAME is running. Stopping it..."
    docker stop $CONTAINER_NAME
    echo "Container stopped."
else
    echo "Container $CONTAINER_NAME is not running."
fi

# Pull and Run the API in Docker:
docker pull swaggerapi/petstore3:unstable
docker run  --name $CONTAINER_NAME -d -p 8080:8080 swaggerapi/petstore3:unstable

# Run the tests using Maven:
mvn clean test
