package br.org.curitiba.ici.gtm.web.controller.response;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Relation(itemRelation = "agencia", collectionRelation = "agencias")
public class PesquisaAgenciaResponse extends RepresentationModel<PesquisaAgenciaResponse>{
	private Integer codPessoa;
	private Integer codBanco;
	private Integer codAgencia;
	private String situacaoAgenciaRetorno;
	private Boolean agenciaCentralizadora;
	private String nomeBanco;
	private String nomePessoa;
	
}
