#!/bin/bash

arquivo="$1"

if [ -f "$arquivo" ]; then
    echo "O arquivo existe"
else
    echo "Erro: arquivo não encontrado"
    exit 1
fi

echo "Arquivo informado: $arquivo"

contador=0
conteudo=$(cat "$arquivo")

for palavras in $conteudo
do
    contador=$((contador + 1))
done

echo "$contador palavras"