package com.luna.budget.controller

import com.luna.budget.domain.Expense
import com.luna.budget.domain.ExpensePreset
import com.luna.budget.service.ExpensePresetService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@CrossOrigin(origins = ["*"])
@RestController
@RequestMapping("api/v1/expense-presets")
class ExpensePresetController(
    private val service: ExpensePresetService
) {
    
    @GetMapping("/")
    fun getAllExpensePresets(): ResponseEntity<List<ExpensePreset>> {
        return ResponseEntity.ok(service.getAllExpensePresets())
    }
}
