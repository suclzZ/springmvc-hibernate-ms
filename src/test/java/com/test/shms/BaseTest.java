package com.test.shms;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author sucl
 * @since 2019/3/16
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration("src/main/webapp")
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/configs/spring/applicationContext.xml"})
public class BaseTest {
}
