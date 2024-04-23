#!/bin/bash

# Crea un script que borre todos los ficheros pasados como parámetros, el script
# comprobará si el fichero existe y mostrará un resumen de los ficheros borrados con
# éxito y los ficheros que no existen.

clear
RESET='\033[0m'
GREEN='\033[00;32m'
RED='\033[00;31m'
PURPLE='\033[00;35m'

if [ $# -ne 0 ]; then
  for i in $* ; do
    if [ -f $i ]; then
      rm $i
      echo -e "${GREEN}[+]${RESET} Archivo $i eliminado con éxito"
    else
      echo -e "${RED}[-]${RESET} El archivo $i no existe"
    fi
  done
else
  echo -e "${RED}[!]${RESET} Se deben pasar los archivos que se quieren borrar como parámetro."
fi
