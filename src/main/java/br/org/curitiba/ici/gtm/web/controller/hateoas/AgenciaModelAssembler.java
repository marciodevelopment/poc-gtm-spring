package br.org.curitiba.ici.gtm.web.controller.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Collection;
import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import br.org.curitiba.ici.gtm.entity.AgenciaEntity;
import br.org.curitiba.ici.gtm.web.controller.AgenciaController;
import br.org.curitiba.ici.gtm.web.controller.mapstruct.AgenciaMapper;
import br.org.curitiba.ici.gtm.web.controller.response.AgenciaResponse;
import br.org.curitiba.ici.gtm.web.controller.response.PesquisaAgenciaResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class AgenciaModelAssembler implements RepresentationModelAssembler<AgenciaEntity, AgenciaResponse> {
	private final AgenciaMapper agenciaMapper;
	
	@Override
	public AgenciaResponse toModel(AgenciaEntity entity) {
		AgenciaResponse agenciaResponse = agenciaMapper.toAgenciaResponse(entity);
		
		agenciaResponse.add(
				linkTo(methodOn(AgenciaController.class)
						.delete(entity.getCodPessoa())).withRel("deletar"));
		
		agenciaResponse.add(
				linkTo(methodOn(AgenciaController.class)
						.salvarNovaAgencia(null)).withRel("criar"));
		
		agenciaResponse.add(
				linkTo(methodOn(AgenciaController.class)
						.atualizar(null, entity.getCodPessoa())).withRel("atualizar"));
		
		agenciaResponse.add(
				getLinkToAgencia(entity.getCodPessoa()));
		
		agenciaResponse.add(getLinkToCollectionPesquisa().withRel("agencias"));
		return agenciaResponse;
	}
	
	public CollectionModel<PesquisaAgenciaResponse> toCollectionPesquisaAgenciaModel(
			Collection<AgenciaEntity> entities) {
		List<PesquisaAgenciaResponse> agenciasResponse = agenciaMapper.toPesquisaResponse(entities);
		agenciasResponse.forEach(resp -> resp.add(getLinkToAgencia(resp.getCodPessoa())));
		return CollectionModel.of(agenciasResponse)
				.add(getLinkToCollectionPesquisa());
		
	}
	
	public Link getLinkToCollectionPesquisa() {
		return linkTo(WebMvcLinkBuilder.methodOn(AgenciaController.class)
				.pesquisar(null, null)).withSelfRel();
	}
	
	private Link getLinkToAgencia(Integer codPessoa) {
		return
			linkTo(WebMvcLinkBuilder.methodOn(AgenciaController.class)
					.getAgencia(codPessoa)).withSelfRel();
	}
}
