package domain.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.time.OffsetDateTime;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Aniversario {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DATA_ANIVERSARIO", columnDefinition = "datetime")
    private OffsetDateTime dataAniversario;
}
