#!/bin/bash

while true
do
    read -r _ u n s i w irq sirq st _ < /proc/stat
    total1=$((u + n + s + i + w + irq + sirq + st))
    ocioso1=$((i + w))

    sleep 1

    read -r _ u n s i w irq sirq st _ < /proc/stat
    total2=$((u + n + s + i + w + irq + sirq + st))
    ocioso2=$((i + w))

    diferenca=$((total2 - total1))
    if [ "$diferenca" -gt 0 ]; then
        uso=$((100 * (diferenca - ocioso2 + ocioso1) / diferenca))
        if [ "$uso" -gt 50 ]; then
            echo "Aviso: uso da CPU acima de 50% ($uso%)"
        fi
    fi
done