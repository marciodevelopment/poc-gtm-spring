package br.org.curitiba.ici.gtm.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.curitiba.ici.gtm.entity.PessoaEntity;
import br.org.curitiba.ici.gtm.service.PessoaService;
import br.org.curitiba.ici.gtm.web.controller.hateoas.PessoaModelAssembler;
import br.org.curitiba.ici.gtm.web.controller.request.PaginationRequest;
import br.org.curitiba.ici.gtm.web.controller.response.PessoaResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pessoas")
public class PessoaController {
	private final PessoaService pessoaService;
	private final PessoaModelAssembler pessoaModelAssembler;
	
	@GetMapping
	public CollectionModel<PessoaResponse> pesquisar(PaginationRequest paginationRequest, Optional<String> nome) {
		List<PessoaEntity> pessoas = pessoaService.findByNomePessoaLike(nome, 
				paginationRequest.toPageable());
		return pessoaModelAssembler.toCollectionModel(pessoas);
	}
	
	
	@GetMapping(path = "/{cod-pessoa}")
	public PessoaResponse getPessoa(@PathVariable("cod-pessoa") Integer codPessoa) {
		return pessoaModelAssembler.toModel(pessoaService.findById(codPessoa));
	}
	
	
}