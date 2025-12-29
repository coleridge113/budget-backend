package com.luna.budget.service

import com.luna.budget.data.AuthRequest
import com.luna.budget.data.AuthResponse
import com.luna.budget.service.JwtService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val authManager: AuthenticationManager,
    private val userDetailsService: UserDetailsService,
    private val jwtService: JwtService
) {
    fun login(req: AuthRequest): AuthResponse {
        authManager.authenticate(
            UsernamePasswordAuthenticationToken(req.username, req.password)
        )
        val userDetails = userDetailsService.loadUserByUsername(req.username)
        val token = jwtService.generateToken(userDetails.username, userDetails.authorities.map { it.authority })
        return AuthResponse(token)
    }
}
