package com.oranet.aniversarioapi.api.controller;

import com.oranet.aniversarioapi.domain.model.Aniversario;
import com.oranet.aniversarioapi.domain.repository.AniversarioRepository;
import com.oranet.aniversarioapi.domain.service.CadastroAniversarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/aniversario")
@RestController
public class AniversarioController {

    @Autowired
    private AniversarioRepository aniversarioRepository;

    @Autowired
    private CadastroAniversarioService cadastroAniversarioService;

    @RequestMapping
    public List<Aniversario> listarAniversarios() {
        return aniversarioRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Aniversario adicionarAniversario(@RequestBody Aniversario aniversario) {
        return cadastroAniversarioService.adicionar(aniversario);
    }
}
