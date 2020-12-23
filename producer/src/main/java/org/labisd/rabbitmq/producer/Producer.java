package org.labisd.rabbitmq.producer;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Producer {

	@Value("${app.queue.name}")
	String queueName;

	@Bean
	public Queue queue() {
		return new Queue(queueName);
	}

	@Autowired
	private Queue queue;

	@Autowired
	private RabbitTemplate rabbit;

	int count = 0;

	@Scheduled(fixedDelay = 1000, initialDelay = 500)
	public void send() {
		String message = "message " + count++;
		rabbit.convertAndSend(queue.getName(), message);
		System.out.println(" [x] Sent '" + message + "'");
	}

}
