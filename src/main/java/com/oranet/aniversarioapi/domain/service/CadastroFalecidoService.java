package com.oranet.aniversarioapi.domain.service;

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

    @Autowired
    private CadastroAniversarioService cadastroAniversarioService;

    public Falecido declararFalecido(Long pessoaId) {
        Falecido falecido = new Falecido();
        Pessoa pessoa = cadastroAniversarioService.buscarOuFalhar(pessoaId).getPessoa();

        falecido.setPessoa(pessoa);

        Period periodo = Period.between(
                pessoa.getAniversario().getDataAniversario().toLocalDate(),
                falecido.getDataFalecimento().toLocalDate()
        );

        falecido.setIdadeQueFaleceu(periodo.getYears());
        pessoa.setIsFalecido(Boolean.TRUE);

        return falecidoRepository.save(falecido);
    }

    @Transactional
    public void removerFalecido(Long pessoaId) {
        Pessoa pessoa = cadastroAniversarioService.buscarOuFalhar(pessoaId).getPessoa();

        pessoa.setIsFalecido(Boolean.FALSE);
        falecidoRepository.deleteById(pessoaId);
    }
}
