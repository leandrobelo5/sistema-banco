package view;

import controller.BancoController;
import dao.ContaDAO;
import model.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ContaDAO contaDAO = new ContaDAO();
        Banco banco = null;

        System.out.println("=== Bem-vindo ao Sistema Bancário ===");
        System.out.print("Escolha o banco (A/B): ");
        String escolhaBanco = sc.nextLine().trim().toUpperCase();

        switch (escolhaBanco) {
            case "A" -> banco = new BancoA();
            case "B" -> banco = new BancoB();
            default -> {
                System.out.println("Banco inválido. Encerrando.");
                System.exit(0);
            }
        }

        BancoController controller = new BancoController(banco);

        int opcao;
        do {
            System.out.println("""
                    
                    ===== Menu =====
                    1 - Depositar
                    2 - Sacar
                    3 - Extrato
                    4 - Transferir (somente Banco A)
                    0 - Sair
                    """);
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); // consumir quebra de linha

            switch (opcao) {
                case 1 -> {
                    System.out.print("CPF da conta: ");
                    String cpf = sc.nextLine();
                    System.out.print("Valor do depósito: ");
                    double valor = sc.nextDouble();
                    controller.depositar(cpf, valor);
                }
                case 2 -> {
                    System.out.print("CPF da conta: ");
                    String cpf = sc.nextLine();
                    System.out.print("Valor do saque: ");
                    double valor = sc.nextDouble();
                    controller.sacar(cpf, valor);
                }
                case 3 -> {
                    System.out.print("CPF da conta: ");
                    String cpf = sc.nextLine();
                    controller.extrato(cpf);
                }
                case 4 -> {
                    if (banco instanceof BancoA bancoA) {
                        System.out.print("CPF da conta origem: ");
                        String cpfOrigem = sc.nextLine();
                        System.out.print("CPF da conta destino: ");
                        String cpfDestino = sc.nextLine();
                        System.out.print("Valor da transferência: ");
                        double valor = sc.nextDouble();

                        Conta origem = contaDAO.buscarContaPorCPF(cpfOrigem);
                        Conta destino = contaDAO.buscarContaPorCPF(cpfDestino);

                        if (origem != null && destino != null) {
                            if (bancoA.transferenciaConta(origem, destino, valor)) {
                                contaDAO.atualizarSaldo(origem);
                                contaDAO.atualizarSaldo(destino);
                            }
                        } else {
                            System.out.println("Conta(s) não encontrada(s).");
                        }
                    } else {
                        System.out.println("Transferência não disponível para Banco B.");
                    }
                }
                case 0 -> System.out.println("Encerrando sistema...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }
}
