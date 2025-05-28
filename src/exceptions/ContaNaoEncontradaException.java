package exceptions;

public class ContaNaoEncontradaException extends Exception {
    public ContaNaoEncontradaException(String cpf) {
        super("Conta com o CPF " + cpf + " não foi encontrada.");
    }
}
