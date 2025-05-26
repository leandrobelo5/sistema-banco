package model;

public interface Banco {
    boolean sacar(Conta conta, double valor);
    void depositar(Conta conta, double valor);
    void extrato(Conta conta);
}
