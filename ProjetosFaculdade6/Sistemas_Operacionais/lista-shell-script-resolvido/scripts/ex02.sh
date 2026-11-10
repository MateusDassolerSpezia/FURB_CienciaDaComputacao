#!/bin/bash
palavra="$1"
arquivo="$2"

if [ -z "$palavra" ] || [ ! -f "$arquivo" ]; then
    echo "Uso: ./scripts/ex02.sh palavra arquivo.txt"
    exit 1
fi

mkdir -p saida
sed "s/\\<$palavra\\>//g" "$arquivo" > saida/sem_palavra.txt
echo "Resultado salvo em saida/sem_palavra.txt"