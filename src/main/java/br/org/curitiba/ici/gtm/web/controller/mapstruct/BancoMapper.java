package br.org.curitiba.ici.gtm.web.controller.mapstruct;

import java.util.Collection;
import java.util.List;

import org.mapstruct.Mapper;

import br.org.curitiba.ici.gtm.entity.BancoEntity;
import br.org.curitiba.ici.gtm.web.controller.response.BancoResponse;

@Mapper(componentModel = "spring", 
	implementationPackage = "br.org.curitiba.ici.gtm.web.controller.mapstruct")
public interface BancoMapper {
	List<BancoResponse> toResponses(Collection<BancoEntity> bancos);
	BancoResponse toResponse(BancoEntity banco);
}
