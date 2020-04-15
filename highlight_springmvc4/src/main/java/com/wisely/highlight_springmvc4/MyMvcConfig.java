package com.wisely.highlight_springmvc4;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.wisely.highlight_springmvc4.interceptor.DemoInterceptor;
import com.wisely.highlight_springmvc4.messageconverter.MyMessageConverter;

/**
 * <b>概要：</b>:
 * 		自定义mvc配置
 * <b>作者：</b>SUXH</br>
 * <b>日期：</b>2020/3/6 14:22 </br> 
 */
@Configuration
@EnableWebMvc// 1
@EnableScheduling
@ComponentScan("com.wisely.highlight_springmvc4")
public class MyMvcConfig extends WebMvcConfigurerAdapter {// 2


	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/classes/views/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setViewClass(JstlView.class);
		return viewResolver;
	}

	//--------------------静态资源配置------------------------
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/assets/**").addResourceLocations(
				"classpath:/assets/");// 3
	}
	//--------------------静态资源配置------------------------


	//----------------添加拦截器--------------------------
	@Bean
	public DemoInterceptor demoInterceptor() {
		return new DemoInterceptor();
	}
	@Override
	public void addInterceptors(InterceptorRegistry registry) {// 2
		registry.addInterceptor(demoInterceptor());
	}
	//----------------添加拦截器--------------------------


	//--------------------无任何业务逻辑只是简单页面转向------------
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/index").setViewName("/index");
		registry.addViewController("/toUpload").setViewName("/upload");
		registry.addViewController("/converter").setViewName("/converter");
		registry.addViewController("/sse").setViewName("/sse");
		registry.addViewController("/async").setViewName("/async");
	}
	//--------------------无任何业务逻辑只是简单页面转向------------

	//---------------路径匹配参数配置------------------
	 @Override
	 public void configurePathMatch(PathMatchConfigurer configurer) {
	 configurer.setUseSuffixPatternMatch(false);
	 }
	//---------------路径匹配参数配置------------------



	 //----------------------------文件上传配置-------------------
	 /**
	  * <b>概要：</b>:
	  * 	文件上传配置
	  * <b>作者：</b>SUXH</br>
	  * <b>日期：</b>2019/11/21 19:41 </br>
	  * @param:
	  * @return:
	  */
	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(1000000);
		return multipartResolver;
	}
	//----------------------------文件上传配置-------------------


	//------------------------HttpMessageConverter配置 -----------------------
	/**
	 * <b>概要：</b>:
	 * 		configMessageConverters:重载会覆盖掉Spring MVC 默认注册的多个 HttpMessageConverter
	 * 	extendMessageConverters ： 仅添加一个自定义的HttpMessageConverter ， 不覆盖默认注册的HttpMessageConvertero
	 * <b>作者：</b>SUXH</br>
	 * <b>日期：</b>2019/11/21 19:37 </br>
	 * @param:
	 * @return:
	 */
	@Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(converter());
    }

    /**
     * <b>概要：</b>:
	 * 		自定义HttpMessageConverter
     * <b>作者：</b>SUXH</br>
     * <b>日期：</b>2019/11/21 19:40 </br>
     * @param:
     * @return:
     */
	@Bean 
	public MyMessageConverter converter(){
		return new MyMessageConverter();
	}
	//------------------------HttpMessageConverter配置 -----------------------
	

}
