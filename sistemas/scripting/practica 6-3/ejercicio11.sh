#!/bin/bash

# Realiza un script que reciba un nombre de usuario como parámetro (sólo uno) y
# compruebe si dicho usuario es un usuario del sistema. Si es así, que compruebe si está
# conectado al equipo en este momento. Si no introduce parámetros o introduce más de
# uno, informará y finalizará.

clear

if [ $# -eq 1 ]; then
  if id "$1" &>/dev/null; then
    activo=$(who | cut -d " " -f1 2>/dev/null)
    if [ "$activo" = "$1" ]; then
      echo "[+] El usuario $1 está conectado"
    else
      echo "[!] El usuario $1 no está conectado"
    fi
  else
    echo "[!] El usuario no está en el sistema"
  fi
else
  echo "[!] Se debe pasar solo un parámetro como usuario"
fi