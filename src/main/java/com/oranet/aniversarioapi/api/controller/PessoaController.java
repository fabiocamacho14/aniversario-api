package com.oranet.aniversarioapi.api.controller;

import com.oranet.aniversarioapi.api.assembler.PessoaModelAssembler;
import com.oranet.aniversarioapi.api.assembler.disassembler.PessoaInputDisassembler;
import com.oranet.aniversarioapi.api.model.input.PessoaInput;
import com.oranet.aniversarioapi.api.model.view.PessoaModel;
import com.oranet.aniversarioapi.domain.model.Pessoa;
import com.oranet.aniversarioapi.domain.repository.PessoaRepository;
import com.oranet.aniversarioapi.domain.service.CadastroPessoaService;
import jakarta.validation.Valid;
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

    @Autowired
    private PessoaInputDisassembler pessoaInputDisassembler;

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
    public PessoaModel adicionar(@RequestBody @Valid PessoaInput pessoaInput) {
        Pessoa pessoa = pessoaInputDisassembler.toDomainObject(pessoaInput);
        return pessoaModelAssembler.toModel(cadastroPessoa.adicionar(pessoa));
    }

    @DeleteMapping("/{pessoaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long pessoaId) {
        cadastroPessoa.remover(pessoaId);
    }
}
