package com.oranet.aniversarioapi.api.controller;

import com.oranet.aniversarioapi.api.assembler.PessoaModelAssembler;
import com.oranet.aniversarioapi.api.model.PessoaModel;
import com.oranet.aniversarioapi.domain.model.Pessoa;
import com.oranet.aniversarioapi.domain.repository.PessoaRepository;
import com.oranet.aniversarioapi.domain.service.CadastroPessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private CadastroPessoaService cadastroPessoa;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaModelAssembler pessoaModelAssembler;

    @GetMapping("/{pessoaId}")
    public PessoaModel buscar(@PathVariable Long pessoaId) {
        return pessoaModelAssembler.toModel(cadastroPessoa.buscarOuFalhar(pessoaId));
    }

    @GetMapping()
    public List<PessoaModel> listar() {
        return pessoaModelAssembler.toCollectionModel(pessoaRepository.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PessoaModel adicionar(@RequestBody Pessoa pessoa) {
        return pessoaModelAssembler.toModel(cadastroPessoa.adicionar(pessoa));
    }

    @DeleteMapping("/{pessoaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long pessoaId) {
        cadastroPessoa.remover(pessoaId);
    }
}
