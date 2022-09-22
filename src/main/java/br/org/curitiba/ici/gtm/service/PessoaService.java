package br.org.curitiba.ici.gtm.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.org.curitiba.ici.gtm.entity.PessoaEntity;
import br.org.curitiba.ici.gtm.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PessoaService {
	private final PessoaRepository pessoaRepository;

	public List<PessoaEntity> findByNomePessoaLike(Optional<String> nomePessoa, 
			Pageable pageable) {
		return pessoaRepository.findByNomePessoaStartingWith(nomePessoa.orElse("").toUpperCase(), pageable);
	}

	public PessoaEntity getReference(Integer codPessoa) {
		return pessoaRepository.getReferenceById(codPessoa);
	}

	public PessoaEntity findById(Integer codPessoa) {
		return pessoaRepository
				.findById(codPessoa)
				.orElseThrow(() -> new EntityNotFoundException("Não foi encontrado pessoa para o código de pessoa enviado."));

	}


}
