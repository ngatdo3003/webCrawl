package com.ngatdo.WebCrawl.staging.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ngatdo.WebCrawl.WebCrawlApplication;
import com.ngatdo.WebCrawl.entities.Topic;
import com.ngatdo.WebCrawl.repository.TopicRepository;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class WebCrawlListener {
    private static final Logger logger = LoggerFactory.getLogger(WebCrawlListener.class);

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    TopicRepository topicRepository;

    @RabbitListener(id = "WebCrawl_LS",queues = WebCrawlApplication.QUEUE_NAME)
    public void listen(String data) {
        try {
            logger.info("Listener received : " + data);
            List<Topic> list = null;
                try {
                    list = Arrays.asList(objectMapper.readValue(data, Topic[].class));
                    if(list.size()>0) {
                        topicRepository.saveAll(list);
                    }
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
        } catch (Exception e) {
            logger.info("Has an error : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
