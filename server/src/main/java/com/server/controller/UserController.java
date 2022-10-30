package com.server.controller;

import com.server.common.R;
import com.server.entity.User;
import com.server.entity.Voucher;
import com.server.mapper.UserMapper;
import com.server.mapper.VoucherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Optional;


@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    UserMapper userRepo;
    @Autowired
    VoucherMapper VoucherRepo;

    @PostMapping("/login")
    public R<User> login(HttpServletRequest request, @RequestBody User user){

//        1.The password submitted by the page is encrypted in md5
        String username = user.getUsername();
        String password = user.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));

//        2.Query the database according to the submitted username
        User existUser = userRepo.findUserByUsername(username);
//        3.If it is not found, the failure result is returned
        if(existUser == null){
            return R.error("login failed");
        }
//        4.It is found that the comparison passwords are the same. If they are inconsistent, return failure
        if(!existUser.getPassword().equals(password)){
            return R.error("failed:wrong password");
        }
//        5.Check whether the employee is disabled. If disabled, failure is returned
        if(existUser.isBan() ){
            return R.error("this account has been banned");
        }
//        6.Return successful results
        request.getSession().setAttribute("user",existUser.getId());
        return R.success(existUser);
    }
    @GetMapping("/editprofile")
    public R<User> get(HttpServletRequest request){
        var obj = request.getSession().getAttribute("user");
        var id = (int) obj;
        User u = userRepo.findUserById(id);
        System.out.println(u.getUsername());
        System.out.println("Session id is" + request.getSession().getId());
        return R.success(u);
    }
    @GetMapping("/name")
    public String getName(HttpServletRequest request){
        var obj = request.getSession().getAttribute("user");
        var id = (int) obj;
        User u = userRepo.findUserById(id);
        System.out.println(u.getUsername());
        System.out.println("Session id is" + request.getSession().getId());
        return u.getUsername();
    }
    @PostMapping("/updateinfo")
    public R<User>  updateInfo(HttpServletRequest request, @RequestBody User user){
//        System.out.println(user.getUsername()+user.getUserEmail()+user.getPassword());
        String username = user.getUsername();
        String password = user.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));

        User existUser = userRepo.findUserByUsername(username);
        // user exist
        if(existUser != null){
        existUser.setUsername(username);
        existUser.setUserEmail(user.getUserEmail());
        existUser.setAddress(user.getAddress());
        existUser.setPassword(password);
        userRepo.save(existUser);
        System.out.println(existUser);
        System.out.println("Session id is" + request.getSession().getId());
        request.getSession().setAttribute("user",existUser.getId());
        return R.success(existUser);
        }else {
            return R.error("no such user");
        }
    }

    @PostMapping("/register")
    public R<String> register(HttpServletRequest request, @RequestBody User user){

//        1.The password submitted by the page is encrypted in md5
        String username = user.getUsername();
        String password = user.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));

//        2.Query the database according to the submitted username
        User existUser = userRepo.findUserByUsername(username);
//        3.Found, indicating that the user name already exists, and the failure result is returned
        if(existUser != null){
            return R.error("username already existed");
        }
//        4.If not found, execute the save operation
        user.setPassword(password);
        userRepo.save(user);

        //When registering, two coupons are allocated to voucher table according to the user id
        Voucher voucher1 = new Voucher();
        Voucher voucher2 = new Voucher();
        voucher1.setUserId(user.getId());
        voucher1.setName("10percent off");
        voucher1.setPrice(0);
        voucher1.setRate(0.9);
        VoucherRepo.save(voucher1);
        voucher2.setUserId(user.getId());
        voucher2.setName("10 dollar off");
        voucher2.setPrice(10);
        voucher2.setRate(1);
        VoucherRepo.save(voucher2);

//        5.Return successful results
        request.getSession().setAttribute("user",user.getId());

        return R.success("register success");
    }


    //For test
    @GetMapping("/{id}")
    public R<User> getUser(@PathVariable Integer id){
        return R.success(userRepo.findById(id).get());
    }
}

