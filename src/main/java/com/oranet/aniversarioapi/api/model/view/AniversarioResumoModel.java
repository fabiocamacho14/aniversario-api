package com.oranet.aniversarioapi.api.model.view;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class AniversarioResumoModel {

    private Long id;
    private OffsetDateTime dataAniversario;
}
