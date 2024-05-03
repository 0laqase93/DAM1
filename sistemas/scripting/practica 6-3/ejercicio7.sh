#!/bin/bash

# Crea un script que muestre un menú donde la primera opción sea insertar números en
# un fichero (mostrarlos de manera ordenada), la segunda los borra del fichero
# (elegimos el número que queremos borrar) y por último sale del menú.
# Comprobar que los números no estén repetidos. Utilizamos el case

file="numeros.num" # Archivo donde se van a guardar los números

function ordenarArchivo() {
  sort -g $file | uniq > temp.num
  mv temp.num $file
}

function insertarNumero() {
  declare -i input=0
  echo -n "[+] Inserte un número: "
  read -r input
  if [[ $input != [!0-9]* || -z $input ]]; then
    echo "$input" >> $file
  else
    echo -n "[+] Solo se pueden poner números"
    read -r
  fi
}

function borrarNumero() {
  declare -i input=0
  echo -n "[+] Inserte un número: "
  read -r input
  if [[ $input != [!0-9]* || -z $input ]] ; then
    (tr "$input" ' ' > tmp.fl) < $file
    mv tmp.fl "$file"
  else
    echo -n "[+] Solo se pueden poner números"
    read -r
  fi
}

function mostrarMenu() {
  clear
  echo "+----------------------+"
  echo "|       OPCIONES       |"
  echo "+----------------------+"
  echo "| 1 -> Insertar número |"
  echo "| 2 -> Borrar número   |"
  echo "| 3 -> Salir           |"
  echo "+----------------------+"
  ordenarArchivo
  ordenarArchivo
  echo "Números: [$(tr '\n' ' ' < $file)]"
}

if  [ -f "$file" ]; then
  echo "" > "$file"
else
  touch "$file"
fi

declare -i input=0
while [ $input -ne 3 ]; do
  mostrarMenu
  echo -n "[+] Inserte una opción: "
  read -r input
  case $input in
    1)insertarNumero;;
    2)borrarNumero;;
    3)echo "[!] Saliendo del programa...";;
    *)echo -n "[!] Opción no válida"
      read -r;;
  esac
done