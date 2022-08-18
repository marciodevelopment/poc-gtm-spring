package br.org.curitiba.ici.gtm.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.curitiba.ici.gtm.controller.request.PaginationRequest;
import br.org.curitiba.ici.gtm.controller.response.PessoaResponse;
import br.org.curitiba.ici.gtm.service.PessoaService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pessoas")
public class PessoaController {
	private final PessoaService pessoaService;

	@GetMapping
	public List<PessoaResponse> pesquisar(PaginationRequest paginationRequest, Optional<String> nome) {
		return pessoaService.findByNomePessoaLike(nome, 
					paginationRequest.getDirection(), 
					paginationRequest.getPage(), 
					paginationRequest.getPageSize())
				.stream().map(PessoaResponse::new)
				.collect(Collectors.toList());
	}
}