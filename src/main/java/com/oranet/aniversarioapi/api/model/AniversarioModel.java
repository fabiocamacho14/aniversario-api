package com.oranet.aniversarioapi.api.model;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.time.Period;

@Getter
@Setter
public class AniversarioModel {

    private Long id;
    private OffsetDateTime dataAniversario;
    private String desejo;
    private Period proximoAniversario;
}
