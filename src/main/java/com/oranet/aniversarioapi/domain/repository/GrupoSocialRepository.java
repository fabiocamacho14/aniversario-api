package com.oranet.aniversarioapi.domain.repository;

import com.oranet.aniversarioapi.domain.model.GrupoSocial;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashSet;
import java.util.Set;

@Repository
public interface GrupoSocialRepository extends CustomJpaRepository<GrupoSocial, Long>{

//    @Query("select gs from GrupoSocial gs order by gs.id asc")
//    LinkedHashSet<GrupoSocial> findAllSet();
}
