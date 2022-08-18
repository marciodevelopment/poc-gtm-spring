package br.org.curitiba.ici.gtm.controller.request;





import com.fasterxml.jackson.annotation.JsonProperty;

import br.org.curitiba.ici.gtm.type.OrderDirection;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class PaginationRequest {
	private int page = 0;
	@JsonProperty("page-size")
	private int pageSize = 10;
	private String direction = "asc";
	private String sortField;
	
	public OrderDirection getDirection() {
		return OrderDirection.fromString(direction);
	}
}
