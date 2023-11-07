package com.oranet.aniversarioapi.domain.exception;

public class AniversarioNaoEncontradoException extends EntidadeNaoEncontradaException {

    public AniversarioNaoEncontradoException(String message) {
        super(message);
    }

    public AniversarioNaoEncontradoException(Long aniversarioId) {
        this(String.format("Aniversário de id %d não foi encontrado.", aniversarioId));
    }
}
