package org.labisd.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

	@RabbitListener(queues = "${app.queue.name}")
	public void receive(String message) throws InterruptedException {
		System.out.println(" [x] Received '" + message + "'");
		Thread.sleep(1000L);
	}

}
