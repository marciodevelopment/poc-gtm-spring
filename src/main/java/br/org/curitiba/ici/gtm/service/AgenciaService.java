package br.org.curitiba.ici.gtm.service;

import java.util.Collection;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import br.org.curitiba.ici.gtm.entity.AgenciaEntity;
import br.org.curitiba.ici.gtm.exception.ConstraintViolationException;
import br.org.curitiba.ici.gtm.exception.NotFoundException;
import br.org.curitiba.ici.gtm.repository.AgenciaRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class AgenciaService {
	private final AgenciaRepository agenciaRepository;
	private final BancoService bancoService;

	@Transactional
	public AgenciaEntity persist(AgenciaEntity agencia) {
		checkBancoParaPersistirAgencia(agencia);
		if (agenciaRepository.existsById(agencia.getPessoa().getCodPessoa())) {
			throw new ConstraintViolationException("Agência já existente para o código de pessoa.", "agencia.pessoa.codPessoa");
		}
		return agenciaRepository.save(agencia);
	}
	
	private void checkBancoParaPersistirAgencia(AgenciaEntity agencia) {
		if (bancoService.notExists(agencia.getBanco())) {
			throw new ConstraintViolationException("Banco inexistente.", "agencia.banco.codBanco");
		}
	}

	
	public Collection<AgenciaEntity> pesquisar(Optional<String> nomePessoa, Pageable pageable) {
		return agenciaRepository.findByPessoa_nomePessoaStartingWithOrderByPessoa_nomePessoaAsc(nomePessoa.orElse("").toUpperCase(), pageable);
	}
	
	
	public Optional<AgenciaEntity> findByIdOptional(Integer codPessoa) {
		return agenciaRepository.findById(codPessoa);
	}
	
	public AgenciaEntity findById(Integer codPessoa) {
		return 
				agenciaRepository.findById(codPessoa)
				.orElseThrow(() -> new NotFoundException("codPessoa", "Pessoa não encontrada para o código enviado"));
	}

	public AgenciaEntity update(AgenciaEntity request) {
		checkBancoParaPersistirAgencia(request);
		return agenciaRepository.save(request);
	}

	public void deleteById(Integer codPessoa) {
		if (!agenciaRepository.existsById(codPessoa)) {
			throw new NotFoundException("Agencia inexistente para o código de pessoa enviado.", "codPesssoa");
		}
		agenciaRepository.deleteById(codPessoa);
	}

	
}
