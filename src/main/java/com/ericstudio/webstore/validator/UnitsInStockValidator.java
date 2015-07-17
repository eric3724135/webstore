package com.ericstudio.webstore.validator;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ericstudio.webstore.domain.Product;

@Component
public class UnitsInStockValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Product.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Product product = (Product) obj;
		if (product.getUnitPrice() == null
				|| product.getUnitPrice().equals(new BigDecimal(0))) {
			errors.rejectValue("unitsInStock",
					"com.ericstudio.webstore.validator.UnitsInStockValidator.zeroPrice.message");
		}
		if (product.getUnitPrice() != null
				&& new BigDecimal(10000).compareTo(product.getUnitPrice()) <= 0
				&& product.getUnitsInStock() > 99) {
			errors.rejectValue("unitsInStock",
					"com.ericstudio.webstore.validator.UnitsInStockValidator.message");
		}
	}

}
