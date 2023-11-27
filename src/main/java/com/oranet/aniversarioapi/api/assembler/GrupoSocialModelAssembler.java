package com.oranet.aniversarioapi.api.assembler;

import com.oranet.aniversarioapi.api.model.view.GrupoSocialModel;
import com.oranet.aniversarioapi.domain.model.GrupoSocial;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GrupoSocialModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public GrupoSocialModel toModel(GrupoSocial grupoSocial) {
        return modelMapper.map(grupoSocial, GrupoSocialModel.class);
    }

    public List<GrupoSocialModel> toCollectionModel(Collection<GrupoSocial> gruposSociais) {
        return gruposSociais.stream()
                .map(this::toModel).collect(Collectors.toList());
    }
}
