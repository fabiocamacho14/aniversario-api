package com.oranet.aniversarioapi.domain.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.time.OffsetDateTime;
import java.time.Period;

@Entity
public class Falecido {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_falecimento", nullable = false, insertable = false)
    private OffsetDateTime dataFalecimento;

    @Column(name = "periodo_falecimento", nullable = false, insertable = false)
    private Period periodoFalecimento;

    @Column(name = "idade_que_faleceu", nullable = false, insertable = false)
    private Integer idadeQueFaleceu;


//    Relacionamentos
    @MapsId
    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "pessoa_id", nullable = false, unique = true, foreignKey = @ForeignKey(name = "FK_FALECIDO_PESSOA"))
    private Pessoa pessoa;
}
