package com.oranet.aniversarioapi.domain.repository;

import com.oranet.aniversarioapi.domain.model.Falecido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FalecidoRepository extends JpaRepository<Falecido, Long> {
}
