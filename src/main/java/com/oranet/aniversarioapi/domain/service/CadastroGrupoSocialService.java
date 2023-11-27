package com.oranet.aniversarioapi.domain.service;

import com.oranet.aniversarioapi.domain.exception.GrupoSocialNaoEncontradoException;
import com.oranet.aniversarioapi.domain.model.GrupoSocial;
import com.oranet.aniversarioapi.domain.repository.GrupoSocialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroGrupoSocialService {

    @Autowired
    private GrupoSocialRepository grupoSocialRepository;

    @Transactional
    public GrupoSocial adicionar(GrupoSocial grupoSocial) {
        return grupoSocialRepository.save(grupoSocial);
    }

    @Transactional
    public void remover(Long grupoSocialId) {
        buscarOuFalhar(grupoSocialId);

        grupoSocialRepository.deleteById(grupoSocialId);
    }

    public GrupoSocial buscarOuFalhar(Long grupoSocialId) {
        return grupoSocialRepository.findById(grupoSocialId)
                .orElseThrow(() -> new GrupoSocialNaoEncontradoException(grupoSocialId));
    }
}
