package com.oranet.aniversarioapi.domain.service;

import ch.qos.logback.core.joran.event.BodyEvent;
import com.oranet.aniversarioapi.domain.exception.AniversarioNaoEncontradoException;
import com.oranet.aniversarioapi.domain.model.Aniversario;
import com.oranet.aniversarioapi.domain.model.Pessoa;
import com.oranet.aniversarioapi.domain.repository.AniversarioRepository;
import com.oranet.aniversarioapi.domain.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;

@Service
public class CadastroAniversarioService {

    @Autowired
    private AniversarioRepository aniversarioRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Transactional
    public Aniversario adicionar(Aniversario aniversario) {
        Pessoa pessoa = aniversario.getPessoa();

        Period periodoDeVida = Period.between(
                aniversario.getDataAniversario().toLocalDate(),
                LocalDate.now()
        );

        int idade = periodoDeVida.getYears();
        pessoa.setIdade(idade);

        Period proximoAniversarioEm = Period.between(
                LocalDate.now(),
                aniversario.getDataAniversario().plusYears(1).toLocalDate()
        );

        pessoa.setProximoAniversario(proximoAniversarioEm);

        if (idade < 18) {
            pessoa.setIsMaiorDeIdade(Boolean.FALSE);
        } else {
            pessoa.setIsMaiorDeIdade(Boolean.TRUE);
        }



        return aniversarioRepository.save(aniversario);
    }

    @Transactional
    public void remover(Long aniversarioId) {
        buscarOuFalhar(aniversarioId);

        aniversarioRepository.deleteById(aniversarioId);
    }

    public Aniversario buscarOuFalhar(Long aniversarioId) {
        return aniversarioRepository.findById(aniversarioId)
                .orElseThrow(() -> new AniversarioNaoEncontradoException(aniversarioId));
    }
}
