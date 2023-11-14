package com.oranet.aniversarioapi.domain.service;

import com.oranet.aniversarioapi.domain.model.Aniversario;
import com.oranet.aniversarioapi.domain.model.Falecido;
import com.oranet.aniversarioapi.domain.model.Pessoa;
import com.oranet.aniversarioapi.domain.repository.FalecidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Period;

@Service
public class CadastroFalecidoService {

    @Autowired
    private FalecidoRepository falecidoRepository;

//    @Autowired
//    private CadastroAniversarioService cadastroAniversarioService;

//    public Falecido declararFalecido(Long pessoaId) {
//        Falecido falecido = new Falecido();
//        Pessoa pessoa = cadastroAniversarioService.buscarOuFalhar(pessoaId).getPessoa();
//        Aniversario aniversario = cadastroAniversarioService.buscarOuFalhar(pessoaId);

//        falecido.setPessoa(aniversario.getPessoa());

//        Period periodo = Period.between(
//                aniversario.getDataAniversario().toLocalDate(),
//                falecido.getDataFalecimento().toLocalDate()
//        );
//
//        falecido.setIdadeQueFaleceu(periodo.getYears());
//        aniversario.getPessoa().setIsFalecido(Boolean.TRUE);

//        return falecidoRepository.save(falecido);
//    }

//    @Transactional
//    public void removerFalecido(Long pessoaId) {
//        Pessoa pessoa = cadastroAniversarioService.buscarOuFalhar(pessoaId).getPessoa();
//
//        pessoa.setIsFalecido(Boolean.FALSE);
//        falecidoRepository.deleteById(pessoaId);
//    }
}
