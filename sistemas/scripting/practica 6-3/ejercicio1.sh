#!/bin/bash

# Escribe un script en el que dados dos argumentos numéricos (pasados como
# parámetros) los sumen si el primero es menor o igual que el segundo y los reste en
# caso contrario.

clear

if [ $# -eq 2 ]; then
  if expr $1 -le $2 2>/dev/null; then
    if [ "$1" -le "$2" ]; then
      echo "[+] La suma de $1 + $2 = $(($1+$2))"
    else
      echo "[+] La resta de $1 - $2 = $(($1-$2))"
    fi
  else
    echo "[!] Valores no válidos"
  fi
else
  echo -e "[!] Se necesitan dos argumentos numéricos [Ej: ejercicio1 1 2]"
fi