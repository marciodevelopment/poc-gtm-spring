package br.org.curitiba.ici.gtm.web.controller;

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

import br.org.curitiba.ici.gtm.entity.AgenciaEntity;
import br.org.curitiba.ici.gtm.exception.NotFoundException;
import br.org.curitiba.ici.gtm.service.AgenciaService;
import br.org.curitiba.ici.gtm.service.BancoService;
import br.org.curitiba.ici.gtm.service.PessoaService;
import br.org.curitiba.ici.gtm.web.controller.request.AtualizacaoAgenciaRequest;
import br.org.curitiba.ici.gtm.web.controller.request.NovaAgenciaRequest;
import br.org.curitiba.ici.gtm.web.controller.request.PaginationRequest;
import br.org.curitiba.ici.gtm.web.controller.response.AgenciaResponse;
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
		AgenciaEntity novaAgencia = request.toModel(pessoaService, bancoService);
		AgenciaEntity agenciaCriada = agenciaService.persist(novaAgencia);
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
		AgenciaEntity agencia = 
				agenciaService.findByIdOptional(codPessoa)
				.orElseThrow(() -> new NotFoundException("codPessoa", "Não existe agência para o código de pessoa enviado."))
				.atualizar(bancoService.getReference(request.getCodBanco()), 
						request.getCodAgencia(), 
						request.getAgenciaCentralizadora(), 
						request.getSituacaoAgenciaRetorno());
		agenciaService.update(agencia);
	}

	@DeleteMapping(path = "/cod-pessoa/{cod-pessoa}")
	public void delete(@PathVariable("cod-pessoa") Integer codPessoa) {
		agenciaService.deleteById(codPessoa);
	}

}