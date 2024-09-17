package app;

import java.util.InputMismatchException;
import java.util.Scanner;

import pkg.Cliente;
import pkg.Conta;
import pkg.ContaCorrente;
import pkg.ContaPoupanca;

public class Program {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Cliente cliente = obterCliente();
        Conta cc = criarContaCorrente(cliente);
        Conta cp = criarContaPoupanca(cliente);

        exibirInformacoes(cc, cp);

        while (true) {
            switch (mostrarMenu()) {
                case 1:
                    realizarDeposito(cc);
                    break;
                case 2:
                    realizarSaque(cc);
                    break;
                case 3:
                    realizarTransferencia(cc, cp);
                    break;
                case 4:
                    exibirInformacoes(cc, cp);
                    break;
                case 5:
                    System.out.println("Saindo...");
                    sc.close();
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }

    private static Cliente obterCliente() {
        System.out.println("Digite o seu nome:");
        String nome = sc.nextLine();

        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        return cliente;
    }

    private static Conta criarContaCorrente(Cliente cliente) {
        System.out.println("Digite o valor que deseja depositar na conta corrente:");
        double valor = lerValor();
        Conta cc = new ContaCorrente(cliente);
        cc.depositar(valor);
        return cc;
    }

    private static Conta criarContaPoupanca(Cliente cliente) {
        System.out.println("Digite o valor que deseja depositar na conta poupança:");
        double valor = lerValor();
        Conta cp = new ContaPoupanca(cliente);
        cp.depositar(valor);
        return cp;
    }

    private static void exibirInformacoes(Conta cc, Conta cp) {
        System.out.println("\nInformações:");
        cc.imprimirExtrato();
        System.out.println();
        cp.imprimirExtrato();
    }

    private static int mostrarMenu() {
        System.out.println("Escolha uma opção:");
        System.out.println("1 - Depositar em Conta Corrente");
        System.out.println("2 - Sacar de Conta Corrente");
        System.out.println("3 - Transferir entre Contas");
        System.out.println("4 - Imprimir extratos");
        System.out.println("5 - Sair");
        return lerInteiro();
    }

    private static void realizarDeposito(Conta cc) {
        System.out.println("Digite o valor a ser depositado na Conta Corrente:");
        double valor = lerValor();
        cc.depositar(valor);
    }

    private static void realizarSaque(Conta cc) {
        System.out.println("Digite o valor a ser sacado da Conta Corrente:");
        double valor = lerValor();
        cc.sacar(valor);
    }

    private static void realizarTransferencia(Conta cc, Conta cp) {
        System.out.println("Digite o valor a ser transferido:");
        double valor = lerValor();
        System.out.println("Escolha a conta destino:");
        System.out.println("1 - Conta Corrente");
        System.out.println("2 - Conta Poupança");
        int destino = lerInteiro();

        switch (destino) {
            case 1:
                if (cc == cp) {
                    System.out.println("Não é possível transferir para a mesma conta.");
                } else {
                    cc.transferir(valor, cp);
                }
                break;
            case 2:
                cc.transferir(valor, cp);
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }
    }

    private static double lerValor() {
        while (true) {
            try {
                return sc.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número válido.");
                sc.next();
            }
        }
    }

    private static int lerInteiro() {
        while (true) {
            try {
                return sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número válido.");
                sc.next();
            }
        }
    }
}
