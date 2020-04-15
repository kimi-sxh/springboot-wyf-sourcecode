package com.wisely.highlight_springmvc4.web.ch4_6;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.wisely.highlight_springmvc4.MyMvcConfig;
import com.wisely.highlight_springmvc4.service.DemoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MyMvcConfig.class})
@WebAppConfiguration("src/main/resources") //1 用来声明ApplicationContext是一个WebApplicationContext
public class TestControllerIntegrationTests {
	private MockMvc mockMvc; //2 模拟mvc对象
	
	@Autowired
	private DemoService demoService;//3 可以在测试用例中注入spring bean
	
	@Autowired 
	WebApplicationContext wac; //4 可注入webApplicationContext
	
    @Autowired 
    MockHttpSession session; //5 可注入模拟HttpSession
    
    @Autowired 
    MockHttpServletRequest request; //6 可注入模拟HttpRequest
    
    @Before //7 在测试前进行初始化工作
    public void setup() {
    	mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build(); //2
	}
	
	@Test
	public void testNormalController() throws Exception{
		mockMvc.perform(get("/normal")) //8 get请求
				.andExpect(status().isOk())//9 期待返回200
				.andExpect(view().name("page"))//10 预期view的名称为page
				.andExpect(forwardedUrl("/WEB-INF/classes/views/page.jsp"))//11 转向真正路径
				.andExpect(model().attribute("msg", demoService.saySomething()));//12
				
	}
	
	@Test
	public void testRestController() throws Exception{
		mockMvc.perform(get("/testRest")) //13
        .andExpect(status().isOk())
         .andExpect(content().contentType("text/plain;charset=UTF-8"))//14
        .andExpect(content().string(demoService.saySomething()));//15
	}

}
