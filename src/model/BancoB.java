package model;

public class BancoB implements Banco {
    private static final double LIMITE = 500.00;

    @Override
    public boolean sacar(Conta conta, double valor) {
        if (conta.getSaldo() + LIMITE >= valor) {
            conta.setSaldo(conta.getSaldo() - valor);
            System.out.println("Saque realizado com sucesso.");
            return true;
        }
        System.out.println("Saldo insuficiente com limite.");
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
}
