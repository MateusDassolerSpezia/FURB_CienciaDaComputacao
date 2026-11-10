#!/bin/bash
arquivo="$1"

if [ -z "$arquivo" ]; then
    echo "Erro, arquivo faltando"
    exit 1
fi

if [ ! -f "$arquivo" ]; then
    echo "Erro: arquivo não encontrado"
    exit 1
fi

grep "incerteza" "$arquivo"