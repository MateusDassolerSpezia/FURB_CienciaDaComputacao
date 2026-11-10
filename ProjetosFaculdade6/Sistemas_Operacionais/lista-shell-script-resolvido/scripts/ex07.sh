#!/bin/bash
diretorio="$1"
gerar_log="$2"

if [ ! -d "$diretorio" ] || { [ "$gerar_log" != "0" ] && [ "$gerar_log" != "1" ]; }; then
    echo "Uso: ./scripts/ex07.sh diretorio 0|1"
    exit 1
fi

mkdir -p "$HOME/backup"
data=$(date +%d-%m-%Y)
destino="$HOME/backup/backup-$data.tar.bz2"
inicio=$(date +%H:%M:%S)

if ! tar -cjf "$destino" -C "$diretorio" .; then
    echo "Erro ao criar backup"
    exit 1
fi

echo "Backup criado: $destino"

if [ "$gerar_log" = "1" ]; then
    {
        echo "Execução do backup - $(date +%d/%m/%Y)"
        echo "Horário de início - $inicio"
        echo
        echo "Arquivos compactados:"
        find "$(realpath "$diretorio")" -type f
        echo
        echo "Diretório de backup: $HOME/backup/"
        echo
        echo "Horário da Finalização do backup - $(date +%H:%M:%S)"
    } > "$HOME/backup/logBKP"
    echo "Log criado: $HOME/backup/logBKP"
fi
