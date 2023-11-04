package com.oranet.aniversarioapi.domain.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.Period;
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

    @Column(name = "idade", nullable = false, insertable = false)
    private Integer idade;

    @Column(name = "proximo_aniversario_em", nullable = false, insertable = false)
    private Period proximoAniversario;

    @Column(name = "falecido", nullable = false)
    private Boolean isFalecido = Boolean.FALSE;

    @Column(name = "maior_de_idade", nullable = false)
    private Boolean isMaiorDeIdade;

    @Column(name = "desejo")
    @Lob
    private String desejo;

    @Column(name = "sexo", nullable = false)
    @Enumerated(EnumType.STRING)
    private Sexo sexo;

//    Relacionamentos
    @OneToOne(mappedBy = "pessoa")
    private Aniversario aniversario;

    @OneToOne(mappedBy = "pessoa")
    private Falecido falecido;

    @ManyToMany
    @JoinTable(name = "pessoa_grupos_sociais",
                    joinColumns = @JoinColumn(name = "pessoa_id", nullable = false, foreignKey = @ForeignKey(name = "FK_PESSOA_GRUPOS_SOCIAIS_PESSOA")),
                    inverseJoinColumns = @JoinColumn(name = "grupo_social_id", nullable = false, foreignKey = @ForeignKey(name = "FK_GRUPOS_SOCIAIS_GRUPO_SOCIAL")))
    private Set<GrupoSocial> gruposSociais = new HashSet<>();
}
