package com.example.scrumtool;

import com.example.scrumtool.controller.Controller;
import com.example.scrumtool.dao.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ScrumtoolApplicationTests {

	@Autowired
	Controller controller;
	
	@Test
	void testUpdateGroupInfo() {
		try {
			controller.updateGroupInfo("test", 12);
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
			controller.register(user);
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
			controller.login(user);
		} catch (Exception e) {
			//
		}
	}

}
