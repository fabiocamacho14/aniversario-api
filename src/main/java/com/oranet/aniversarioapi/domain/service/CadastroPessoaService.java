package com.oranet.aniversarioapi.domain.service;

import com.oranet.aniversarioapi.domain.exception.FalecidoNaoEncontradoException;
import com.oranet.aniversarioapi.domain.exception.NegocioException;
import com.oranet.aniversarioapi.domain.exception.PessoaNaoEncontradaException;
import com.oranet.aniversarioapi.domain.model.Aniversario;
import com.oranet.aniversarioapi.domain.model.Falecido;
import com.oranet.aniversarioapi.domain.model.GrupoSocial;
import com.oranet.aniversarioapi.domain.model.Pessoa;
import com.oranet.aniversarioapi.domain.repository.FalecidoRepository;
import com.oranet.aniversarioapi.domain.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.Period;
import java.util.HashSet;

@Service
public class CadastroPessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private FalecidoRepository falecidoRepository;

    @Autowired
    private CadastroGrupoSocialService cadastroGrupoSocialService;

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
            proximoAniversario = LocalDate.of(
                    (LocalDate.now().getYear()),
                    aniversario.getDataAniversario().getMonth(),
                    aniversario.getDataAniversario().getDayOfMonth()
            );
        } else {
            proximoAniversario = LocalDate.of(
                    (LocalDate.now().getYear() + 1),
                    aniversario.getDataAniversario().getMonth(),
                    aniversario.getDataAniversario().getDayOfMonth()
            );
        }

//        System.out.println(proximoAniversario.toString());

        Period periodParaProximoAniversario = Period.between(
                LocalDate.now(),
                proximoAniversario
        );

        pessoa.setGruposSociais(new HashSet<>());

        aniversario.setProximoAniversario(periodParaProximoAniversario);
        pessoa.setAniversario(aniversario);

        return pessoaRepository.save(pessoa);
    }

    @Transactional
    public void remover(Long pessoaId) {
        buscarOuFalhar(pessoaId);

        pessoaRepository.deleteById(pessoaId);
    }

    @Transactional
    public void associarGrupoSocial(Long pessoaId, Long grupoSocialId) {
        Pessoa pessoa = buscarOuFalhar(pessoaId);
        GrupoSocial grupoSocial = cadastroGrupoSocialService.buscarOuFalhar(grupoSocialId);


        if (!pessoa.getGruposSociais().add(grupoSocial)) {
            throw new NegocioException(String.format("Pessoa de código %d já pertence ao grupo social de código %d",
                    pessoaId, grupoSocialId));
        }
    }

    @Transactional
    public void desassociarGrupoSocial(Long pessoaId, Long grupoSocialId) {
        Pessoa pessoa = buscarOuFalhar(pessoaId);
        GrupoSocial grupoSocial = cadastroGrupoSocialService.buscarOuFalhar(grupoSocialId);

        if(!pessoa.getGruposSociais().remove(grupoSocial)) {
            throw new NegocioException(String.format("Pessoa de código %d não pertence ao grupo social de código %d",
                    pessoaId, grupoSocialId));
        }
    }

    @Transactional
    public void adicionarFalecido(Long pessoaId) {
        Pessoa pessoa = buscarOuFalhar(pessoaId);

        if (pessoa.getIsFalecido()) {
            throw new NegocioException(String.format("Pessoa de código %d já consta como falecida no sistema.",
                    pessoaId));
        }

        pessoa.setIsFalecido(Boolean.TRUE);

        Falecido falecido = new Falecido();
        falecido.setPessoa(pessoa);
        falecido.setIdadeQueFaleceu(pessoa.getIdade());
        falecido.setDataFalecimento(OffsetDateTime.now());
//        Provisório
        Period period = Period.between(LocalDate.now(), LocalDate.now());
        falecido.setPeriodoFalecimento(period);

        falecidoRepository.save(falecido);
    }

    @Transactional
    public void removerFalecido(Long pessoaId) {
        Pessoa pessoa = buscarOuFalhar(pessoaId);

        if (!pessoa.getIsFalecido()) {
            throw new FalecidoNaoEncontradoException(String.format("Pessoa de código %d não consta como falecida no sistema.",
                    pessoaId));
        }

        pessoa.setIsFalecido(Boolean.FALSE);
        falecidoRepository.deleteByPessoaId(pessoaId);
    }

    public Pessoa buscarOuFalhar(Long pessoaId) {
        return pessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new PessoaNaoEncontradaException(pessoaId));
    }
}
