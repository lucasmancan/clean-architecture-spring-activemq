package br.com.alelo.estrutura.exception;

public class CustomException extends RuntimeException {

    public CustomException(String mensagem) {
        super(mensagem);
    }
}
