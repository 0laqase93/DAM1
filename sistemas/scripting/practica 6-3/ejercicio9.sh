#!/bin/bash

# Realiza un script que te calcule el precio del billete del tren. El trayecto del tren cuesta
# 30 euros. Si se realiza un trayecto de ida y vuelta tiene un descuento del 20%.

clear
billete=30
descuento=$(("$billete"*2*20/100))
input=""
precio=0

echo -n "[?] ¿Quiere el billete de ida y vuelta?(S/N): "
read -r input
if [ "$input" = "N" ]; then
  precio=$billete
elif [ "$input" = "S" ]; then
  precio=$(("$billete" * 2 - "$descuento"))
else
  echo "[!] Valor no válido"
  exit 1
fi

echo "[+] Su billete vale $precio €"