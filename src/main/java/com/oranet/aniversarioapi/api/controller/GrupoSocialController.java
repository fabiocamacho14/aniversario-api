package com.oranet.aniversarioapi.api.controller;

import com.oranet.aniversarioapi.api.assembler.GrupoSocialModelAssembler;
import com.oranet.aniversarioapi.api.assembler.disassembler.GrupoSocialInputDisassembler;
import com.oranet.aniversarioapi.api.model.input.GrupoSocialInput;
import com.oranet.aniversarioapi.api.model.view.GrupoSocialModel;
import com.oranet.aniversarioapi.domain.model.GrupoSocial;
import com.oranet.aniversarioapi.domain.repository.GrupoSocialRepository;
import com.oranet.aniversarioapi.domain.service.CadastroGrupoSocialService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupo")
public class GrupoSocialController {

    @Autowired
    private GrupoSocialRepository grupoSocialRepository;

    @Autowired
    private CadastroGrupoSocialService cadastroGrupoSocialService;

    @Autowired
    private GrupoSocialModelAssembler grupoSocialModelAssembler;

    @Autowired
    private GrupoSocialInputDisassembler grupoSocialInputDisassembler;

    @GetMapping("/{grupoSocialId}")
    public GrupoSocialModel buscar(@PathVariable Long grupoSocialId) {
        GrupoSocial grupoSocial = cadastroGrupoSocialService.buscarOuFalhar(grupoSocialId);
        return grupoSocialModelAssembler.toModel(grupoSocial);
    }

    @GetMapping
    public List<GrupoSocialModel> listar() {
        return grupoSocialModelAssembler.toCollectionModel(grupoSocialRepository.findAll());
    }

    @PostMapping
    public GrupoSocialModel adicionar(@RequestBody @Valid GrupoSocialInput grupoSocialInput) {
        GrupoSocial grupoSocial = grupoSocialInputDisassembler.toDomainObject(grupoSocialInput);
        return grupoSocialModelAssembler.toModel(cadastroGrupoSocialService.adicionar(grupoSocial));
    }

    @DeleteMapping("/{grupoSocialId}")
    public void remover(@PathVariable Long grupoSocialId) {
        cadastroGrupoSocialService.remover(grupoSocialId);
    }
}
