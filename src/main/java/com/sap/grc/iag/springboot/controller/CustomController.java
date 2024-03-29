package com.sap.grc.iag.springboot.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = CustomController.PATH)
public class CustomController {

    public static final String PATH = "/api/v1/custom";

    @GetMapping("/endpoint")
    // http://localhost:8080/boot.example/api/v1/custom/endpoint
    public String endpoint() {

        return "endpoint";
    }

    @GetMapping("/list")
    // http://localhost:8080/boot.example/api/v1/custom/list
    public List<String> list() {

        return List.of("I", " am ", "list");
    }
}
