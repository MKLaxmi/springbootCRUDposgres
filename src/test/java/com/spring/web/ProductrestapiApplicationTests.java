package com.spring.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import com.spring.web.entity.Product;

@SpringBootTest
class ProductrestapiApplicationTests {

	@Value("${productrestapi.services.url}")
	private String baseUrl;

	@Test
	public void testGetProduct() {
		System.out.println(baseUrl);
		RestTemplate restTemplate = new RestTemplate();
		Product product = restTemplate.getForObject(baseUrl+"/2", Product.class);
		assertNotNull(product);
		assertEquals("oneplus", product.getName());
	}

	@Test
	public void testCreateProduct() {
		RestTemplate restTemplate = new RestTemplate();

		Product product = new Product();
		product.setName("micromaxMob");
		product.setDescription("high specs");
		product.setPrice(2000);
		Product newproduct = restTemplate.postForObject(baseUrl, product, Product.class);
		assertNotNull(newproduct);
		assertNotNull(newproduct.getId());
		assertEquals("micromaxMob", newproduct.getName());
	}

	@Test
	public void testUpdateProduct() {
		RestTemplate restTemplate = new RestTemplate();
		Product product = restTemplate.getForObject(baseUrl+"/2", Product.class);
		product.setPrice(15000);
		restTemplate.put("http://localhost:8080/products", product);
		assertEquals(15000, product.getPrice());
	}
}
