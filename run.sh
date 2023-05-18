#!/bin/bash
dos2unix mvnw # esta linea lo que hace es que permite que el archivo mvnw pueda ser identificado en cualquier SO, linux/windows
./mvnw spring-boot:run &
while true; do
  inotifywait -e modify,create,delete,move -r ./src/ && ./mvnw compile
done
