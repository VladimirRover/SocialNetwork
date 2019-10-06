package com.vaha.justForFun.controller;

import com.vaha.justForFun.domain.Message;
import com.vaha.justForFun.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    MessageRepository repository;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<Message> messages = repository.findAll();
        model.put("messages", messages);
        return "main";
    }

    @PostMapping("/main")
    public String addMessage(@RequestParam String text, @RequestParam String tag, Map<String, Object> model) {
        Message message = new Message(text, tag);
        repository.save(message);
        Iterable<Message> messages = repository.findAll();
        model.put("messages", messages);
        return "main";

    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Message> messages;
        if (filter != null && !filter.isEmpty()) {
            messages = repository.findByTag(filter);
        } else {
            messages = repository.findAll();
        }
        model.put("messages", messages);
        return "main";
    }
}