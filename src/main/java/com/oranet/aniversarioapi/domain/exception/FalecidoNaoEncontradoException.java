package com.oranet.aniversarioapi.domain.exception;

public class FalecidoNaoEncontradoException extends EntidadeNaoEncontradaException {


    public FalecidoNaoEncontradoException(String message) {
        super(message);
    }

    public FalecidoNaoEncontradoException(Long pessoaId) {
        this(String.format("Falecido de código %s não encontrado.", pessoaId));
    }
}
