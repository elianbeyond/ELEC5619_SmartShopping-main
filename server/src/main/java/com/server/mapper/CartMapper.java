package com.server.mapper;

import com.server.entity.Cart;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

//spring-data-jpa
//select * from user where name=?


public interface CartMapper extends CrudRepository<Cart, Integer> {

    @Query(nativeQuery = true, value = "select * from cart where user_id = ?1 and product_id = ?2")
    Cart getCartByUserIdAndProductId(Integer userId, Integer productId);

    @Query(nativeQuery = true, value = "select * from cart where product_id = ?1")
    List<Cart> getCartProductId(Integer productId);
    @Query(nativeQuery = true, value = "select * from cart where user_id =?1")
    List<Cart> getCartByUserId(Integer userId);
    @Transactional
    void deleteByUserId(Integer userId);

    @Transactional
    void deleteByUserIdAndProductId(Integer userId,Integer productId);


}
