package com.oranet.aniversarioapi.domain.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany(mappedBy = "gruposSociais")
    private Set<Pessoa> pessoas = new HashSet<>();
}
