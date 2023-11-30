package com.oranet.aniversarioapi.api.model.view;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaResumoModel {

    private String nome;
    private AniversarioResumoModel aniversario;
}
