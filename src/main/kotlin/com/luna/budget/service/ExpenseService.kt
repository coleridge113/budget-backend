package com.luna.budget.service
import com.luna.budget.domain.Expense
import com.luna.budget.repository.ExpenseRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ExpenseService (
    private val repository: ExpenseRepository
) {

    fun getExpenses(): List<Expense?> {
        return repository.findAll()
    }

    fun getExpenseById(id: Int): Expense? {
        return repository.findByIdOrNull(id)
    }

    fun getExpensesByType(type: String): List<Expense?> {
        return repository.findByType(type)
    }

    fun getExpensesByCategory(category: String): List<Expense?> {
        return repository.findByCategory(category)
    }

    fun addExpense(expense: Expense): Expense {
        return repository.save(expense)
    }

    fun updateExpense(expense: Expense, id: Int): Expense {
        val existingExpense = repository.findByIdOrNull(id)
        return if(existingExpense != null) {
            val updatedExpense = expense.copy(id = id)
            repository.save(updatedExpense)
        } else {
            throw IllegalStateException("Expense not found")
        }
    }

    fun deleteExpense(id: Int) {
        repository.deleteById(id)
    }
}