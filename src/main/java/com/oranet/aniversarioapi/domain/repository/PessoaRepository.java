package com.oranet.aniversarioapi.domain.repository;

import com.oranet.aniversarioapi.domain.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    @Query("select p from Pessoa p join fetch p.aniversario a join fetch p.gruposSociais g where p.aniversario = :pessoaId")
    Optional<Pessoa> findById(Long pessoaId);

    @Query("select p from Pessoa p join fetch p.aniversario a join fetch p.gruposSociais g")
    List<Pessoa> findAll();
}
