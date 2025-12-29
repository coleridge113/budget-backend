package com.luna.budget.data

data class AuthRequest(
    val username: String,
    val password: String
)

data class AuthResponse(
    val accessToken: String,
    val tokenType: String = "Bearer"
)

