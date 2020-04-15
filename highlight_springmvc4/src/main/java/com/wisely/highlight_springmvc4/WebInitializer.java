package com.wisely.highlight_springmvc4;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * <b>概要：</b>:
 *      WebApplicationlnitializer 是Spring 提供用来配置Servlet 3.0＋配置的接口， 从而实现了
 * 替代web.xml的位置。实现此接口将会自动被Spring ServletContainerlnitializer （ 用来启动Serviet
 * 3.0 容器） 获取到。
 * <b>作者：</b>SUXH</br>
 * <b>日期：</b>2019/11/21 16:08 </br>
 */
public class WebInitializer implements WebApplicationInitializer {//1

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(MyMvcConfig.class);
        ctx.setServletContext(servletContext); //2
        
        Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx)); //3
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
        servlet.setAsyncSupported(true);//1

	}

}
