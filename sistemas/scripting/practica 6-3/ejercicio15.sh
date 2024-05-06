#!/bin/bash

# Realiza un script que muestre por pantalla todos los usuarios cuyo UID sea menor que
# 1000, los usuarios con UID mayor o igual a 1000 y un resumen con el total de cada
# grupo.

clear
# UID : cat /etc/passwd | cut -d ":" -f3
# Nombre : cat /etc/passwd |  grep "1000" | cut -d ":" -f1

menor1000=""
mayorIgual1000=""
lista=$(cut -d ":" -f3 < /etc/passwd)

for i in $lista ; do
  nombre=$(cat /etc/passwd | cut -d ":" -f1,3 | grep -w "$i" | cut -d ":" -f1)
    if [ "$i" -lt 1000 ]; then
      menor1000+=$i" - ""$nombre""\n"
    else
      mayorIgual1000+=$i" - ""$nombre""\n"
    fi
done

echo "+------------+"
echo "| UDI < 1000 |"
echo "+------------+"
echo -e "$menor1000"
echo "+-------------+"
echo "| UDI >= 1000 |"
echo "+-------------+"
echo -e "$mayorIgual1000"