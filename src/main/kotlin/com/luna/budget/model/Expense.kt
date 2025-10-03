package com.luna.budget.model

import jakarta.persistence.*

@Entity
@Table(name = "expenses")
data class Expense(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    val name: String,
    val cost: Long,
    val category: String,
    val type: String
)