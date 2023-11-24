package com.oranet.aniversarioapi.api.model.view;

import com.oranet.aniversarioapi.domain.model.GrupoSocial;
import com.oranet.aniversarioapi.domain.model.Sexo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PessoaModel {

    private String nome;
    private String descricao;
    private Integer idade;
    private Boolean isMaiorDeIdade;
    private Sexo sexo;
    private List<GrupoSocial> gruposSociais;
    private AniversarioModel aniversario;
}
