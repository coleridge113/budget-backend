package com.luna.budget.service

import com.luna.budget.domain.Expense
import com.luna.budget.domain.ExpensePreset
import com.luna.budget.repository.ExpenseRepository
import com.luna.budget.repository.ExpensePresetRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import com.pusher.rest.Pusher
import com.luna.budget.common.Constants.PRIVATE_EXPENSE_CHANNEL
import com.luna.budget.common.Constants.EXPENSE_ADDED_EVENT
import com.fasterxml.jackson.databind.ObjectMapper

@Service
class ExpensePresetService (
    private val repository: ExpensePresetRepository
) {

    fun getAllExpensePresets(): List<ExpensePreset> {
        return repository.findAll()
    }
}
