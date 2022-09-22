package br.org.curitiba.ici.gtm.web.controller.response;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Relation(itemRelation = "pessoa", collectionRelation = "pessoas")
public class PessoaResponse extends RepresentationModel<PessoaResponse> {
	private Integer codPessoa;
	private String nomePessoa;
	
}
