package br.org.curitiba.ici.gtm.web.controller.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import br.org.curitiba.ici.gtm.annotation.ValoresPermitidosString;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NovaAgenciaRequest {
	

	@NotNull(message = "Código pessoa não pode ser nulo")
	private Integer codPessoa;
	
	@NotNull(message = "Cod banco não pode ser nulo")
	private Integer codBanco;
	
	@NotNull(message = "Código agência não pode ser nulo")
	@Max(value = 9999, message = "Código agência deve ser um valor inferior ou igual a 9999")
	@Min(value = 0, message = "Código agência deve ser um valor superio ou igual a 0")
	private Integer codAgencia;
	
	@ValoresPermitidosString(message = "Valores permitidos para situação agência retorno são A ou B", valoresPermitidos = {"A", "B"})
	private  String situacaoAgenciaRetorno;
	
	@NotNull(message = "Agencia Centralizadora não pode ser nulo")
	private Boolean agenciaCentralizadora;

	
	
}
