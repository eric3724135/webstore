package com.ericstudio.webstore.domain.repository.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ericstudio.webstore.domain.Product;
import com.ericstudio.webstore.domain.brick.v2.Sets;
import com.ericstudio.webstore.domain.repository.ProductRepository;
import com.ericstudio.webstore.exception.ProductNotFoundException;
import com.ericstudio.webstore.service.BrickService;
import com.ericstudio.webstore.utils.HibernateUtil;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

	Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private BrickService brickService;

	private List<Product> listOfProducts = new ArrayList<Product>();

	private SessionFactory sessionFactory;

	public ProductRepositoryImpl() {
		this.sessionFactory = HibernateUtil.getSessionFactory();

		/* dev objs */
		// Product iphone = new Product("P1234", "iPhone 5s", new
		// BigDecimal(500));
		// iphone.setDescription("Apple iPhone 5s smartphone with 4.00-inch 640x1136 display and 8-megapixel rear camera");
		// iphone.setCategory("Smart Phone");
		// iphone.setManufacturer("Apple");
		// iphone.setUnitsInStock(1000);
		//
		// Product laptop_dell = new Product("P1235", "Dell Inspiron",
		// new BigDecimal(700));
		// laptop_dell
		// .setDescription("Dell Inspiron 14-inch Laptop (Black) with 3rd Generation Intel Core processors");
		// laptop_dell.setCategory("Laptop");
		// laptop_dell.setManufacturer("Dell");
		// laptop_dell.setUnitsInStock(1000);
		//
		// Product tablet_Nexus = new Product("P1236", "Nexus 7", new
		// BigDecimal(
		// 300));
		// tablet_Nexus
		// .setDescription("Google Nexus 7 is the lightest 7 inch tablet With a quad-core Qualcomm Snapdragon� S4 Pro processor");
		// tablet_Nexus.setCategory("Tablet");
		// tablet_Nexus.setManufacturer("Google");
		// tablet_Nexus.setUnitsInStock(1000);
		//
		// listOfProducts.add(iphone);
		// listOfProducts.add(laptop_dell);
		// listOfProducts.add(tablet_Nexus);
	}

	@Override
	public List<Product> getAllProducts() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		List<Product> resultList = session.createQuery("from Product").list();

		session.getTransaction().commit();
		session.close();

		/* ws api sets */
		/* lego sets parser to product */
		// listOfProducts = new ArrayList<Product>();
		// List<Sets> sourceList = brickService.getSetsByYear("2015");
		// Product product;
		// for(Sets set : sourceList){
		// logger.info(set.getSetID()+":"+set.getUsRetailPrice());
		// product = new Product(String.valueOf(set.getSetID()),
		// set.getName(),StringUtils.equals(set.getUsRetailPrice(), "")?new
		// BigDecimal(999999):new BigDecimal(set.getUsRetailPrice()));
		// product.setDescription(set.getDescription());
		// product.setCategory(set.getTheme());
		// product.setManufacturer("LEGO");
		// product.setUnitsInStock(1000);
		// listOfProducts.add(product);
		// }

		return resultList;
		// return listOfProducts;
	}

	@Override
	public Product getProductById(String productID) {
		Product productById = null;

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query = session.createQuery("from Product where productId=?");
		query.setString(0, productID);
		List<Product> resultList = query.list();
		session.getTransaction().commit();
		session.close();
		for (Product product : resultList) {
			if (product != null && product.getProductId() != null
					&& product.getProductId().equals(productID)) {
				productById = product;
				break;
			}
		}
		
		// DEV without db 
		// for (Product product : listOfProducts) {
		// if (product != null && product.getProductId() != null
		// && product.getProductId().equals(productID)) {
		// productById = product;
		// break;
		// }
		// }

		if (productById == null) {
			throw new ProductNotFoundException(productID);
		}

		return productById;
	}

	@Override
	public List<Product> getProductsByCategory(String category) {
		List<Product> productsByCategory = new ArrayList<Product>();

		for (Product product : listOfProducts) {
			if (category.equalsIgnoreCase(product.getCategory())) {
				productsByCategory.add(product);
			}
		}

		return productsByCategory;
	}

	@Override
	public Set<Product> getProductsByFilter(
			Map<String, List<String>> filterParams) {
		Set<Product> productsByBrand = new HashSet<Product>();
		Set<Product> productsByCategory = new HashSet<Product>();

		Set<String> criterias = filterParams.keySet();

		if (criterias.contains("brand")) {
			for (String brandName : filterParams.get("brand")) {
				for (Product product : listOfProducts) {
					if (brandName.equalsIgnoreCase(product.getManufacturer())) {
						productsByBrand.add(product);
					}
				}
			}
		}

		if (criterias.contains("category")) {
			for (String category : filterParams.get("category")) {
				productsByCategory.addAll(this.getProductsByCategory(category));
			}
		}

		productsByCategory.retainAll(productsByBrand);

		return productsByCategory;
	}

	@Override
	public void addProduct(Product product) {
		listOfProducts.add(product);
	}

}
