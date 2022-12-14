package br.org.curitiba.ici.gtm.annotation;


import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.org.curitiba.ici.gtm.annotation.validator.ValoresPermitidosStringValidator;


@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = ValoresPermitidosStringValidator.class)
public @interface ValoresPermitidosString {

	String message() default "Valores permitidos";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
	
	String[] valoresPermitidos();
	
	
}
