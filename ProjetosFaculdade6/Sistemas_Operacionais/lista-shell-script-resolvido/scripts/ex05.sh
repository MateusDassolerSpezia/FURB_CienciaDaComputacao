#!/bin/bash
arquivo="$1"

if [ ! -f "$arquivo" ]; then
    echo "Erro: arquivo não encontrado"
    exit 1
fi

mkdir -p saida
pasta=$(mktemp -d)
trap 'rm -rf "$pasta"' EXIT

tar -xf "$arquivo" -C "$pasta" || exit 1

: > saida/poema_final.txt
for palavra in incerta ódio ilude
do
    frase=$(find "$pasta" -type f -name '*.txt' -exec grep -hi -m 1 "$palavra" {} + | head -n 1)
    if [ -z "$frase" ]; then
        echo "Erro: frase com '$palavra' não encontrada"
        exit 1
    fi
    echo "$frase" >> saida/poema_final.txt
done

gzip -c saida/poema_final.txt > saida/poema_final.txt.gz
echo "Criados saida/poema_final.txt e saida/poema_final.txt.gz"