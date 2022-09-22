package br.org.curitiba.ici.gtm.web.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.org.curitiba.ici.gtm.entity.AgenciaEntity;
import br.org.curitiba.ici.gtm.service.AgenciaService;
import br.org.curitiba.ici.gtm.web.controller.hateoas.AgenciaModelAssembler;
import br.org.curitiba.ici.gtm.web.controller.mapstruct.AgenciaMapper;
import br.org.curitiba.ici.gtm.web.controller.request.AtualizacaoAgenciaRequest;
import br.org.curitiba.ici.gtm.web.controller.request.NovaAgenciaRequest;
import br.org.curitiba.ici.gtm.web.controller.request.PaginationRequest;
import br.org.curitiba.ici.gtm.web.controller.response.AgenciaResponse;
import br.org.curitiba.ici.gtm.web.controller.response.PesquisaAgenciaResponse;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
@RequestMapping("/agencias")
public class AgenciaController {
	private final AgenciaService agenciaService;
	private final AgenciaMapper agenciaMapper;
	private final AgenciaModelAssembler agenciaModelAssembler;

	@PostMapping
	public ResponseEntity<Void> salvarNovaAgencia(@RequestBody @Valid NovaAgenciaRequest request) {
		AgenciaEntity novaAgencia = agenciaMapper.toEntity(request);
		AgenciaEntity agenciaCriada = agenciaService.persist(novaAgencia);
		return ResponseEntity.created(URI.create("/agencias/cod-pessoa/" + agenciaCriada.getPessoa().getCodPessoa())).build();
	}

	@GetMapping
	public CollectionModel<PesquisaAgenciaResponse> pesquisar(PaginationRequest paginationRequest, 
			@RequestParam(name = "nome-pessoa") Optional<String> nomePessoa) {
		return
				agenciaModelAssembler.toCollectionPesquisaAgenciaModel(agenciaService
						.pesquisar(nomePessoa, paginationRequest.toPageable()));

	}

	@GetMapping(path = "/cod-pessoa/{cod-pessoa}")
	public AgenciaResponse getAgencia(@PathVariable("cod-pessoa") Integer codPessoa) {
		return agenciaModelAssembler.toModel(agenciaService.findById(codPessoa));
	}


	@PutMapping(path = "/cod-pessoa/{cod-pessoa}")
	public ResponseEntity<Void> atualizar(@Valid @RequestBody AtualizacaoAgenciaRequest request, @PathVariable("cod-pessoa") Integer codPessoa) {
		AgenciaEntity agencia = agenciaMapper.toEntity(codPessoa, request);
		agenciaService.update(agencia);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping(path = "/cod-pessoa/{cod-pessoa}")
	public ResponseEntity<Void> delete(@PathVariable("cod-pessoa") Integer codPessoa) {
		agenciaService.deleteById(codPessoa);
		return ResponseEntity.ok().build();
	}

}