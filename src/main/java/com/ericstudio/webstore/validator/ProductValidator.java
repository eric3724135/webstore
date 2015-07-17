package com.ericstudio.webstore.validator;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ericstudio.webstore.domain.Product;

public class ProductValidator implements Validator {
	
	@Autowired
	private javax.validation.Validator beanValidator;

	private Set<Validator> springValidators;

	public void setSpringValidators(Set<Validator> springValidators) {
		this.springValidators = springValidators;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Product.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Set<ConstraintViolation<Object>> constraintViolations = beanValidator
				.validate(obj);

		for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
			String propertyPath = constraintViolation.getPropertyPath()
					.toString();
			String message = constraintViolation.getMessage();
			errors.rejectValue(propertyPath, "", message);
		}

		for (Validator validator : springValidators) {
			validator.validate(obj, errors);
		}
	}

}
