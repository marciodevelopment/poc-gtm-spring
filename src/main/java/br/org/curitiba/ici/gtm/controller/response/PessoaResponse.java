package br.org.curitiba.ici.gtm.controller.response;

import br.org.curitiba.ici.gtm.entity.PessoaEntity;
import lombok.Getter;

@Getter
public class PessoaResponse {
	private Integer codPessoa;
	private String nomePessoa;
	
	public PessoaResponse(PessoaEntity pessoaEntity) {
		this.codPessoa = pessoaEntity.getCodPessoa();
		this.nomePessoa = pessoaEntity.getNomePessoa();
	}
	
}
