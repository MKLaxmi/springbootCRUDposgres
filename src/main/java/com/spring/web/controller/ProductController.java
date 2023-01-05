package com.spring.web.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.web.entity.Product;
import com.spring.web.repository.ProductRepository;

@RestController
public class ProductController {

	private static final Logger log = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductRepository repo;
	
	@GetMapping("/products")
	public List<Product> getProducts(){
		
		return (List<Product>)repo.findAll();
	}
	
	@GetMapping("/products/{id}")
	public Optional<Product> getProduct(@PathVariable("id") Long id){
		log.info("find product by id "+id);
		return repo.findById(id);
	}
	
	@PostMapping("/products")
	public Product createProduct(@RequestBody Product product){
		return repo.save(product);
	}
	
	@PutMapping("/products")
	public Product updateProduct(@RequestBody Product product){
		
		return repo.save(product);
	}
	
	@DeleteMapping("/products/{id}")
	public void deleteProduct(@PathVariable("id") Long id){
		 repo.deleteById(id);
	}
}
