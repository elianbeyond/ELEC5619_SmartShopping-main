package com.server.controller;


import com.alibaba.fastjson.JSONObject;
import com.server.common.R;
import com.server.entity.Cart;
import com.server.entity.CartResult;
import com.server.entity.Product;
import com.server.entity.Voucher;
import com.server.mapper.CartMapper;
import com.server.mapper.ProductMapper;
import com.server.mapper.VoucherMapper;
import com.server.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    CartMapper cartMapper;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    VoucherMapper voucherMapper;

    @PostMapping("/saveCart")
    public R saveCart(@RequestBody Cart cart) {
        return cartService.save(cart);
    }
    @PostMapping("/getCart")
    public List<CartResult> getCart(@RequestBody Cart cart){
        Integer userId = cart.getUserId();
        List<Cart> cartList = cartMapper.getCartByUserId(userId);
        List<CartResult> cartResultsList = new ArrayList<>();

        for (Cart cart1 :cartList
             ) {
            Product product =productMapper.getProductById(cart1.getProductId());
            CartResult cartResult = new CartResult();
            cartResult.setProductName(product.getName());
            cartResult.setProductId(product.getId());
            cartResult.setCartId(cart1.getId());
            cartResult.setQuantity(cart1.getQuantity());
            cartResult.setPrice(product.getPrice());
            cartResultsList.add(cartResult);
        }
        return cartResultsList;
    }
    @PostMapping("/deleteProduct")
    public R deleteProduct(@RequestBody JSONObject cartId){
        int id = cartId.getIntValue("cartId");
        System.out.println(id);
        cartMapper.deleteById(id);
        return R.success("success");
    }

    @PostMapping("/getVoucher")
    public List<Voucher> getVoucher(@RequestBody Cart cart){
        Integer userId = cart.getUserId();
        return voucherMapper.findVouchersByUserId(userId);
    }


    @GetMapping("/purchase")
    public R purchase(Integer userId, String voucher){

        List<Cart> cartList = cartMapper.getCartByUserId(userId);
        for (Cart cart : cartList
             ) {
            int oldQuantity = productMapper.getQuantityById(cart.getProductId());
            productMapper.setQuantityById(oldQuantity-cart.getQuantity(), cart.getProductId());
        }
        System.out.println("request userid is " + userId);
        voucherMapper.deleteByName(voucher);
        cartMapper.deleteByUserId(userId);
        System.out.println("The Vouchers name is" + voucher);
        System.out.println("The Vouchers are" + voucherMapper.findVouchersByUserId(userId));
        return R.success("success");
    }

}