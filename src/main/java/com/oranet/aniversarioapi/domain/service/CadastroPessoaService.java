package com.oranet.aniversarioapi.domain.service;

import com.oranet.aniversarioapi.domain.exception.PessoaNaoEncontradaException;
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
public class CadastroPessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private AniversarioRepository aniversarioRepository;

    @Transactional
    public Pessoa adicionar(Pessoa pessoa) {
        Aniversario aniversario = pessoa.getAniversario();

        Period period = Period.between(
                aniversario.getDataAniversario().toLocalDate(),
                LocalDate.now()
        );

        int idade = period.getYears();
        pessoa.setIdade(idade);

        if (idade < 18) {
            pessoa.setIsMaiorDeIdade(Boolean.FALSE);
        } else {
            pessoa.setIsMaiorDeIdade(Boolean.TRUE);
        }

        LocalDate proximoAniversario;

        if (!aniversario.fezAniversarioEsseAno()) {
            proximoAniversario = LocalDate.now();
        } else {
            proximoAniversario = LocalDate.now().plusYears(1);
        }

        Period periodParaProximoAniversario = Period.between(
                LocalDate.now(),
                proximoAniversario
        );

        aniversario.setProximoAniversario(periodParaProximoAniversario);
        pessoa.setAniversario(aniversario);

        return pessoaRepository.save(pessoa);
    }

    @Transactional
    public void remover(Long pessoaId) {
        buscarOuFalhar(pessoaId);

        pessoaRepository.deleteById(pessoaId);
    }

    public Pessoa buscarOuFalhar(Long pessoaId) {
        return pessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new PessoaNaoEncontradaException(pessoaId));
    }
}
