package com.server.mapper;

import com.server.entity.User;
import com.server.entity.Voucher;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

//Spring data jpa supports user-defined simple functions.
// Repository will automatically generate sql based on the function name
//Such as: select * from user where name=?


/**
 * @author CaoBingxuan
 */
public interface VoucherMapper extends CrudRepository<Voucher, Integer> {

    public Voucher findVoucherById(Integer id);
    @Query(nativeQuery = true, value = "select * from voucher where user_id =?1")
    public List<Voucher> findVouchersByUserId(Integer id);

    @Transactional
    void deleteByUserId(Integer userId);
    @Transactional
    void deleteByName(String name);
}
