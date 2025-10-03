package com.luna.budget.controller

import com.luna.budget.model.Expense
import com.luna.budget.service.ExpenseService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["*"])
@RestController
@RequestMapping("api/v1")
class ExpenseController (
    private val service: ExpenseService
) {

    @GetMapping("/index", "/")
    fun index(): String {
        return "hello"
    }

    @GetMapping("/expenses")
    fun getExpenses(): ResponseEntity<List<Expense>> {
        return ResponseEntity.ok(service.getExpenses())
    }

    @GetMapping("/expenses/{id}")
    fun getExpenseById(@PathVariable("id") id: Int): ResponseEntity<Expense?> {
        return ResponseEntity.ok(service.getExpense(id))
    }

    @GetMapping("expenses/{type}")
    fun getExpenseByType(@PathVariable("type") type: String): ResponseEntity<List<Expense?>> {
        return ResponseEntity.ok(service.getExpenseByType(type))
    }
}