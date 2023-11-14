package com.oranet.aniversarioapi.domain.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Pessoa {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descricao")
    @Lob
    private String descricao;

    @Column(name = "idade", nullable = false)
    private Integer idade;

    @Column(name = "falecido", nullable = false)
    private Boolean isFalecido = Boolean.FALSE;

    @Column(name = "maior_de_idade", nullable = false)
    private Boolean isMaiorDeIdade;

    @Column(name = "sexo", nullable = false)
    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @CreationTimestamp
    @Column(name = "data_criacao", nullable = false, columnDefinition = "datetime")
    private OffsetDateTime dataCriacao;

    @UpdateTimestamp
    @Column(name = "data_ultima_atualizacao", nullable = false, columnDefinition = "datetime")
    private OffsetDateTime dataUltimaAtualizacao;

    //    Relacionamentos
    @OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    @JoinColumn(name = "aniversario_id", nullable = false, unique = true, foreignKey = @ForeignKey(name = "FK_PESSOA_ANIVERSARIO"))
    @MapsId
    private Aniversario aniversario;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "pessoa_grupos_sociais",
                    joinColumns = @JoinColumn(name = "pessoa_id", nullable = false, foreignKey = @ForeignKey(name = "FK_PESSOA_GRUPOS_SOCIAIS_PESSOA")),
                    inverseJoinColumns = @JoinColumn(name = "grupo_social_id", nullable = false, foreignKey = @ForeignKey(name = "FK_GRUPOS_SOCIAIS_GRUPO_SOCIAL")))
    private Set<GrupoSocial> gruposSociais = new HashSet<>();
}
