package com.oranet.aniversarioapi.api.controller;

import com.oranet.aniversarioapi.api.assembler.FalecidoModelAssembler;
import com.oranet.aniversarioapi.api.model.view.FalecidoModel;
import com.oranet.aniversarioapi.domain.repository.FalecidoRepository;
import com.oranet.aniversarioapi.domain.service.CadastroPessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/pessoa/{pessoaId}/falecimento")
@RestController
public class FalecidoController {

    @Autowired
    private CadastroPessoaService cadastroPessoaService;

    @Autowired
    private FalecidoRepository falecidoRepository;

    @Autowired
    private FalecidoModelAssembler falecidoModelAssembler;

    @GetMapping
    public List<FalecidoModel> listar() {
        return falecidoModelAssembler.toCollectionModel(falecidoRepository.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void adicionarFalecido(@PathVariable Long pessoaId) {
        cadastroPessoaService.adicionarFalecido(pessoaId);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerFalecido(@PathVariable Long pessoaId) {
        cadastroPessoaService.removerFalecido(pessoaId);
    }

}
