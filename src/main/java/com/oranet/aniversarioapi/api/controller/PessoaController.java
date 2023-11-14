package com.oranet.aniversarioapi.api.controller;

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

    @GetMapping("/{pessoaId}")
    public Pessoa buscar(@PathVariable Long pessoaId) {
        return cadastroPessoa.buscarOuFalhar(pessoaId);
    }

    @GetMapping()
    public List<Pessoa> listar() {
        return pessoaRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pessoa adicionar(@RequestBody Pessoa pessoa) {
        return cadastroPessoa.adicionar(pessoa);
    }

    @DeleteMapping("/{pessoaId}")
    public void remover(@PathVariable Long pessoaId) {
        cadastroPessoa.remover(pessoaId);
    }
}
