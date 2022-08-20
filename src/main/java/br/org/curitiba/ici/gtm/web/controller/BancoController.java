package br.org.curitiba.ici.gtm.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.curitiba.ici.gtm.service.BancoService;
import br.org.curitiba.ici.gtm.web.controller.response.BancoResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/bancos")
public class BancoController {
	private final BancoService bancoService;
	
	@GetMapping
	public List<BancoResponse> list() {
		return bancoService.listAll()
				.stream()
				.map(BancoResponse::new)
				.collect(Collectors.toList());
	}

}
