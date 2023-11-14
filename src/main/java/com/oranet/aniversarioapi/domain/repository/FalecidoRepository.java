package com.oranet.aniversarioapi.domain.repository;

import com.oranet.aniversarioapi.domain.model.Falecido;
import org.springframework.stereotype.Repository;

@Repository
public interface FalecidoRepository extends CustomJpaRepository<Falecido, Long> {
}
