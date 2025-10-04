package com.luna.budget.repository

import com.luna.budget.domain.Expense
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ExpenseRepository : JpaRepository<Expense, Int> {

    @Query(value = "SELECT * FROM expenses WHERE type = :type", nativeQuery = true)
    fun findByType(type: String): List<Expense?>

    @Query(value = "SELECT * FROM expenses WHERE category = :category", nativeQuery = true)
    fun findByCategory(category: String): List<Expense?>
}