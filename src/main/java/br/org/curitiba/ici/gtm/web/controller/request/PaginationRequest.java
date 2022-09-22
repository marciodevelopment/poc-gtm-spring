package br.org.curitiba.ici.gtm.web.controller.request;





import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;

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
	
	
	public Pageable toPageable() {
		if (StringUtils.hasText(direction) && StringUtils.hasText(sortField)) {
			Sort sort = Sort.by(direction, sortField);
			return PageRequest.of(page, pageSize, sort);
		}
		return PageRequest.of(page, pageSize);
	}
}
