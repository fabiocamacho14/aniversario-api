package com.oranet.aniversarioapi.domain.exception;

public class GrupoSocialNaoEncontradoException extends EntidadeNaoEncontradaException {

    public GrupoSocialNaoEncontradoException(String message) {
        super(message);
    }

    public GrupoSocialNaoEncontradoException(Long grupoSocialId) {
        this(String.format("Grupo social de id %d não foi encontrado.", grupoSocialId));
    }
}
