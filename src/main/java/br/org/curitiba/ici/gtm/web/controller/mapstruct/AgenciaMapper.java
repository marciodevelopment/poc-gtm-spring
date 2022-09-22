package br.org.curitiba.ici.gtm.web.controller.mapstruct;

import java.util.Collection;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import br.org.curitiba.ici.gtm.entity.AgenciaEntity;
import br.org.curitiba.ici.gtm.entity.BancoEntity;
import br.org.curitiba.ici.gtm.entity.PessoaEntity;
import br.org.curitiba.ici.gtm.exception.NotFoundException;
import br.org.curitiba.ici.gtm.service.AgenciaService;
import br.org.curitiba.ici.gtm.service.BancoService;
import br.org.curitiba.ici.gtm.service.PessoaService;
import br.org.curitiba.ici.gtm.web.controller.request.AtualizacaoAgenciaRequest;
import br.org.curitiba.ici.gtm.web.controller.request.NovaAgenciaRequest;
import br.org.curitiba.ici.gtm.web.controller.response.AgenciaResponse;
import br.org.curitiba.ici.gtm.web.controller.response.PesquisaAgenciaResponse;

@Mapper(componentModel = "spring", 
implementationPackage = "br.org.curitiba.ici.gtm.web.controller.mapstruct")
public abstract class AgenciaMapper {
	@Autowired
	private BancoService bancoService;
	@Autowired
	private PessoaService pessoaService;
	@Autowired
	private AgenciaService agenciaService;
	
	public abstract List<PesquisaAgenciaResponse> toPesquisaResponse(Collection<AgenciaEntity> agencias);

	@Mapping(target = "nomePessoa", source = "pessoa.nomePessoa")
	@Mapping(target = "codBanco", source = "banco.codBanco")
	@Mapping(target = "nomeBanco", source = "banco.nomeBanco")
	abstract PesquisaAgenciaResponse toPesquisaResponse(AgenciaEntity agencia);
	
	public abstract AgenciaResponse toAgenciaResponse(AgenciaEntity agencia);


	public AgenciaEntity toEntity(NovaAgenciaRequest request) {
		BancoEntity banco = bancoService.getReference(request.getCodBanco());
		PessoaEntity pessoa = pessoaService.getReference(request.getCodPessoa());
		AgenciaEntity agencia = new AgenciaEntity(pessoa, banco, 
				request.getCodAgencia(), 
				request.getAgenciaCentralizadora());
		agencia.setSituacaoAgenciaRetorno(request.getSituacaoAgenciaRetorno());
		return agencia;

	}
	
	public AgenciaEntity toEntity(Integer codPessoa, AtualizacaoAgenciaRequest request) {
		return
				agenciaService.findByIdOptional(codPessoa)
				.orElseThrow(() -> new NotFoundException("codPessoa", "Não existe agência para o código de pessoa enviado."))
				.atualizar(bancoService.getReference(request.getCodBanco()), 
						request.getCodAgencia(), 
						request.getAgenciaCentralizadora(), 
						request.getSituacaoAgenciaRetorno());
	}
	
	
}
