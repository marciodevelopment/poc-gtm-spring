package br.org.curitiba.ici.gtm.controller.exception;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;


@Getter
public class ErrorConstraint {
	private String title;
	private Integer status;
	private List<Violation> violations = new ArrayList<>(1);
	
	public ErrorConstraint(String title, int status, String field, String message) {
		this.title = title;
		this.status = status;
		this.violations.add(new Violation(field, message));
	}
	
	public ErrorConstraint(String title, int status, List<Violation> violations) {
		this.title = title;
		this.status = status;
		this.violations.addAll(violations);
	}
			
}
