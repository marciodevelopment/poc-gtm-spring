package br.org.curitiba.ici.gtm.service;

import java.util.Collection;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import br.org.curitiba.ici.gtm.entity.AgenciaEntity;
import br.org.curitiba.ici.gtm.exception.ConstraintViolationException;
import br.org.curitiba.ici.gtm.exception.NotFoundException;
import br.org.curitiba.ici.gtm.repository.AgenciaRepository;
import br.org.curitiba.ici.gtm.type.OrderDirection;
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

	public Collection<AgenciaEntity> pesquisar(Optional<String> nomePessoa, OrderDirection direction, int page,
			int pageSize) {
		Pageable pageable = PageRequest.of(page, pageSize);
		return agenciaRepository.findByPessoa_nomePessoaStartingWithOrderByPessoa_nomePessoaAsc(nomePessoa.orElse("").toUpperCase(), pageable);
	}

	public Optional<AgenciaEntity> findByIdOptional(Integer codPessoa) {
		return agenciaRepository.findById(codPessoa);
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
