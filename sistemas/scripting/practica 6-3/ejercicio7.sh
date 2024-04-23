#!/bin/bash

# Crea un script que muestre un menú donde la primera opción sea insertar números en
# un fichero (mostrarlos de manera ordenada), la segunda los borra del fichero
# (elegimos el número que queremos borrar) y por último sale del menú.
# Comprobar que los números no estén repetidos. Utilizamos el case

clear

function mostrarMenu() {
  echo "+----------------------+"
  echo "|       OPCIONES       |"
  echo "+----------------------+"
  echo "| 1 -> Insertar número |"
  echo "| 2 -> Borrar número   |"
  echo "| 3 -> Salir           |"
  echo "+----------------------+"
}