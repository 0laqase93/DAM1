#!/bin/bash

# Crea un script que borre todos los ficheros pasados como parámetros, el script
# comprobará si el fichero existe y mostrará un resumen de los ficheros borrados con
# éxito y los ficheros que no existen.

clear

echo -e "ARCHIVOS ELIMINADOS\n-------------------" > archivosEliminados.fich
echo -e "ARCHIVOS NO ELIMINADOS\n----------------------" > archivosNoEliminados.fich

if [ $# -ne 0 ]; then
  for i in $* ; do
    if rm "$i" 2>/dev/null; then
      echo -e "[+] Archivo $i eliminado con éxito" >> archivosEliminados.fich
    else
      echo -e "[-] El archivo $i no se ha borrado" >> archivosNoEliminados.fich
    fi
  done
else
  echo -e "[!] Se deben pasar los archivos que se quieren borrar como parámetro."
fi
