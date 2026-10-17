package com.pavswap.LearningSpringBoot;

import com.pavswap.LearningSpringBoot.DI.MessageSender;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MessageController {

    public final MessageSender ms;

    public MessageController(MessageSender ms) {
        this.ms = ms;
    }

    @GetMapping(path = "/paavan")
    public String returnMessage() {
        return ms.sendMessage();
    }
}
