package com.oranet.aniversarioapi.api.model.input;

import com.oranet.aniversarioapi.domain.model.NivelImportancia;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrupoSocialInput {

    @NotNull
    @NotBlank
    private String nome;

    private String descricao;

    @NotNull
    private NivelImportancia nivelImportancia;
}
