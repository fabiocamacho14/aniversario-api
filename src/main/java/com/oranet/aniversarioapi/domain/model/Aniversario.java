package com.oranet.aniversarioapi.domain.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

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

    @MapsId
    @OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    @JoinColumn(name = "pessoa_id", nullable = false, unique = true, foreignKey = @ForeignKey(name = "FK_ANIVERSARIO_PESSOA"))
    private Pessoa pessoa;
}
