package com.oranet.aniversarioapi.api.controller;

import com.oranet.aniversarioapi.api.assembler.GrupoSocialModelAssembler;
import com.oranet.aniversarioapi.api.model.view.GrupoSocialModel;
import com.oranet.aniversarioapi.domain.model.GrupoSocial;
import com.oranet.aniversarioapi.domain.model.Pessoa;
import com.oranet.aniversarioapi.domain.service.CadastroGrupoSocialService;
import com.oranet.aniversarioapi.domain.service.CadastroPessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/pessoa/{pessoaId}/grupoSocial")
public class PessoaGrupoSocialController {

    @Autowired
    private CadastroPessoaService cadastroPessoaService;

    @Autowired
    private CadastroGrupoSocialService cadastroGrupoSocialService;

    @Autowired
    private GrupoSocialModelAssembler grupoSocialModelAssembler;


    @GetMapping
    public List<GrupoSocialModel> listar(@PathVariable Long pessoaId) {
        Pessoa pessoa = cadastroPessoaService.buscarOuFalhar(pessoaId);
        Set<GrupoSocial> gruposSociais = pessoa.getGruposSociais();
        return grupoSocialModelAssembler.toCollectionModel(gruposSociais);
    }

    @PostMapping("/{grupoSocialId}")
    public void associarGrupoSocial(@PathVariable Long pessoaId, @PathVariable Long grupoSocialId) {
        cadastroPessoaService.associarGrupoSocial(pessoaId, grupoSocialId);
    }

    @DeleteMapping("/{grupoSocialId}")
    public void desassociarGrupoSocial(@PathVariable Long pessoaId, @PathVariable Long grupoSocialId) {
        cadastroPessoaService.desassociarGrupoSocial(pessoaId, grupoSocialId);
    }
}
