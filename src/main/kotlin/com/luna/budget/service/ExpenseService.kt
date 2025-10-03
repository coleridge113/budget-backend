package com.luna.budget.service
import com.luna.budget.model.Expense
import com.luna.budget.repository.ExpenseRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ExpenseService (
    private val repository: ExpenseRepository
) {

    fun getExpenses(): List<Expense> {
        return repository.findAll()
    }

    fun getExpense(id: Int): Expense? {
        return repository.findByIdOrNull(id)
    }

    fun getExpenseByType(type: String): List<Expense?> {
        return repository.findByType(type)
    }
}