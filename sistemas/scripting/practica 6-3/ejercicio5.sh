#!/bin/bash

# Diseña un script que haga de calculadora. El script mostrará un menú para sumar,
# restar, multiplicar, dividir y elevar. Antes de realizar la operación correspondiente
# solicitará al usuario los operandos mostrará el resultado volverá a mostrar el menú
# hasta que seleccionemos la opción de salir.

function sumar() {
  n1=0
  n2=0
  echo "SUMAR"
  echo "-----"
  echo -n "[+] Escriba un número: "
  read -r n1
  echo -n "[+] Escriba otro número: "
  read -r n2
  if ((n1+n2)); then
    echo "[+] La suma de $n1 + $n2 = $((n1+n2))"
  else
    echo "[-] Valores no válidos"
  fi

}

function restar() {
  n1=0
  n2=0
  echo "RESTA"
  echo "-----"
  echo -n "[+] Escriba un número: "
  read -r n1
  echo -n "[+] Escriba otro número: "
  read -r n2
  if ((n1-n2)); then
    echo "[+] La resta de $n1 - $n2 = $((n1-n2))"
  else
    echo "[-] Valores no válidos"
  fi
}

function multiplicación() {
  n1=0
  n2=0
  echo "MULTIPLICAR"
  echo "-----------"
  echo -n "[+] Escriba un número: "
  read -r n1
  echo -n "[+] Escriba otro número: "
  read -r n2
  if ((n1*n2)); then
    echo "[+] La multiplicación de $n1 * $n2 = $((n1*n2))"
  else
    echo "[-] Valores no válidos"
  fi
}

function división() {
  n1=0
  n2=0
  echo "DIVIDIR"
  echo "-------"
  echo -n "[+] Escriba un número: "
  read -r n1
  echo -n "[+] Escriba otro número: "
  read -r n2
  if ((n1/n2)); then
    echo "[+] La división de $n1 / $n2 = $((n1/n2))"
  else
    echo "[-] Valores no válidos"
  fi
}

function elevar() {
  n1=0
  n2=0
  echo "ELEVAR"
  echo "------"
  echo -n "[+] Escriba un número: "
  read -r n1
  echo -n "[+] Escriba otro número: "
  read -r n2
  if ((n1**n2)); then
    echo "[+] $n1 elevado a $n2 = $((n1**n2))"
  else
    echo "[-] Valores no válidos"
  fi
}

function salir() {
    echo "[+] Saliendo del programa..."
    exit 0
}

clear
echo "CALCULADORA"
echo "-----------"
input=0
select input in sumar restar multiplicación división elevar salir ; do
  $input
done