# creo la imagen para el proyecto basandome en una eclipse de oracle
FROM eclipse-temurin:17
# actualizo los paquetes del SO
RUN apt-get update && apt-get -y upgrade
RUN apt-get install -y inotify-tools dos2unix
#establesco como HOME el directorio code
ENV HOME=/code
# creo el directorio
RUN mkdir -p $HOME
# establesco el directorio de trabajo
WORKDIR $HOME
