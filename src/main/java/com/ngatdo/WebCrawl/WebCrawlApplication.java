package com.ngatdo.WebCrawl;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WebCrawlApplication {

	public static final String EXCHANGE_NAME = "appExchange";
	public static final String QUEUE_NAME = "webCrawlQueue";
	public static final String ROUTING_KEY = "messages.key";

	public static void main(String[] args) {
		SpringApplication.run(WebCrawlApplication.class, args);
	}

	@Bean
	public TopicExchange appExchange() {
		return new TopicExchange(EXCHANGE_NAME);
	}


	@Bean
	public Queue appQueue() {
		return new Queue(QUEUE_NAME);
	}
	@Bean
	public Binding declareBinding() {
		return BindingBuilder.bind(appQueue()).to(appExchange()).with(ROUTING_KEY);
	}

	// You can comment the two methods below to use the default serialization / deserialization (instead of JSON)
	@Bean
	public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
	//	rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
		return rabbitTemplate;
	}

//	@Bean
//	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
//		return new Jackson2JsonMessageConverter();
//	}



}
