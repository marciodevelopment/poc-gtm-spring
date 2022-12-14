package br.org.curitiba.ici.gtm.web.controller.response;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
@Relation(itemRelation = "banco", collectionRelation = "bancos")
public class BancoResponse extends RepresentationModel<AgenciaResponse> {
	private Integer codBanco;
	private String nomeBanco;
	
}
