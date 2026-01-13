package com.pavswap.LearningSpringBoot.configuration;

import com.pavswap.LearningSpringBoot.DI.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigClass {

    @Bean
    public Message message() {
        return new MessageIMPLI2();
    }

    @Bean
    public MessageSender sender(Message m) {
        return new MessageSenderIMPLI(m);
    }
}
