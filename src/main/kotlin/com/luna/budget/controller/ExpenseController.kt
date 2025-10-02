package com.luna.budget.controller

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin(origins = ["*"])
@RestController
class ExpenseController {

    @GetMapping("/index")
    fun index(): String {
        return "hello"
    }
}