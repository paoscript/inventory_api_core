package com.dicapisar.inventory_api_core.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PingController {

    @GetMapping("/ping")
    public String ping() { return "pong";}
}
