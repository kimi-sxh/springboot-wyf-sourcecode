package com.wisely.highlight_springmvc4.advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice //1
public class ExceptionHandlerAdvice { 

	/**
	 * <b>概要：</b>:
	 * 	@ExceptionHandler--全局处理控制器里的异常
	 * <b>作者：</b>SUXH</br>
	 * <b>日期：</b>2019/11/21 16:44 </br>
	 * @param:
	 * @return:
	 */
	@ExceptionHandler(value = Exception.class)//2
	public ModelAndView exception(Exception exception, WebRequest request) {
		ModelAndView modelAndView = new ModelAndView("error");// error页面
		modelAndView.addObject("errorMessage", exception.getMessage());
		return modelAndView;
	}

	/**
	 * <b>概要：</b>:
	 * 	@ModelAttribute--让@RequestMapping都能获得此处设置的键值对
	 * <b>作者：</b>SUXH</br>
	 * <b>日期：</b>2019/11/21 16:44 </br>
	 * @param:
	 * @return:
	 */
	@ModelAttribute //3
	public void addAttributes(Model model) {
		model.addAttribute("msg", "额外信息"); //3
	}

	/**
	 * <b>概要：</b>:
	 * 	@InitBinder--用来自动绑定前台请求参数到model中
	 * <b>作者：</b>SUXH</br>
	 * <b>日期：</b>2019/11/21 16:44 </br>
	 * @param:
	 * @return:
	 */
	@InitBinder //4
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.setDisallowedFields("id"); //5
	}
}
