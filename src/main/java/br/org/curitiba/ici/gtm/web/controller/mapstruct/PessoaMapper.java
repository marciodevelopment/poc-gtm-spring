package br.org.curitiba.ici.gtm.web.controller.mapstruct;

import java.util.List;

import org.mapstruct.Mapper;

import br.org.curitiba.ici.gtm.entity.PessoaEntity;
import br.org.curitiba.ici.gtm.web.controller.response.PessoaResponse;

@Mapper(componentModel = "spring", 
	implementationPackage = "br.org.curitiba.ici.gtm.web.controller.mapstruct")
public interface PessoaMapper {
	PessoaResponse toResponse(PessoaEntity pessoa);
	List<PessoaResponse> toResponses(List<PessoaEntity> pessoas);
}
