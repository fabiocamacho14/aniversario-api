package com.oranet.aniversarioapi.domain.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
@Table(name = "grupo_social")
public class GrupoSocial {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao")
    @Lob
    private String descricao;

    @Column(name = "nivel_importancia", nullable = false)
    @Enumerated(EnumType.STRING)
    private NivelImportancia nivelImportancia;

    @CreationTimestamp
    @Column(name = "data_criacao", nullable = false, columnDefinition = "datetime")
    private OffsetDateTime dataCriacao;

    @UpdateTimestamp
    @Column(name = "data_ultima_atualizacao", nullable = false, columnDefinition = "datetime")
    private OffsetDateTime dataUltimaAtualizacao;
}
