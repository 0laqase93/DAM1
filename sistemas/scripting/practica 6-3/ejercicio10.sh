#!/bin/bash

# Realiza un script que reciba varios parámetros. El programa deberá indicar qué
# parámetros no son ficheros ni directorios. Al final mostrará la siguiente frase: “Se han
# introducido X parámetros, Y son ficheros y Z son directorios. Deberás averiguar el
# valor de X, Y y Z.

clear

parametros=$#
ficheros=0
directorios=0

if [ $# -eq 0 ]; then
  echo "[!] se deben pasar parámetros"
else
  for i in $* ; do
      if [ -f "$i" ]; then
        ficheros=$(("$ficheros"+1))
      else
        directorios=$(("$directorios"+1))
      fi
  done
  echo "[+] Se han introducido $parametros parámetros, $ficheros son ficheros y $directorios son directorios"
fi