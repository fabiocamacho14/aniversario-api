package com.oranet.aniversarioapi.api.assembler;

import com.oranet.aniversarioapi.api.model.view.FalecidoModel;
import com.oranet.aniversarioapi.domain.model.Falecido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FalecidoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public FalecidoModel toModel(Falecido falecido) {
        return modelMapper.map(falecido, FalecidoModel.class);
    }

    public List<FalecidoModel> toCollectionModel(List<Falecido> falecidos) {
        return falecidos.stream()
                .map(this::toModel)
                .toList();
    }
}
