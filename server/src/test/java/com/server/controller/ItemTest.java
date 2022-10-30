package com.server.controller;

import com.server.ServerApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;

@SpringBootTest(classes = ServerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ItemTest {
    @Resource
    private MockMvc mockMvc;

    @Test
    void testSaveCart() throws Exception {
        //Save succeeded
        String json = "{\"productId\": \"2\", \"userId\": \"2\",\"quantity\": \"1\"}";
        String responseString = mockMvc.perform(MockMvcRequestBuilders.post("/cart/saveCart")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json.getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse().getContentAsString();

        //return code 1 means Save succeeded
        assert responseString.charAt(8) == '1';

        //save failed
        json = "{\" productId\": \"2\", \"userId\": \"2\",\"quantity\": \"100\"}";
        responseString = mockMvc.perform(MockMvcRequestBuilders.post("/cart/saveCart")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json.getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse().getContentAsString();
        //return code 0 means login failed
        assert responseString.charAt(8) == '0';
    }


    @Test
    void testComment() throws Exception {
        //post comment success
        String json = "{\"productId\":\"1\",\"userId\":\"2\",\"rate\":\"5\",\"content\":\"Good.\"}";
        String responseString = mockMvc.perform(MockMvcRequestBuilders.post("/comment/saveComment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json.getBytes()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse().getContentAsString();
        //return code 1 means login succeeded
        assert responseString.charAt(8) == '1';


    }

    @Test
    void testShowItem() throws Exception {
        //show Item success

        String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/product/getProductById/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse().getContentAsString();
        ;
        //return code 1 means show Item succeeded
        assert responseString.charAt(8) == '1';


    }

    @Test
    void testShowComment() throws Exception {
        //show Item success

        String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/comment/getCommentList/1/1/3"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse().getContentAsString();
        ;
        //return code 1 means show Item succeeded
        assert responseString.charAt(8) == '1';

    }
}