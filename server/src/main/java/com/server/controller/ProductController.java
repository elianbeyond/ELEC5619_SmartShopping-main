package com.server.controller;


import com.server.common.R;
import com.server.entity.Product;
import com.server.entity.User;
import com.server.entity.vo.ProductVO;
import com.server.mapper.ProductMapper;
import com.server.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductMapper productRepo;

    @Autowired
    UserMapper userMapper;

    @GetMapping("getAll")
    public R getAllProducts(){
        List<ProductVO> result = new ArrayList<>();
        List<Product> products = (List<Product>) productRepo.findAll();
        ProductVO productVO;
        for (Product product : products) {
            productVO = new ProductVO();
            BeanUtils.copyProperties(product, productVO);
            Integer userId = product.getUserId();
            Optional<User> user = userMapper.findById(userId);
            if (user.isPresent()) {
                productVO.setSupplier(user.get().getUsername());
            }
            result.add(productVO);
        }
        return R.success(result);
    }

    @PostMapping("/getLength")
    public R ProductsLength(){
        return R.success(productRepo.count());
    }

    @PostMapping("/getMaxvalue")
    public R ProductsMaxvalue(){
        return R.success(productRepo.getMaxvalue());
    }

    @GetMapping("/getProductById/{productId}")
    public R getProductById(@PathVariable("productId") Integer productId){
        Product productById = productRepo.getProductById(productId);
        if (null != productById) {
            return R.success(productById);
        }
        return R.error("can't find this product");
    }

    // get products by username
    @GetMapping("/getByUser")
    public R getProductsByUsername(HttpServletRequest request){
        Integer userId = (Integer) request.getSession().getAttribute("user");
        List<Product> products = productRepo.findByUserId(userId);
        return R.success(products);
    }

    // delete product by id
    @DeleteMapping("/deleteProduct/{id}")
    public R deleteProduct(@PathVariable Integer id){
        productRepo.deleteById(id);
        return R.success("delete success");
    }
    

    // add new product to db
    @PostMapping("/addProduct")
    public R addProduct(HttpServletRequest request, @RequestBody Product product){
        Integer userId = (Integer) request.getSession().getAttribute("user");
        product.setUserID(userId);
        productRepo.save(product);
        return R.success("add success");
    }


}