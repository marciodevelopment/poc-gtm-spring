package br.org.curitiba.ici.gtm.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

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

import br.org.curitiba.ici.gtm.controller.request.AtualizacaoAgenciaRequest;
import br.org.curitiba.ici.gtm.controller.request.NovaAgenciaRequest;
import br.org.curitiba.ici.gtm.controller.request.PaginationRequest;
import br.org.curitiba.ici.gtm.controller.response.AgenciaResponse;
import br.org.curitiba.ici.gtm.entity.AgenciaEntity;
import br.org.curitiba.ici.gtm.service.AgenciaService;
import br.org.curitiba.ici.gtm.service.BancoService;
import br.org.curitiba.ici.gtm.service.PessoaService;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
@RequestMapping("/agencias")
public class AgenciaController {
	private final AgenciaService agenciaService;
	private final BancoService bancoService;
	private final PessoaService pessoaService;


	@PostMapping
	public ResponseEntity<Void> salvar(@RequestBody @Valid NovaAgenciaRequest request) {
		AgenciaEntity agencia = request.toModel(pessoaService, bancoService);
		AgenciaEntity agenciaCriada = agenciaService.persist(agencia);
		return ResponseEntity.created(URI.create("/agencias/cod-pessoa/" + agenciaCriada.getPessoa().getCodPessoa())).build();
	}

	@GetMapping
	public List<AgenciaResponse> pesquisar(PaginationRequest paginationRequest, 
			@RequestParam(name = "nome-pessoa") Optional<String> nomePessoa) {
		return agenciaService
				.pesquisar(nomePessoa, paginationRequest.getDirection(), paginationRequest.getPage(), paginationRequest.getPageSize())
				.stream()
				.map(AgenciaResponse::new)
				.collect(Collectors.toList());

	}

	@PutMapping(path = "/cod-pessoa/{cod-pessoa}")
	public void atualizar(@Valid @RequestBody AtualizacaoAgenciaRequest request, @PathVariable("cod-pessoa") Integer codPessoa) {
		agenciaService.update(codPessoa, request);
		
		/*
		Optional<AgenciaEntity> possivelAgencia = agenciaService.findByIdOptional(codPessoa);
		if (possivelAgencia.isEmpty()) {
			throw new NotFoundException("codPessoa", "Não existe agência para o código de pessoa enviado.");
		}
		BancoEntity banco = bancoService.getReference(request.getCodBanco());
		possivelAgencia.get().atualizar(banco, 
				request.getCodAgencia(), 
				request.getAgenciaCentralizadora(), 
				request.getSituacaoAgenciaRetorno());
		agenciaService.update(possivelAgencia.get());
		*/
	}



	@DeleteMapping(path = "/cod-pessoa/{cod-pessoa}")
	public void delete(@PathVariable("cod-pessoa") Integer codPessoa) {
		agenciaService.deleteById(codPessoa);
	}

}