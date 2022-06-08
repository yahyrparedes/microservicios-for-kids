package com.killerypa.products.controllers;


import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class PingController {

    @GetMapping("/ping")
    private String index() {
        return "{status: 'ok', ms: 'ms-product'}";
    }


}


