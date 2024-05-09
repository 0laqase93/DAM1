#!/bin/bash

clear

function printMenu() {
    echo "+--------------------------------------+"
    echo "|                 MENÚ                 |"
    echo "+--------------------------------------+"
    echo "| 1 -> Generar Fichero                 |"
    echo "| 2 -> Imprimir Menores de 50          |"
    echo "| 3 -> Imprimir Mayores o Iguales a 50 |"
    echo "| 4 -> Salir                           |"
    echo "+--------------------------------------+"
}

function generateFile() {
  rm numbers.num 2>/dev/null
  touch numbers.num
  for (( i = 0; i < 20; i++ )); do
      num=$((RANDOM % 101))
      if grep -w $num numbers.num &>/dev/null; then
        i=$(("$i"-1));
      else
        echo "$num" >> numbers.num
      fi
  done
  sort -g numbers.num >> tmp.num
  mv tmp.num numbers.num
  echo -n "[+] Números generados en el archivo 'numbers.num': "
  tr "\n" " " < numbers.num
  echo ""
  echo -n "[+] Presione enter para continuar..."
  read -r
}

function printLower() {
  if [ -f numbers.num ]; then
    numbers=$(cat numbers.num)
    echo -n "[+] Números menores a 50: "
    for i in $numbers ; do
        if [ "$i" -lt 50 ]; then
            echo -n "$i "
        fi
    done
    echo ""
    echo -n "[+] Presione enter para continuar..."
    read -r
  else
    echo "[!] No se ha encontrado el archivo, por favor creelo en la opción 1."
    echo -n "[+] Presione enter para continuar..."
    read -r
  fi
}

function printHighest() {
  if [ -f numbers.num ]; then
    numbers=$(cat numbers.num)
    echo -n "[+] Números mayores o iguales a 50: "
    for i in $numbers ; do
        if [ "$i" -ge 50 ]; then
            echo -n "$i "
        fi
    done
    echo ""
    echo -n "[+] Presione enter para continuar..."
    read -r
  else
    echo "[!] No se ha encontrado el archivo, por favor creelo en la opción 1."
    echo -n "[+] Presione enter para continuar..."
    read -r
  fi
}

function exitScript() {
    echo "[+] Saliendo del programa..."
    rm numbers.num 2>/dev/null
    exit 0
}

input=0
while [ "$input" -ne 4 ]; do
  clear
  printMenu
  echo -n "[+] Inserte una opción: "
  read -r input
  if [ "$input" -eq "$input" ] 2>/dev/null; then
      case $input in
        1) generateFile;;
        2) printLower;;
        3) printHighest;;
        4) exitScript;;
        *) echo "[!] Opción no válida"
           echo -n "[+] Presione enter para continuar..."
           read -r;;
      esac
  else
    input=0
    echo "[!] Opción no válida"
    echo -n "[+] Presione enter para continuar..."
    read -r
  fi
done