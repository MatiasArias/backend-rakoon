# **Para levantar el proyecto**

- Primero lo que hice fue crear el docker file para crear la imagen del proyecto.
- Despues cree el docker compose que crea la imgaen del proyecto y conecta con los demas contenedores de postgres y pgadmin
- cree el scrip de run.sh que corre el proyecto dentro del contenedor y cada ves que detecta que hay algun cambio en los archivos del proyecto lo detecta y actualiza el proyecto

### Comando para borrar el cache de docker y construir de nuevo.
Docker tiene una memoria cache, le llama "capas", lo usa para almacenar los cambios realizados en los pasos de la construccion de la imagen. Entonces para construir de nuevo una imagen limpia hay que especificarle con --no-cache.
```
docker compose build --no-cache
```
### Comando para crear la imagen y levantar el proyecto
con este comando lo que hace es primero un buil de la imagen que tiene que construir, en este casa la del proyecto basandose en el dockerfile y luego levanta el proyecto. **ESTE COMANDO SE USA UNA VES, O SOLO CUANDO HAYAN CAMBIOS DIRECTOS EN LA IMAGEN DEL PROYECTO, ES DECIR ALGUN CAMBIO EN EL Dockerfile**
```
docker compose up --build
```

### Levantar el proyecto

Con este es el que van a ejecutar todos los dias despues que hicieron el buil de arriba, con este comando levantan todos los contenedores, lo ejecutan cuando arrancan a trabajar o cuando quieren ver los cambios, en ves de darle al boton verde de ejecutar del intellj, porque eso no va a funcionar.
```
docker compose up
```

### Para ingresar a la base de datos
Este comando lo tienen que ejecutar **despues** que levantaron el proyecto, es decir hiciero el up con el docker compose, de otra manera no va a correr.
```
docker exec -it db-back-rakoon psql -U postgres
```
