package com.luna.budget.domain

import jakarta.annotation.Nullable
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "expenses")
data class Expense(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Int,
    val name: String?,
    val cost: Long,
    val category: String,
    val type: String,
    val date: LocalDate = LocalDate.now()
)