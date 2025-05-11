package com.example.sample.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping

@Controller
class PingController {
    @GetMapping("/")
    fun ping(model: Model): String {
        model["title"] = "pong"
        return "ping"
    }
}