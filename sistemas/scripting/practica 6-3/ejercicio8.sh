#!/bin/bash

# Crea un script que solicite un número y diga si ese número es primo o no. Un número
# es primo solo si es divisible por sí mismo y por 1.

esPrimo=true
echo -n "[+] Escriba un número para saber si en primo: "
read -r input
if [ "$input" -eq "$input" ] 2>/dev/null; then
  if [ "$input" -lt 2 ]; then
      esPrimo="false"
  else
      esPrimo="true"
      for (( i = 2; i < input; i++ )); do
          if [ $((input % i)) -eq 0 ]; then
              esPrimo="false"
              break
          fi
      done
  fi
  if [ $esPrimo = "true" ]; then
    echo "[+] El número $input es primo"
  else
    echo "[-] El número $input no es primo"
  fi
else
  echo "[!] Debe ingresar un número"
fi
