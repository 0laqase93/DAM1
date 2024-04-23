#!/bin/bash

# Escribe un script en el que dados dos argumentos numéricos (pasados como
# parámetros) los sumen si el primero es menor o igual que el segundo y los reste en
# caso contrario.

clear
RESET='\033[0m'
GREEN='\033[00;32m'
RED='\033[00;31m'
PURPLE='\033[00;35m'

if [ $# -eq 2 ]; then
  if [ $1 -le $2 ]; then
    declare -i res=$1+$2
    echo -e "${GREEN}[+]${RESET} La ${PURPLE}suma${RESET} de ${GREEN}$1${RESET} + ${GREEN}$2${RESET} = ${GREEN}$res${RESET}"
  else
    declare -i res=$1-$2
        echo -e "${GREEN}[+]${RESET} La ${PURPLE}resta${RESET} de ${GREEN}$1${RESET} - ${GREEN}$2${RESET} = ${GREEN}$res${RESET}"
  fi
else
  echo -e "${RED}[!]${RESET} Se necesitan dos argumentos numéricos ${PURPLE}[Ej: ejercicio1 1 2]${RESET}"
fi