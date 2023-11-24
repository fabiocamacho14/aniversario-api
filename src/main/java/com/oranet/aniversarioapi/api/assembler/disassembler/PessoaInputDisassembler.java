package com.oranet.aniversarioapi.api.assembler.disassembler;

import com.oranet.aniversarioapi.api.model.input.PessoaInput;
import com.oranet.aniversarioapi.domain.model.Pessoa;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PessoaInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Pessoa toDomainObject(PessoaInput pessoaInput) {
        return modelMapper.map(pessoaInput, Pessoa.class);
    }
}
