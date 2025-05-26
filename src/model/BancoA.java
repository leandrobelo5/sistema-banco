package model;

public class BancoA implements Banco {

    @Override
    public boolean sacar(Conta conta, double valor) {
        if (conta.getSaldo() >= valor) {
            conta.setSaldo(conta.getSaldo() - valor);
            System.out.println("Saque realizado com sucesso.");
            return true;
        }
        System.out.println("Saldo insuficiente.");
        return false;
    }

    @Override
    public void depositar(Conta conta, double valor) {
        conta.setSaldo(conta.getSaldo() + valor);
        System.out.println("Depósito realizado com sucesso.");
    }

    @Override
    public void extrato(Conta conta) {
        System.out.printf("Extrato da conta (CPF: %s): Saldo R$ %.2f%n", conta.getCpf(), conta.getSaldo());
    }

    public boolean transferenciaConta(Conta origem, Conta destino, double valor) {
        if (sacar(origem, valor)) {
            depositar(destino, valor);
            System.out.println("Transferência realizada com sucesso.");
            return true;
        }
        System.out.println("Transferência falhou.");
        return false;
    }
}
