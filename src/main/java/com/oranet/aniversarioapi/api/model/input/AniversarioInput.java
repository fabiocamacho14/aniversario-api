package com.oranet.aniversarioapi.api.model.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class AniversarioInput {

    @NotNull
    private OffsetDateTime dataAniversario;

    private String desejo;
}
