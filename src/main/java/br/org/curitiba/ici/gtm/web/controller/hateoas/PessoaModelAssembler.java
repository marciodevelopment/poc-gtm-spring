package br.org.curitiba.ici.gtm.web.controller.hateoas;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import br.org.curitiba.ici.gtm.entity.PessoaEntity;
import br.org.curitiba.ici.gtm.web.controller.PessoaController;
import br.org.curitiba.ici.gtm.web.controller.mapstruct.PessoaMapper;
import br.org.curitiba.ici.gtm.web.controller.response.PessoaResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class PessoaModelAssembler implements RepresentationModelAssembler<PessoaEntity, PessoaResponse> {
	private final PessoaMapper pessoaMapper;
	
	@Override
	public PessoaResponse toModel(PessoaEntity entity) {
		PessoaResponse pessoaReponse = pessoaMapper.toResponse(entity);
		
		pessoaReponse.add(
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PessoaController.class)
				.getPessoa(entity.getCodPessoa())).withSelfRel());
		
		pessoaReponse.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PessoaController.class)
				.pesquisar(null, null)).withRel("pessoas"));
		
		return pessoaReponse;
	}
	
	
	public CollectionModel<PessoaResponse> toCollectionModel(Collection<PessoaEntity> entities) {
		CollectionModel<PessoaResponse> bancoResponses = CollectionModel.of( 
				entities
				.stream()
				.map(this::toModel)
				.collect(Collectors.toList()));
		
		bancoResponses.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PessoaController.class)
				.pesquisar(null, Optional.empty())).withSelfRel());
		return bancoResponses;
	}
	
	
}
