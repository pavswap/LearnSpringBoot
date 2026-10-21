package com.paavan.BasicAuth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class API {

    @GetMapping("/api")
    public String api() {
        return "API Accessed";
    }
}
