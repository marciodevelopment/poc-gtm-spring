package br.org.curitiba.ici.gtm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import lombok.Getter;

@Getter
@Entity
@Table(name = "GTMPESPessoa")
@Where(clause =  " Ind_tipo_pessoa = '2' "
		+ "and exists ("
		+ "            select 1"
		+ "            from GTMPEJPessoa_Juridica pj "
		+ "            where pj.Cod_Pessoa = Cod_Pessoa  )")
public class PessoaEntity  {
	@Id
	@Column(name = "Cod_Pessoa")
	private Integer codPessoa;
	@Column(name = "Nme_Pessoa")
	private String nomePessoa;
	@Column(name = "Ind_tipo_pessoa")
	private String tipoPessoa;
}
