package com.example.scrumtool;

import com.example.scrumtool.controller.Controller;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class BaseTest {
    Logger logger = LoggerFactory.getLogger(BaseTest.class);
    @Before
    public void init() {
        logger.info("test start...");
    }
    
    @After
    public void after() {
        logger.info("test end...");
    }
}
