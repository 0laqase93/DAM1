#!/bin/bash

# Realizar un script que dado un número 'n' muestre los diez primeros elementos de su
# tabla de multiplicar, mostrando el resultado en la forma: i x n = resultado. Emplear un
# bucle for con seq. Si no se proporciona un número, mostrar cómo se usa el programa.

clear

input=0

echo -en "[+] Ingrese un número: "
read -r input
if [ "$input" -eq "$input" ] 2>/dev/null; then
  if [ "$input" -gt 0 ]; then
    echo -e "----------------"
    echo -e "[+] Tabla del $input"
    echo -e "----------------"
    for i in $(seq 1 10) ; do
      echo -e "$input x $i = $((input*i))"
    done
    echo -e "----------------"
  else
    echo -e "[!] Se debe ingresar un número mayor a 0."
  fi
else
  echo -e "[!] Se debe ingresar un número"
fi
