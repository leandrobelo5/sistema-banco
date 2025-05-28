package model;

import exceptions.SaldoInsuficienteException;
import exceptions.ValorInvalidoException;

public class BancoA implements Banco {

    @Override
    public boolean sacar(Conta conta, double valor) {
        try {
            if (valor <= 0) {
                throw new ValorInvalidoException("O valor do saque deve ser maior que zero.");
            }

            if (conta.getSaldo() < valor) {
                throw new SaldoInsuficienteException("Saldo insuficiente para saque.");
            }

            conta.setSaldo(conta.getSaldo() - valor);
            System.out.println("Saque realizado com sucesso.");
            return true;
        } catch (ValorInvalidoException | SaldoInsuficienteException e) {
            System.out.println("Erro ao sacar: " + e.getMessage());
            return false;
        }
    }

    @Override
    public void depositar(Conta conta, double valor) {
        try {
            if (valor <= 0) {
                throw new ValorInvalidoException("O valor do depósito deve ser maior que zero.");
            }

            conta.setSaldo(conta.getSaldo() + valor);
            System.out.println("Depósito realizado com sucesso.");
        } catch (ValorInvalidoException e) {
            System.out.println("Erro ao depositar: " + e.getMessage());
        }
    }

    @Override
    public void extrato(Conta conta) {
        System.out.printf("Extrato da conta (CPF: %s): Saldo R$ %.2f%n", conta.getCpf(), conta.getSaldo());
    }

    public boolean transferenciaConta(Conta origem, Conta destino, double valor) {
        try {
            if (valor <= 0) {
                throw new ValorInvalidoException("O valor da transferência deve ser maior que zero.");
            }

            if (!sacar(origem, valor)) {
                throw new SaldoInsuficienteException("Transferência falhou por saldo insuficiente.");
            }

            depositar(destino, valor);
            System.out.println("Transferência realizada com sucesso.");
            return true;
        } catch (ValorInvalidoException | SaldoInsuficienteException e) {
            System.out.println("Erro na transferência: " + e.getMessage());
            return false;
        }
    }
}
