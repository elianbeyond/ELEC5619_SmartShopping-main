package com.server.service.impl;

import com.server.common.R;
import com.server.entity.Cart;
import com.server.entity.Product;
import com.server.mapper.CartMapper;
import com.server.mapper.ProductMapper;
import com.server.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartMapper cartMapper;

    @Autowired
    ProductMapper productMapper;

    @Override
    public R save(Cart cart) {
        // Inventory Quantity
        Integer quantity = 0;
        //Total number of products added to the cart
        Integer productCount = cart.getQuantity();
        Integer productId = cart.getProductId();
        Product productById = productMapper.getProductById(productId);
        List<Cart> cartList = cartMapper.getCartProductId(productId);
        // This item has been added to stock
        int sum = cartList.stream().mapToInt(Cart::getQuantity).sum();
        if (null != productById) {
            quantity = productById.getQuantity();
            if (quantity == 0) {
                return R.error("This item is not in stock now !");
            }
            if (quantity == sum) {
                return R.error("The number of items exceeds the available stock!");
            }
            if (sum + cart.getQuantity() > quantity) {
                return R.error("The number of items exceeds the available stock!");
            }
        } else {
            return R.error("The product was not found");
        }
        Cart cartByUserIdAndProductId = cartMapper.getCartByUserIdAndProductId(cart.getUserId(), cart.getProductId());
        if (null != cartByUserIdAndProductId) {
            productCount += cartByUserIdAndProductId.getQuantity();
            cartByUserIdAndProductId.setQuantity(productCount);
            cart.setId(cartByUserIdAndProductId.getId());
            cart.setQuantity(productCount);
        }
        return R.success(cartMapper.save(cart));
    }
}
