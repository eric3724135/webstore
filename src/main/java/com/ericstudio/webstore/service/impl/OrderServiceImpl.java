package com.ericstudio.webstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ericstudio.webstore.domain.Product;
import com.ericstudio.webstore.domain.repository.ProductRepository;
import com.ericstudio.webstore.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private ProductRepository productRepository;

	public void processOrder(String productID, long quantity) {
		Product productById = productRepository.getProductById(productID);

		if (productById.getUnitsInStock() < quantity) {
			throw new IllegalArgumentException(
					"Out of Stock. Available Units in stock"
							+ productById.getUnitsInStock());
		}

		productById.setUnitsInStock(productById.getUnitsInStock() - quantity);
	}

}
