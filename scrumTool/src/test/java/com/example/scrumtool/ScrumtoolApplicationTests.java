package com.example.scrumtool;

import com.alibaba.fastjson.JSONObject;
import com.example.scrumtool.controller.Controller;
import com.example.scrumtool.dao.User;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
class ScrumtoolApplicationTests extends BaseTest{

	@Autowired
	Controller controller;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	void testUpdateGroupInfo() {
		
		try {
			
			MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("update/test/12")
							.accept(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andDo(MockMvcResultHandlers.print())
					.andReturn();
			logger.info(mvcResult.getResponse().getContentAsString());
		} catch (Exception e) {
			//
		}
	}
	
	@Test
	void testRegister() {
		try {
			User user = new User();
			user.setEmail("123@qq.com");
			user.setName("admin");
			user.setPassword("123");
			user.setRole("leader");
			
			MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/register")
							.accept(MediaType.APPLICATION_JSON).param(JSONObject.toJSONString(user)))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andDo(MockMvcResultHandlers.print())
					.andReturn();
			logger.info(mvcResult.getResponse().getContentAsString());
		} catch (Exception e) {
			//
		}
	}
	
	@Test
	void testLogin() {
		try {
			User user = new User();
			user.setEmail("123@qq.com");
			user.setName("admin");
			user.setPassword("123");
			user.setRole("leader");
			MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/login")
							.accept(MediaType.APPLICATION_JSON).param(JSONObject.toJSONString(user)))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andDo(MockMvcResultHandlers.print())
					.andReturn();
			logger.info(mvcResult.getResponse().getContentAsString());
		} catch (Exception e) {
			//
		}
	}

}
