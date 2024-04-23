#!/bin/bash

# Diseña un script que haga de calculadora. El script mostrará un menú para sumar,
# restar, multiplicar, dividir y elevar. Antes de realizar la operación correspondiente
# solicitará al usuario los operandos mostrará el resultado volverá a mostrar el menú
# hasta que seleccionemos la opción de salir.

function sumar() {
  declare -i res=$1+$2
  echo "[+] La suma de $1 + $2 = $res"
}

function restar() {
  declare -i res=$1-$2
  echo "[+] La resta de $1 - $2 = $res"
}

function multiplicación() {
  declare -i res=$1*$2
  echo "[+] La multiplicación de $1 * $2 = $res"
}

function División() {
  if [ $2 -eq 0 ]; then
      echo "[!] No se puede dividir entre 0"
  else
    declare -i res=$1/$2
    echo "[+] La división de $1 / $2 = $res"
  fi
}

function elevar() {
  declare -i res=$1**$2
  echo "[+] $1 elevado a $2 = $res"
}

function mostrarMenu() {
  clear
  echo "+---------------------+"
  echo "|     OPERACIONES     |"
  echo "+---------------------+"
  echo "| 1 -> Suma           |"
  echo "| 2 -> Resta          |"
  echo "| 3 -> Multiplicación |"
  echo "| 4 -> División       |"
  echo "| 5 -> Elevar         |"
  echo "| 6 -> Salir          |"
  echo "+---------------------+"
}

declare -i input=0
declare -i n1=0
declare -i n2=0

while [ $input -ne 6 ]; do
  mostrarMenu
  echo -n "[+] Seleccione una opción: "
  read input
  if [ $input -ne 6 ]; then
      echo -n "[+] Escriba un número: "
      read n1
      echo -n "[+] Escriba otro número: "
      read n2
      case $input in
        1) sumar $n1 $n2 ;;
        2) restar $n1 $n2 ;;
        3) multiplicación $n1 $n2 ;;
        4) División $n1 $n2 ;;
        5) elevar $n1 $n2 ;;
        *) echo "[!] Opción no válida" ;;
      esac
      echo "[+] Presione enter para continuar..."
      read
  fi
done

echo "[!] Saliendo del programa..."