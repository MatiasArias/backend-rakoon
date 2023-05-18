# creo la imagen para el proyecto basandome en una eclipse de oracle
FROM eclipse-temurin:17

# creo el directorio /code
RUN mkdir /code
# establesco el directorio actual de trabajo
WORKDIR /code
# actualizo los paquetes del SO
RUN apt-get update && apt-get -y upgrade && \
    apt-get install -y inotify-tools dos2unix
