package com.server;

import com.alibaba.fastjson.JSON;
import com.server.ServerApplication;
import com.server.common.R;
import com.server.entity.User;
import com.server.mapper.UserMapper;
import com.server.mapper.VoucherMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static org.mockito.Mockito.*;
@SpringBootTest(classes = ServerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UserTest {
    @Resource
    private MockMvc mockMvc;
    @Test
    void testLogin() throws Exception {
        //login succeeded
        String json = "{\"username\": \"elian\", \"password\": \"123456\"}";
        String responseString = mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json.getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse().getContentAsString();

        //return code 1 means loggin succeeded
        assert responseString.charAt(8)=='1';

        //login failed
        json = "{\"username\": \"elian\", \"password\": \"12345\"}";
        responseString = mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json.getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse().getContentAsString();
        //return code 0 means login failed
        assert responseString.charAt(8)=='0';
    }

    @Transactional
    @Rollback
    @Test
    void testRegister() throws Exception {
        //register succeeded, rollback in case generate dirty data
        String json = "{\"username\":\"elianTest\",\"password\":\"21654\",\"userEmail\":\"814846181@qq.com\",\"address\":\"home\",\"adminCheck\":\"ELEC5619\",\"admin\":true,\"productId\":0,\"voucherId\":0}";
        String responseString = mockMvc.perform(MockMvcRequestBuilders.post("/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json.getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse().getContentAsString();
        //return code 1 means login succeeded
        assert responseString.charAt(8)=='1';

        //register failed
        json = "{\"username\":\"elian\",\"password\":\"21654\",\"userEmail\":\"814846181@qq.com\",\"address\":\"home\",\"adminCheck\":\"ELEC5619\",\"admin\":true,\"productId\":0,\"voucherId\":0}";
        responseString = mockMvc.perform(MockMvcRequestBuilders.post("/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json.getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse().getContentAsString();
        //return code 1 means login succeeded
        assert responseString.charAt(8)=='0';

    }

}