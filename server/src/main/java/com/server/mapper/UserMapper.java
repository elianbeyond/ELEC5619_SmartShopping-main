package com.server.mapper;

import com.server.entity.User;
import org.springframework.data.repository.CrudRepository;

//Spring data jpa supports user-defined simple functions.
// Repository will automatically generate sql based on the function name
//Such as: select * from user where name=?


public interface UserMapper extends CrudRepository<User, Integer> {

    public User findUserByUsername(String username);
    public User findUserById(Integer id);

}
