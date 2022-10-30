package com.server.service;

import com.server.entity.Product;
import com.server.mapper.ProductMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface ProductService {
    Specification<Product> prepare(String keyword, String category, double price);
    int count(Specification<Product> specification);
    List<Product> searchAll_paging(Specification<Product> specification, int page, int size);
}
