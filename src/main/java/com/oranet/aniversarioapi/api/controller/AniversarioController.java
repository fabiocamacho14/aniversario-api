package com.oranet.aniversarioapi.api.controller;

import com.oranet.aniversarioapi.domain.model.Aniversario;
import com.oranet.aniversarioapi.domain.repository.AniversarioRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/aniversario")
@RestController
public class AniversarioController {

    @Autowired
    private AniversarioRepository aniversarioRepository;

    @RequestMapping
    public List<Aniversario> listarAniversarios() {
        return aniversarioRepository.findAll();
    }
}
