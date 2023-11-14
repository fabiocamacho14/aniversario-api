package com.oranet.aniversarioapi.api.assembler;

import com.oranet.aniversarioapi.api.model.PessoaModel;
import com.oranet.aniversarioapi.domain.model.Pessoa;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PessoaModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public PessoaModel toModel(Pessoa pessoa) {
        return modelMapper.map(pessoa, PessoaModel.class);
    }

    public List<PessoaModel> toCollectionModel(List<Pessoa> pessoas) {
        return pessoas.stream()
                .map(this::toModel)
                .toList();
    }
}
