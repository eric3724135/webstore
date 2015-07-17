package com.ericstudio.webstore.domain.brick.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

//@Configuration
public class BrickConfig {
//	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setContextPath("com.ericstudio.webstore.domain.brick");
		return marshaller;
	}

//	@Bean
//	public BrickClient brickClient(Jaxb2Marshaller marshaller) {
//		BrickClient client = new BrickClient();
//		client.setDefaultUri("http://brickset.com/webservices/brickset.asmx/search");
//		client.setMarshaller(marshaller);
//		client.setUnmarshaller(marshaller);
//		return client;
//	}
}
