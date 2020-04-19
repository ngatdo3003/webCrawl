package com.ngatdo.WebCrawl.staging;


import org.apache.logging.log4j.LogManager;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class StagingConfiguration {

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("staging-webCrawl-exchange");
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(CachingConnectionFactory cachingConnectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(cachingConnectionFactory);
        // Thiết lập số consumer cho mỗi listener,
        // ở đây thiết lập là 3 vậy sẽ có 2 thread được phân cho mỗi listener
        factory.setConcurrentConsumers(3);
        factory.setMaxConcurrentConsumers(16);
        factory.setErrorHandler(ex -> {
            LogManager.getLogger(getClass()).error("Listener error " + ex.getMessage());
        });
        return factory;
    }

}
