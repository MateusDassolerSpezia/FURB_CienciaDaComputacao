#!/bin/bash

if [ "$(id -u)" -eq 0 ]; then
    echo "Olá, root"
    date '+%H:%M:%S - %d/%m/%Y'
    free -h | awk '/^Mem:/ {print "RAM livre: " $4; print "RAM em uso: " $3}'
else
    echo "Você não é root. Execute novamente como root ou com sudo."
fi