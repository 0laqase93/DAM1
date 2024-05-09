#!/bin/bash

clear

function showTable() {
  echo "----------------"
  echo "Tabla del $1"
  echo "----------------"
  for (( i = 1; i <= 10; i++ )); do
      echo "$i" x "$1" "=" "$(("$i"*"$1"))"
  done
  echo "----------------"
}

input=0
repeat=1

if [ $# -eq 1 ]; then
  showTable "$1"
  echo -n "[+] ¿Desea volver a iniciar el programa?(0=No): "
  read -r repeat
  if ! [ "$repeat" -eq "$repeat" ] 2>/dev/null; then
    repeat=1
  fi
else
  echo "[!] Se debe pasar un número como argumento ej: './Casares-scriptA.sh 5'"
  exit 0
fi

while [ "$repeat" -ne 0 ]; do
  clear
  echo -n "[+] introduce un número : "
  read -r input
  if [ "$input" -eq "$input" ] 2>/dev/null; then
    showTable "$input"
    echo -n "[+] ¿Desea volver a iniciar el programa?(0=No): "
    read -r repeat
    if ! [ "$repeat" -eq "$repeat" ] 2>/dev/null; then
      repeat=1
    fi
  else
    echo "[!] Se debe insertar un número"
    echo -n "[+] Presione enter para continuar..."
    read -r
  fi
done

echo "[+] Saliendo del programa..."