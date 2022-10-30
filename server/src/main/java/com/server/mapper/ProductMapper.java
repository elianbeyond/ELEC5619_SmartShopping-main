package com.server.mapper;

import com.server.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ProductMapper extends JpaRepository<Product,Integer>, JpaSpecificationExecutor<Product> {

    @Query(value = "select MAX(price) from Product")
    double  getMaxvalue();

    @Query(nativeQuery = true, value = "select * from product where id = ?1")
    Product getProductById(Integer id);

    @Query(value = "select * from Product where Product.user_id = ?1",nativeQuery = true)
    List<Product> findByUserId(Integer userId);
    @Query(value ="select quantity from Product where id =?1", nativeQuery = true)
    int getQuantityById(Integer id);
    @Transactional
    @Modifying
    @Query(value = "update Product set quantity=?1 where id=?2 ", nativeQuery = true)
    void setQuantityById(Integer quantity, Integer id);
}

