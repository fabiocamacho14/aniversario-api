package com.oranet.aniversarioapi.api.model.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrupoSocialIdInput {

    @NotNull
    private Integer id;
}
