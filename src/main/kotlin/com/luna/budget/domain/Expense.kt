package com.luna.budget.domain

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate

@Entity
@Table(name = "expenses")
data class Expense(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Long,
    val name: String?,
    val amount: BigDecimal,
    val category: String,
    val type: String,
    val date: LocalDate = LocalDate.now()
)