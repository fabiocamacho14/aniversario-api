package com.oranet.aniversarioapi.domain.model;

import com.oranet.aniversarioapi.domain.model.converter.PeriodConverter;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.time.Period;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Falecido {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_falecimento", nullable = false, columnDefinition = "datetime")
    private OffsetDateTime dataFalecimento;

    @Column(name = "periodo_falecimento", nullable = false)
    @Convert(converter = PeriodConverter.class)
    private Period periodoFalecimento;

    @Column(name = "idade_que_faleceu", nullable = false)
    private Integer idadeQueFaleceu;

    //    Relacionamentos
    @MapsId
    @OneToOne()
    @JoinColumn(name = "pessoa_id", nullable = false, unique = true, foreignKey =
    @ForeignKey(name = "FK_FALECIDO_PESSOA", foreignKeyDefinition = "FOREIGN KEY (pessoa_id) REFERENCES Pessoa(aniversario_id)"))
    private Pessoa pessoa;
}

