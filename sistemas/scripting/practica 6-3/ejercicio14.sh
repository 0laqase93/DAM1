#!/bin/bash

# Realiza un script al que se le pase un usuario como parámetro y devuelva todos los
# grupos secundarios a los que pertenece. La salida del script será toda en la misma
# línea (“El usuario XXXX tiene como grupos secundarios: grupo1, grupo2, …”).

clear
grupos=""

if [ $# -eq 1 ]; then
  grupos=$(cat /etc/group | grep -w "$1" | cut -d ":" -f1 | grep -v "$1" | tr '\n' ' ')
  echo "El usuario $1 tiene como grupos secundarios: $grupos"
else
  echo "[!] Se debe pasar un usuario como parámetro"
fi
