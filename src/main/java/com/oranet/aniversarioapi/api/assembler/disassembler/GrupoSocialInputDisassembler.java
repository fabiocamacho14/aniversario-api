package com.oranet.aniversarioapi.api.assembler.disassembler;

import com.oranet.aniversarioapi.api.model.input.GrupoSocialInput;
import com.oranet.aniversarioapi.domain.model.GrupoSocial;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GrupoSocialInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public GrupoSocial toDomainObject(GrupoSocialInput grupoSocialInput) {
        return modelMapper.map(grupoSocialInput, GrupoSocial.class);
    }
}
