package com.oranet.aniversarioapi.domain.repository;

import com.oranet.aniversarioapi.domain.model.Falecido;
import com.oranet.aniversarioapi.domain.model.Pessoa;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FalecidoRepository extends CustomJpaRepository<Falecido, Long> {

    Optional<Falecido> findByPessoa(Pessoa pessoa);

    @Modifying
    @Query("delete from Falecido f where f.pessoa.id = :pessoaId")
    void deleteByPessoaId(Long pessoaId);
}
