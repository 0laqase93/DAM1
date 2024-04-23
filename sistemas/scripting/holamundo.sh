#!/bin/bash
# Mi primer shell script

clear
echo -e "Hola mundo \vme aburro"

let tres=3
echo $tres

read -p "[+] Introduzca tu nombre: " nombre
echo "¡Hola $nombre!"

echo "El resultado es igual a `expr 6 + 3`"