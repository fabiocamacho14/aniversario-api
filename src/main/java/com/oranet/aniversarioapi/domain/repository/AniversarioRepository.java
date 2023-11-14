package com.oranet.aniversarioapi.domain.repository;

import com.oranet.aniversarioapi.domain.model.Aniversario;
import org.springframework.stereotype.Repository;

@Repository
public interface AniversarioRepository extends CustomJpaRepository<Aniversario, Long> {
}
