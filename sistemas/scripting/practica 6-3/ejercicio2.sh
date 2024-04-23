#!/bin/bash

# Realizar un script que dado un número 'n' muestre los diez primeros elementos de su
# tabla de multiplicar, mostrando el resultado en la forma: i x n = resultado. Emplear un
# bucle for con seq. Si no se proporciona un número, mostrar cómo se usa el programa.

clear
RESET='\033[0m'
GREEN='\033[00;32m'
RED='\033[00;31m'
PURPLE='\033[00;35m'

declare -i input=0

echo -en "${GREEN}[+]${RESET} Ingrese un número: "
read input

if [ $input -gt 0 ]; then
  echo -e "----------------"
  echo -e "${PURPLE}[+] Tabla del $input${RESET}"
  echo -e "----------------"
  for (( i = 1; i <= 10; i++ )); do
    declare -i mult=$input*$i
    echo -e "${GREEN}$input${RESET} x ${GREEN}$j${RESET} = ${RED}$mult${RESET}"
  done
  echo -e "----------------"
else
  echo -e "${RED}[!]${RESET} Se debe ingresar un número mayor a 0."
fi