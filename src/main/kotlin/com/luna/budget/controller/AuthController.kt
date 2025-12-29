package com.luna.budget.controller

import org.springframework.web.bind.annotation.*
import org.springframework.http.ResponseEntity
import com.luna.budget.service.AuthService
import com.luna.budget.data.AuthRequest
import com.luna.budget.data.AuthResponse

@CrossOrigin(origins = ["*"])
@RestController
@RequestMapping("api/v1/auth")
class AuthController(
    private val authService: AuthService
) {

    @PostMapping("/login")
    fun login(@RequestBody req: AuthRequest): ResponseEntity<AuthResponse> {
        return ResponseEntity.ok(authService.login(req))
    }
}
