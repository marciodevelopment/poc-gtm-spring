package br.org.curitiba.ici.gtm.web.controller.request;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

class PaginationRequestTest {

	@Test
	void toPageableTestDeveRertornarSortSeDirectionESortFielNotNull() {
		PaginationRequest pagination = new PaginationRequest();
		pagination.setDirection("asc");
		pagination.setSortField("nome");
		Pageable pageable = pagination.toPageable();
		assertNotNull(pageable.getSort());
	}
	
	@Test
	void toPageableTestDeveRertornarUnsortedEmSortSeDirectionNull() {
		PaginationRequest pagination = new PaginationRequest();
		pagination.setDirection("asc");
		Pageable pageable = pagination.toPageable();
		assertEquals(Sort.unsorted(), pageable.getSort());
	}
	
	@Test
	void toPageableTestDeveRertornarUnsortedEmSortSeSortFieldNull() {
		PaginationRequest pagination = new PaginationRequest();
		pagination.setDirection("asc");
		Pageable pageable = pagination.toPageable();
		assertEquals(Sort.unsorted(), pageable.getSort());
	}

}
