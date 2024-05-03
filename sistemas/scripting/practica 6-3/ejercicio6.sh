#!/bin/bash

clear

vidas=5
random=$((RANDOM%100))
input=0
echo "[+] Encuentra el número entre el 1-100"
while [ $vidas -ne 0 ]; do
  vidas=$vidas-1
  echo -n "[+] Inserte su adivinación: "
  read -r input

  if [[ $input != [!0-9]* || -z $input ]]; then
    if [ "$input" -lt "$random" ] 2>/dev/null; then
      echo "[⇧] El número es más grande"
      echo "[+] Le quedan $vidas vidas"
    elif [ "$input" -gt "$random" ] 2>/dev/null; then
      echo "[⇩] El número es más pequeño"
      echo "[+] Le quedan $vidas vidas"
    elif [ "$input" -eq "$random" ] 2>/dev/null; then
      echo "[++] Felicidades, lo has adivinado"
      vidas=0
    else
      echo "[-] Valores no válidos, por gracioso te quedas sin vidas"
      vidas=0
    fi
  else
    echo "[-] Valores no válidos, por gracioso te quedas sin vidas"
    vidas=0
  fi
done

if [ $vidas -eq 0 ]; then
    echo "[+] Buen intento, casi lo has conseguido, el número era $random"
fi
