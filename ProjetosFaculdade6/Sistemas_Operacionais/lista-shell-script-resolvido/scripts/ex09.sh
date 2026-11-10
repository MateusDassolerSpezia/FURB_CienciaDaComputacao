#!/bin/bash
hora=$(date +%H)

if [ "$hora" -lt 12 ]; then
    echo "Bom dia"
elif [ "$hora" -lt 18 ]; then
    echo "Boa tarde"
else
    echo "Boa noite"
fi