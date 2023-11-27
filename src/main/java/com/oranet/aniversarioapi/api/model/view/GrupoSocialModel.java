package com.oranet.aniversarioapi.api.model.view;

import com.oranet.aniversarioapi.domain.model.NivelImportancia;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrupoSocialModel {

    private Long id;

    private String descricao;

    private NivelImportancia nivelImportancia;

}
