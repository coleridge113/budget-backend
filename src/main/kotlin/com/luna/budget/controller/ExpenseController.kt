package com.luna.budget.controller

import com.luna.budget.domain.Expense
import com.luna.budget.service.ExpenseService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["*"])
@RestController
@RequestMapping("api/v1/expenses")
class ExpenseController (
    private val service: ExpenseService
) {

    @GetMapping("/")
    fun getExpenses(): ResponseEntity<List<Expense?>> {
        return ResponseEntity.ok(service.getExpenses())
    }

    @GetMapping("{id}")
    fun getExpenseById(@PathVariable("id") id: Long): ResponseEntity<Expense?> {
        val expense = service.getExpenseById(id)
        return if (expense != null) {
            ResponseEntity.ok(expense)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("type/{type}")
    fun getExpenseByType(@PathVariable("type") type: String): ResponseEntity<List<Expense?>> {
        return ResponseEntity.ok(service.getExpensesByType(type))
    }

    @GetMapping("category/{category}")
    fun getExpenseByCategory(@PathVariable("category") category: String): ResponseEntity<List<Expense?>> {
        return ResponseEntity.ok(service.getExpensesByCategory(category))
    }

    @PostMapping("add")
    fun addExpense(@Valid @RequestBody expense: Expense): ResponseEntity<Expense> {
        return try {
            service.addExpense(expense)
            ResponseEntity.status(HttpStatus.CREATED).body(expense)
        } catch(e: Exception) {
            e.printStackTrace()
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
        }
    }

    @PostMapping("add/batch")
    fun addExpenses(@Valid @RequestBody expenses: List<Expense>): ResponseEntity<List<Expense>> {
        return try {
            service.addExpenses(expenses)
            ResponseEntity.status(HttpStatus.CREATED).body(expenses)
        } catch (e: Exception) {
            e.printStackTrace()
            ResponseEntity.internalServerError().body(null)
        }
    }

    @PutMapping("{id}")
    fun updateExpense(@Valid @RequestBody expense: Expense, @PathVariable("id") id: Long): ResponseEntity<Expense> {
        return try {
            service.updateExpense(expense, id)
            ResponseEntity.ok(expense)
        } catch (e: IllegalStateException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
        } catch (e: Exception) {
            ResponseEntity.internalServerError().body(null)
        }
    }

    @DeleteMapping("{id}")
    fun deleteExpense(@PathVariable("id") id: Long): ResponseEntity<Boolean> {
        return try {
            val expense = service.getExpenseById(id)
            if (expense != null) {
                service.deleteExpense(id)
                ResponseEntity.ok(true)
            } else {
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(false)
            }
        } catch (e: Exception) {
            ResponseEntity.internalServerError().body(false)
        }
    }
}