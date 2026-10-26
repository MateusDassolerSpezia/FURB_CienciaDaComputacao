/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package questao_extra;

import javax.swing.JOptionPane;

/**
 *
 * @author mdspezia
 */
public class App {

    private ContaBancaria[] contas;
    private int quantidadeContas;

    public App() {
        contas = new ContaBancaria[10];
        int opcao = 0;

        do {
            String retorno = JOptionPane.showInputDialog("Digite uma opção:\n1- Criar conta bancaria\n2- Sacar\n3- Depositar\n4- transferir\n5- Sair");
            opcao = Integer.parseInt(retorno);
            switch (opcao) {
                case 1:
                    criarConta();

                    break;

                case 2:
                    sacar();
                    break;
                case 3:
                    depositar();
                    break;
                case 4:
                    transferir();
                    break;
                case 5:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida");
            }
        } while (opcao != 5);

    }

    public static void main(String[] args) {
        new App();
    }

    private void criarConta() {
        String numeroConta = JOptionPane.showInputDialog("Número da conta: ");

        if (buscar(numeroConta) == null && quantidadeContas < contas.length) {
            String titular = JOptionPane.showInputDialog("Titular: ");
            ContaBancaria conta = new ContaBancaria(numeroConta, titular);
            //conta.setNumero(numeroConta);
            //conta.setTitular(titular);
            contas[quantidadeContas] = conta;
            quantidadeContas++;

            JOptionPane.showMessageDialog(null, "Conta criada");
        } else {
            JOptionPane.showMessageDialog(null, "Conta ja existe ou já está cheio");
        }
    }

    private ContaBancaria buscar(String numeroConta) {
        /*for (int i = 0; i < contas.length; i++) {
            ContaBancaria c = contas[i];
        if (c.getNumero() == numeroConta) {
            return i;
        }
         */
        for (ContaBancaria c : contas) {
            if (c != null && c.getNumero().trim().equalsIgnoreCase(numeroConta)) {
                return c;
            }
        }
        return null;
    }

    private void sacar() {
        String numeroConta = JOptionPane.showInputDialog("Número da conta: ");
        ContaBancaria conta = buscar(numeroConta);
        if (conta != null) {
            boolean deuErro = true;
            do {
                float valor = Float.parseFloat(JOptionPane.showInputDialog("Valor do saque: "));

                try {
                    conta.sacar(valor);
                    JOptionPane.showMessageDialog(null, "Saldo R$ " + conta.getSaldo());
                    deuErro = false;
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            } while (deuErro && conta.getSaldo() > 0);
        } else {
            JOptionPane.showMessageDialog(null, "Conta não existe");
        }
    }

    private void depositar() {
        String numeroConta = JOptionPane.showInputDialog("Número da conta: ");
        ContaBancaria conta = buscar(numeroConta);
        if (buscar(numeroConta) != null) {
            float valor = Float.parseFloat(JOptionPane.showInputDialog("Valor do saque: "));
            conta.depositar(valor);
            JOptionPane.showMessageDialog(null, "Saldo R$ " + conta.getSaldo());
        } else {
            JOptionPane.showMessageDialog(null, "Conta não existe");
        }
    }

    private void transferir() {
        String numeroContaOrigem = JOptionPane.showInputDialog("Número da conta: ");
        ContaBancaria contaOrigem = buscar(numeroContaOrigem);
        if (contaOrigem != null) {
            String numeroContaDestino = JOptionPane.showInputDialog("Número da conta de depósito: ");
            ContaBancaria contaDestino = buscar(numeroContaDestino);
            if (contaDestino != null) {
                float valor = Float.parseFloat(JOptionPane.showInputDialog("Valor da transferência: "));
                contaOrigem.transferir(contaDestino, valor);
                JOptionPane.showMessageDialog(null, "Saldo R$" + contaOrigem.getSaldo());
                JOptionPane.showMessageDialog(null, "Saldo Destino R$" + contaDestino.getSaldo());
            } else {
                JOptionPane.showMessageDialog(null, "Conta destino não existe");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Conta origem não existe");
        }
    }
}
