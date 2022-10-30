package com.server;

import com.server.entity.Product;
import com.server.mapper.ProductMapper;
import com.server.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

@SpringBootTest
class ProductTest {

	@Autowired
	ProductMapper productMapper;
	@Autowired
	ProductService productService;

	@Test
	void getAllProductsTest(){
		List<Product> products = productMapper.findAll();
		for (Product p: products) {
			System.out.println(p);
		}
	}

	@Test
	void findProductsByUserTest(){
		List<Product> products = productMapper.findByUserId(1);
		for (Product p: products) {
			System.out.println(p);
		}
	}

	@Test
	void contextLoads() {
		Specification<Product> pho = productService.prepare("pho", "", 200.0);
		List<Product> a = productService.searchAll_paging(pho,0,2);
		System.out.println(a.size());
		System.out.println(productService.count(pho));
	}

	@Test
	void productLength() {
		System.out.println(productMapper.count());
	}
	@Test
	void productMaxvalue() {
		System.out.println(productMapper.getMaxvalue());
	}
}
