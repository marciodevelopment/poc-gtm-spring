package br.org.curitiba.ici.gtm.annotation.validator;



import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.org.curitiba.ici.gtm.annotation.NotExistsId;

public class NotExistsIdValidator implements ConstraintValidator<NotExistsId, Object> {
	private String domainAttribute;
	private Class<?> klass;
	//private JpaOperations jpaOperations = new JpaOperations();
	
	@Override
	public void initialize(NotExistsId params) {
		domainAttribute = params.fieldName();
		klass = params.domainClass();
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		return true;
		/*
		if (value == null) {
			return true;
		}
		Query query = jpaOperations.getEntityManager().createQuery("select 1 from " + klass.getName() + " where " + domainAttribute + "=:value");
		query.setMaxResults(1);
		query.setParameter("value", value);
		return query.getResultList().isEmpty();
		*/
	}
	
}
