package com.oranet.aniversarioapi.api.assembler.disassembler;

import com.oranet.aniversarioapi.api.model.input.AniversarioInput;
import com.oranet.aniversarioapi.domain.model.Aniversario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AniversarioInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Aniversario toDomainObject(AniversarioInput aniversarioInput) {
        return modelMapper.map(aniversarioInput, Aniversario.class);
    }
}
