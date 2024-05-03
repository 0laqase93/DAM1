#!/bin/bash

# Realiza un script que compruebe si una palabra es palíndroma o no. Una palabra es
# palíndroma si se lee igual de izquierda a derecha que de derecha a izquierda.

clear

inversa=""

if [ $# -eq 1 ]; then
  inversa=$(echo "$1" | rev);
  if [ "$inversa" = "$1" ]; then
    echo "[+] la palabra $1 es un palíndromo"
  else
    echo "[-] La palabra $1 al revés es $inversa, no es un palíndromo"
  fi
else
  echo "[!] Se debe pasar una palabra como parámetro"
fi