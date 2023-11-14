package com.oranet.aniversarioapi.domain.repository;

import com.oranet.aniversarioapi.domain.model.Pessoa;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PessoaRepositoryCustom {

    Optional<Pessoa> findById(Long pessoaId);
    List<Pessoa> findAll();
}
