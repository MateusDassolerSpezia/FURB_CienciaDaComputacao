#!/bin/bash
arquivo="$1"

if [ ! -f "$arquivo" ]; then
    echo "Erro: arquivo não encontrado"
    exit 1
fi

if [ "$(file -b --mime-encoding "$arquivo")" = "binary" ]; then
    echo "Erro: arquivo binário"
    exit 1
fi

mkdir -p saida
novo="saida/$(basename "$arquivo").utf8.txt"

if iconv -f ISO-8859-1 -t UTF-8 "$arquivo" > "$novo"; then
    echo "Conversão realizada: $novo"
else
    echo "Erro na conversão"
    exit 1
fi

read -r -p "Deseja remover o arquivo original? (s/n): " resposta
if [ "$resposta" = "s" ]; then
    if rm "$arquivo"; then
        echo "Arquivo original removido"
    else
        echo "Erro ao remover arquivo original"
        exit 1
    fi
fi