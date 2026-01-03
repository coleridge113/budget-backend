package com.luna.budget.service

import com.luna.budget.domain.Expense
import com.luna.budget.repository.ExpenseRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import com.pusher.rest.Pusher
import com.luna.budget.common.Constants.PRIVATE_EXPENSE_CHANNEL
import com.luna.budget.common.Constants.EXPENSE_ADDED_EVENT
import com.fasterxml.jackson.databind.ObjectMapper

@Service
class ExpenseService (
    private val repository: ExpenseRepository,
    private val objectMapper: ObjectMapper,
    private val pusher: Pusher
) {

    fun getExpenses(): List<Expense> {
        return repository.findAll()
    }

    fun getExpenseById(id: Long): Expense? {
        return repository.findByIdOrNull(id)
    }

    fun getExpensesByType(type: String): List<Expense> {
        return repository.findByType(type)
    }

    fun getExpensesByCategory(category: String): List<Expense> {
        return repository.findByCategory(category)
    }

    fun addExpense(expense: Expense): Expense {
        val newExpense = repository.save(expense)
        triggerExpenseAddedEvent(expense)
        return newExpense
    }

    fun addExpenses(expenses: List<Expense>): List<Expense> {
        return try {
            repository.saveAll(expenses)
        } catch(e: IllegalArgumentException) {
            e.printStackTrace()
            emptyList()
        }
    }

    fun updateExpense(expense: Expense, id: Long): Expense {
        val existingExpense = repository.findByIdOrNull(id)
        return if(existingExpense != null) {
            val updatedExpense = existingExpense.copy(
                name = expense.name,
                amount = expense.amount,
                category = expense.category,
                type = expense.type,
                date = existingExpense.date

            )
            repository.save(updatedExpense)
        } else {
            throw IllegalStateException("Expense not found")
        }
    }

    fun deleteExpense(id: Long) {
        repository.deleteById(id)
    }

    private fun triggerExpenseAddedEvent(expense: Expense) {
        val expenseJson = objectMapper.writeValueAsString(expense)
        pusher.trigger(PRIVATE_EXPENSE_CHANNEL, EXPENSE_ADDED_EVENT, expenseJson)
    }
}
