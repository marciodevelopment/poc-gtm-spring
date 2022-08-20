package br.org.curitiba.ici.gtm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.org.curitiba.ici.gtm.entity.PessoaEntity;
import br.org.curitiba.ici.gtm.repository.PessoaRepository;
import br.org.curitiba.ici.gtm.type.OrderDirection;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PessoaService {
	private final PessoaRepository pessoaRepository;

	public List<PessoaEntity> findByNomePessoaLike(Optional<String> nomePessoa, 
			OrderDirection direction, 
			int page,
			int pageSize) {
		Pageable pageable = PageRequest.of(page, pageSize, direction.toSpringDataDirection(), "nomePessoa");
		return pessoaRepository.findByNomePessoaStartingWith(nomePessoa.orElse("").toUpperCase(), pageable );
	}

	public PessoaEntity getReference(Integer codPessoa) {
		return pessoaRepository.getReferenceById(codPessoa);
	}
	
	
}
