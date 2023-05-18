#!/bin/bash
dos2unix mvnw # convierte de formato, unix y windows, segun la maquina donde estes
./mvnw spring-boot:run -Dspring-boot.run.jvmArguments="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005" &
while true; do
  inotifywait -e modify,create,delete,move -r ./src/ && ./mvnw compile
done
