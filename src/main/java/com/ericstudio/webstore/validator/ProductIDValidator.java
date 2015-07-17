package com.ericstudio.webstore.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.ericstudio.webstore.domain.Product;
import com.ericstudio.webstore.exception.ProductNotFoundException;
import com.ericstudio.webstore.service.ProductService;

public class ProductIDValidator implements
		ConstraintValidator<ProductID, String> {
	@Autowired
	private ProductService productService;

	@Override
	public void initialize(ProductID arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		Product product;
		try {
			product = productService.getProductById(value);

		} catch (ProductNotFoundException e) {
			return true;
		}

		if (product != null) {
			return false;
		}

		return true;
	}

}
