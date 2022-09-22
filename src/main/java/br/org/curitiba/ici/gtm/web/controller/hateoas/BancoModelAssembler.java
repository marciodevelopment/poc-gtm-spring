package br.org.curitiba.ici.gtm.web.controller.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import br.org.curitiba.ici.gtm.entity.BancoEntity;
import br.org.curitiba.ici.gtm.web.controller.BancoController;
import br.org.curitiba.ici.gtm.web.controller.mapstruct.BancoMapper;
import br.org.curitiba.ici.gtm.web.controller.response.BancoResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class BancoModelAssembler implements RepresentationModelAssembler<BancoEntity, BancoResponse> {
	private final BancoMapper bancoMapper;
	
	@Override
	public BancoResponse toModel(BancoEntity entity) {
		BancoResponse bancoResponse = bancoMapper.toResponse(entity);
		
		 bancoResponse.add(
				linkTo(WebMvcLinkBuilder.methodOn(BancoController.class)
				.getBanco(entity.getCodBanco())).withSelfRel());
		
		 bancoResponse.add(linkTo(WebMvcLinkBuilder.methodOn(BancoController.class)
				.list()).withRel("bancos"));
		return bancoResponse;
	}
	
	public CollectionModel<BancoResponse> toCollectionModel(Collection<BancoEntity> entities) {
		CollectionModel<BancoResponse> bancoResponses = CollectionModel.of( 
				entities
				.stream()
				.map(this::toModel)
				.collect(Collectors.toList()));
		bancoResponses.add(linkTo(methodOn(BancoController.class)
				.list()).withSelfRel());
		return bancoResponses;
	}
}
