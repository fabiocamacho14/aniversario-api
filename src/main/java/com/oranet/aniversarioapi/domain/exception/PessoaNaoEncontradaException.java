package com.oranet.aniversarioapi.domain.exception;

public class PessoaNaoEncontradaException extends EntidadeNaoEncontradaException {


    public PessoaNaoEncontradaException(String message) {
        super(message);
    }

    public PessoaNaoEncontradaException(Long pessoaId) {
        this(String.format("Pessoa de id %d não foi encontrada.", pessoaId));
    }
}
