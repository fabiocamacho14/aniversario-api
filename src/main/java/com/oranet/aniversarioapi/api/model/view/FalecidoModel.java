package com.oranet.aniversarioapi.api.model.view;

import lombok.Getter;
import lombok.Setter;

import java.time.Period;

@Getter
@Setter
public class FalecidoModel {

    private PessoaResumoModel pessoa;
    private Period periodoFalecimento;
    private Integer idadeQueFaleceu;
}
