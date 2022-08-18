package br.org.curitiba.ici.gtm.repositorys;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import br.org.curitiba.ici.gtm.entity.PessoaEntity;

@SpringBootTest
class PessoaRepositoryTest {
	@Autowired
	private PessoaRepository pessoaRepository;

	@Test
	void findByNomePessoaLikeDeveRetornar2ResultadosParaNomeVazioPaginaZeroETamanhoPagina2() {
		Pageable pageable = PageRequest.of(0, 2, Sort.Direction.ASC, "nomePessoa");
		List<PessoaEntity> pessoas = pessoaRepository.findByNomePessoaStartingWith("", pageable);
		Assertions.assertEquals(2, pessoas.size());
	}

}
