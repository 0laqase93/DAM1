#!/bin/bash

# Crea un script que genere un número aleatorio entre el 0 y el 100. Tienes 5 intentos
# para encontrar el número, el programa te irá diciendo si el número que ha generado
# es mayor o menor que el introducido y el número de intentos que te quedan. Ayuda:
# para generar un número aleatorio entre el 0 y el 100 se usa $[RANDOM%100].

clear

declare -i vidas=5
declare -i random=$[RANDOM%100]
declare -i input=0
echo "[+] Encuentra el número entre el 1-100"
while [ $vidas -ne 0 ]; do
  vidas=$vidas-1
  read input
  if [ $input -lt $random ]; then
    echo "[⇧] El número es más grande"
    echo "[+] Le quedan $vidas vidas"
  elif [ $input -gt $random ]; then
    echo "[⇩] El número es más pequeño"
    echo "[+] Le quedan $vidas vidas"
  else
    echo "[++] Felicidades, lo has adivinado"
    vidas=0
  fi
done

if [ $vidas -eq 0 ]; then
    echo "[!] Buen intento, casi lo has conseguido"
fi