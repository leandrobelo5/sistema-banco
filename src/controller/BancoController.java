package controller;

import dao.ContaDAO;
import model.Banco;
import model.Conta;

public class BancoController {
    private final Banco banco;
    private final ContaDAO contaDAO;

    public BancoController(Banco banco) {
        this.banco = banco;
        this.contaDAO = new ContaDAO();
    }

    public void sacar(String cpf, double valor) {
        Conta conta = contaDAO.buscarContaPorCPF(cpf);
        if (conta != null && banco.sacar(conta, valor)) {
            contaDAO.atualizarSaldo(conta);
        }
    }

    public void depositar(String cpf, double valor) {
        Conta conta = contaDAO.buscarContaPorCPF(cpf);
        if (conta != null) {
            banco.depositar(conta, valor);
            contaDAO.atualizarSaldo(conta);
        }
    }

    public void extrato(String cpf) {
        Conta conta = contaDAO.buscarContaPorCPF(cpf);
        if (conta != null) {
            banco.extrato(conta);
        }
    }
}