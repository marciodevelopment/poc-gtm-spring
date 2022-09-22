package br.org.curitiba.ici.gtm.web.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.curitiba.ici.gtm.web.controller.response.RootEntryPointResponse;


@RestController
public class RootEntryPointController {
	
	@GetMapping
	public RootEntryPointResponse root() {
		RootEntryPointResponse response = new RootEntryPointResponse();
		response.add(linkTo(
				methodOn(AgenciaController.class).pesquisar(null, null)
				).withRel("agencias"));
		response.add(linkTo(
				methodOn(BancoController.class).list()
				).withRel("bancos"));
		response.add(linkTo(
				methodOn(PessoaController.class).pesquisar(null, null)
				).withRel("pessoas"));
		return response;
	}
}
