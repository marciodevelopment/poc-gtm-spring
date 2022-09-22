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
public class AgenciaResponse extends RepresentationModel<AgenciaResponse> {
	private PessoaResponse pessoa;
	private BancoResponse banco;
	
	private Integer codAgencia;
	private String situacaoAgenciaRetorno;
	private Boolean agenciaCentralizadora;
	
}
