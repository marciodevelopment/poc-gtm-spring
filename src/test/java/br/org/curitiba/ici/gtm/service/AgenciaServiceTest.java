package br.org.curitiba.ici.gtm.service;


import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.org.curitiba.ici.gtm.entity.AgenciaEntity;
import br.org.curitiba.ici.gtm.entity.BancoEntity;
import br.org.curitiba.ici.gtm.entity.PessoaEntity;
import br.org.curitiba.ici.gtm.exception.ConstraintViolationException;
import br.org.curitiba.ici.gtm.exception.NotFoundException;
import br.org.curitiba.ici.gtm.repository.AgenciaRepository;

@ExtendWith(MockitoExtension.class)
class AgenciaServiceTest {
	@Mock
	BancoService bancoService;
	@Mock
	AgenciaRepository agenciaRepository;
	
	@InjectMocks
	AgenciaService agenciaService;
	
	AgenciaEntity agencia = new AgenciaEntity(new PessoaEntity(), 
			new BancoEntity(), 
			999, 
			true);
	
	@Test
	void persistNaoDeveSalvarSeBancoNaoExistirTest() {
		when(bancoService.notExists(agencia.getBanco())).thenReturn(true);
		assertThatThrownBy(() -> agenciaService.persist(agencia)).isInstanceOf(ConstraintViolationException.class);
	}
	
	@Test
	void persistNaoDeveSalvarSePessoaJaExistirTest() {
		when(agenciaRepository.existsById(agencia.getCodPessoa())).thenReturn(true);
		assertThatThrownBy(() -> agenciaService.persist(agencia)).isInstanceOf(ConstraintViolationException.class);
	}
	
	@Test
	void persistDeveSalvarSeNaoExistirPessoaEExistirAgenciaTest() {
		agenciaService.persist(agencia);
	}
	
	@Test
	void deleteByIdNaoDeveDeletarSeNaoExistirAgencia() {
		Integer codPessoa = -1;
		when(agenciaRepository.existsById(codPessoa)).thenReturn(false);
		assertThatThrownBy(() -> agenciaService.deleteById(codPessoa)).isInstanceOf(NotFoundException.class);
	}
	
	@Test
	void deleteByIdDeveDeletarSeExistirAgencia() {
		Integer codPessoa = 1;
		when(agenciaRepository.existsById(codPessoa)).thenReturn(true);
		agenciaService.deleteById(codPessoa);
	}
	

}
