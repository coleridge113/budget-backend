package com.luna.budget.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import com.luna.budget.domain.ExpensePreset

@Repository
interface ExpensePresetRepository : JpaRepository<ExpensePreset, Long> 
