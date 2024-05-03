#!/bin/bash

# Realiza un script al que se le pase una palabra como parámetro y devuelva la palabra
# al revés.

if [ $# -eq 1 ]; then
  echo "$1" | rev
else
  echo "[!] Se debe pasar una palabra como parámetro"
fi