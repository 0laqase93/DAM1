#!/bin/bash

# Diseñar un script que le dé permisos de ejecución a un fichero pasado como
# parámetro, primero hemos de comprobar si el fichero existe, si no existe avisará.

clear
RESET='\033[0m'
GREEN='\033[00;32m'
RED='\033[00;31m'
PURPLE='\033[00;35m'


if [ "$1" ]; then
  if [ -f $1 ]; then
    chmod +x $1
    echo -e "${GREEN}[+]${RESET} Se le han asignado permisos de ejecución al fichero ${PURPLE}$1${RESET}"
  else
    echo -e "${RED}[!]${RESET} El fichero no se ha encontrado."
  fi
else
  echo -e "${RED}[!]${RESET} Se debe pasar un fichero como argumento."
fi