package com.ericstudio.webstore.service;

public interface OrderService {
	void processOrder(String  productID, long quantity);
}
