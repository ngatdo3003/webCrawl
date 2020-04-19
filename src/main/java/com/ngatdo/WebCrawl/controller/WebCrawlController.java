package com.ngatdo.WebCrawl.controller;

import com.ngatdo.WebCrawl.repository.TopicRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebCrawlController {

    @Autowired
    private TopicRepository topicRepository;


    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("message", "Welcome to home!!!");

        return "index";
    }


    @RequestMapping(value = { "/topics" }, method = RequestMethod.GET)
    public String topicList(Model model) {

        model.addAttribute("topics", topicRepository.findAll());

        return "topicList";
    }

}
