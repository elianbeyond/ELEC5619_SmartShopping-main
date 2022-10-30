package com.server.controller;

import com.server.common.R;
import com.server.entity.Product;
import com.server.mapper.ProductMapper;
import com.server.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    ProductService productService;
    @Autowired
    ProductMapper productRepo;

    @GetMapping("/{keywords}/{category}/{maxprice}/{page}/{size}")
    public R SearchProducts(
            @PathVariable("keywords") String keywords,
            @PathVariable("category") String category,
            @PathVariable("maxprice") double maxprice,
            @PathVariable("page") int page,
            @PathVariable("size") int size
    )
    {
        Specification<Product> prepare = productService.prepare(keywords,category,maxprice);
        List<Product> products = productService.searchAll_paging(prepare,page,size);
        int count = productService.count(prepare);
        HashMap<String,Object> result = new HashMap<>();
        result.put("products",products);
        result.put("count",count);
        return R.success(result);
    }

    @GetMapping("/{keywords}/{maxprice}/{page}/{size}")
    public R SearchProducts2(
            @PathVariable("keywords") String keywords,
            @PathVariable("maxprice") double maxprice,
            @PathVariable("page") int page,
            @PathVariable("size") int size
    )
    {
        Specification<Product> prepare = productService.prepare(keywords,null,maxprice);
        List<Product> products = productService.searchAll_paging(prepare,page,size);
        int count = productService.count(prepare);
        HashMap<String,Object> result = new HashMap<>();
        result.put("products",products);
        result.put("count",count);
        return R.success(result);
    }


}
