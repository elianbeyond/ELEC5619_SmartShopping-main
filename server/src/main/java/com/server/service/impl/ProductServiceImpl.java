package com.server.service.impl;

import com.server.entity.Product;
import com.server.mapper.ProductMapper;
import com.server.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productRepo;

    public Specification<Product> prepare(String keyword, String category, double price) {
        final Specification<Product> specification = new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                final ArrayList<Predicate> preds = new ArrayList<>();
                if (category!=null&&!category.equals("")&&!category.equals("null")){
                    preds.add(cb.equal(root.get("category"),category));
                }
                if (keyword!=null&&!keyword.equals("")&&!keyword.equals(null)){
                    preds.add(cb.like(root.get("name"),"%"+keyword+"%"));
                }
                if (price>0){
                    preds.add(cb.le(root.get("price"),price));
                }
                Predicate a = cb.and(preds.toArray(new Predicate[0]));
                //order by
                Order desc = cb.desc(root.get("price"));
                return query.where(a).orderBy(desc).getRestriction();
            }
        };
        return specification;

    }

    public int count(Specification<Product> specification){
        return productRepo.findAll(specification).size();
    }

    public List<Product> searchAll_paging(Specification<Product> specification,int page,int size){
        Pageable pageable = PageRequest.of(page,size);
        return productRepo.findAll(specification,pageable).getContent();
    }

}
