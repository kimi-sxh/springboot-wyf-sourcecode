package com.wisely.ch9_3_5;

import com.wisely.ch9_3_5.rabbitmq.MQSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Ch935Application.class)
public class RabbitMQSenderTest {

	@Autowired
	private MQSender mqSender;

	@Test
	public void sendLazy() throws  Exception {
		while(true) {
			int timemills = new Random().nextInt(10000 - 1000) +1000;
			Thread.sleep(timemills);
			String msg = timemills + "在" + LocalDateTime.now() +"下单！---------";
			mqSender.sendLazy(msg);

		}
	}

}
