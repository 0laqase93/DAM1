#!/bin/bash

# Diseñar un script que le dé permisos de ejecución a un fichero pasado como
# parámetro, primero hemos de comprobar si el fichero existe, si no existe avisará.

clear

if [ "$1" ]; then
  if chmod +x "$1" 2>/dev/null; then
    echo -e "[+] Se le han asignado permisos de ejecución al fichero $1"
  else
    echo -e "[!] No se ha podido modificar los permisos de $1."
  fi
else
  echo -e "[!] Se debe pasar un fichero como argumento."
fi