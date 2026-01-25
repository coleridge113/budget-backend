package com.luna.budget.domain

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "expenses")
data class ExpensePreset(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Long,
    val amount: BigDecimal,
    val category: String,
    val type: String,
    val date: LocalDateTime = LocalDateTime.now()
)
