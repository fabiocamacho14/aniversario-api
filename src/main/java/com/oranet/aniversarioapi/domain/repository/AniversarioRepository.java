package com.oranet.aniversarioapi.domain.repository;

import com.oranet.aniversarioapi.domain.model.Aniversario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AniversarioRepository extends JpaRepository<Aniversario, Long> {
}
