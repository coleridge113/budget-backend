package com.luna.budget.repository

import com.luna.budget.model.Expense
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ExpenseRepository : JpaRepository<Expense, Int> {

    @Query(value = "SELECT * FROM expenses WHERE type = :type", nativeQuery = true)
    fun findByType(type: String): List<Expense?>
}