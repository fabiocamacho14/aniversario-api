package com.oranet.aniversarioapi.api.assembler;

import com.oranet.aniversarioapi.api.model.view.AniversarioModel;
import com.oranet.aniversarioapi.domain.model.Aniversario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AniversarioModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public AniversarioModel toModel(Aniversario aniversario) {
        return modelMapper.map(aniversario, AniversarioModel.class);
    }

    public List<AniversarioModel> toCollectionModel(List<Aniversario> aniversarios) {
        return aniversarios.stream()
                .map(this::toModel)
                .toList();
    }
}
