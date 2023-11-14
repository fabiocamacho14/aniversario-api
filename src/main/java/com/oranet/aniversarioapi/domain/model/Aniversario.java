package com.oranet.aniversarioapi.domain.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.Period;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Aniversario {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DATA_ANIVERSARIO", columnDefinition = "datetime")
    private OffsetDateTime dataAniversario;

    @Column(name = "desejo")
    @Lob
    private String desejo;

    @Column(name = "proximo_aniversario_em", nullable = false)
    private Period proximoAniversario;

    @CreationTimestamp
    @Column(name = "data_criacao", nullable = false, columnDefinition = "datetime")
    private OffsetDateTime dataCriacao;

    @UpdateTimestamp
    @Column(name = "data_ultima_atualizacao", nullable = false, columnDefinition = "datetime")
    private OffsetDateTime dataUltimaAtualizacao;

    public Boolean fezAniversarioEsseAno() {
        return dataAniversario.toLocalDate().isBefore(LocalDate.now());
    }
}
