package com.server.service.impl;

import com.server.entity.Cart;
import com.server.mapper.CartMapper;
import com.server.service.CartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class CartServiceImplTest {
	@Resource
	private MockMvc mockMvc;
	@Autowired
	CartService cartService;
	@Autowired
	CartMapper cartMapper;

	@Test
	void saveTest() {
		Cart cart = new Cart();
		cart.setId(8);
		cart.setUserId(1);
		cart.setQuantity(1);
		cart.setProductId(1);
		cartService.save(cart);
	}

	@Test
	void getCartTest() throws Exception {
		String json = "{\"userId\": \"1\", \"productId\": \"1\"}";
		String responseString = mockMvc.perform(MockMvcRequestBuilders.post("/cart/getCart")
						.contentType(MediaType.APPLICATION_JSON)
						.content(json.getBytes()))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andReturn()
				.getResponse().getContentAsString();

		System.out.println(responseString);
	}

	@Test
	void deleteCartTest(){
		cartMapper.deleteByUserIdAndProductId(1,2);
	}
}