# Simulación de Sistemas
Trabajo Práctico Nro. 2: Autómatas Celulares

##Integrantes
* [Paula Andrea Domingues](https://github.com/pdomins), 60148
* [Maximiliano Lombardia](https://github.com/mlombardia), 56276
* [Mariano Leonel Perez Mosquera](https://github.com/marianopm), 56285

## Ejecución
Ubicandose en el directorio ss-tp2, ejecutar
```sh
mvn package o mvn install
```
Una vez ejecutado este comando, se creará un archivo `ss-tp2-1.0-SNAPSHOT-jar-with-dependencies.jar`.
Moverse al directorio en el cual se encuentre ubicado el mismo y ejecutar
```sh
java -jar ss-tp2-1.0-SNAPSHOT-jar-with-dependencies.jar <parametros>
```
Dentro de los parametros se puede especificar con -N el numero de particulas de gas que desea que tenga la grilla, y con -D el tamaño en celdas que desea que mida la apertura entre recintos.
Si los mismos no se especificaran, se tomara por default N = 5000 y D = 50.

