package com.oranet.aniversarioapi.api.model.input;

import com.oranet.aniversarioapi.domain.model.Sexo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class PessoaInput {

    @NotNull
    @NotBlank
    private String nome;

    private String descricao;

    @NotNull
    private Sexo sexo;

    @Valid
    @NotNull
    private AniversarioInput aniversario;

    @Valid
    private Set<GrupoSocialIdInput> gruposSociais;
}
