package com.wisely.highlight_spring4.ch2.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * <b>概要：</b>:
 * 		发布事件
 * <b>作者：</b>SUXH</br>
 * <b>日期：</b>2019/11/21 15:33 </br>
 * @param:
 * @return:
 */
@Component
public class DemoPublisher {
	@Autowired
	ApplicationContext applicationContext;
	
	public void publish(String msg){
		applicationContext.publishEvent(new DemoEvent(this, msg));
	}

}
