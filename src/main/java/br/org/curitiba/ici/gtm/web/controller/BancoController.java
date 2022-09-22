package br.org.curitiba.ici.gtm.web.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.curitiba.ici.gtm.service.BancoService;
import br.org.curitiba.ici.gtm.web.controller.hateoas.BancoModelAssembler;
import br.org.curitiba.ici.gtm.web.controller.response.BancoResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/bancos")
public class BancoController {
	private final BancoService bancoService;
	private final BancoModelAssembler bancoModelAssembler;
	
	@GetMapping
	public CollectionModel<BancoResponse> list() {
		return bancoModelAssembler.toCollectionModel(bancoService.listAll());
	}

	@GetMapping(path = "/{cod-banco}")
	public BancoResponse getBanco(@PathVariable("cod-banco") Integer codBanco) {
		return bancoModelAssembler.toModel(bancoService.findById(codBanco));
	}
	
}
