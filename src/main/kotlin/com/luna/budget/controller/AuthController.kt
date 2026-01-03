package com.luna.budget.controller

import org.springframework.web.bind.annotation.*
import org.springframework.http.ResponseEntity
import com.luna.budget.service.AuthService
import com.luna.budget.service.JwtService
import com.luna.budget.data.AuthRequest
import com.luna.budget.data.AuthResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import com.pusher.rest.Pusher

@CrossOrigin(origins = ["*"])
@RestController
@RequestMapping("api/v1/auth")
class AuthController(
    private val authService: AuthService,
    private val jwtService: JwtService,
    private val pusher: Pusher
) {

    @PostMapping("/login")
    fun login(@RequestBody req: AuthRequest): ResponseEntity<AuthResponse> {
        return ResponseEntity.ok(authService.login(req))
    }

    @PostMapping("/pusher/auth")
    fun authenticateChannel(
        @RequestParam("channel_name") channelName: String,
        @RequestParam("socket_id") socketId: String,
        request: HttpServletRequest
    ): ResponseEntity<String> {
        val token = request.getHeader("Authorization")?.removePrefix("Bearer ")
        val isValidToken = token != null && jwtService.validateToken(token)

        if (!isValidToken) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build()
        }

        val auth = pusher.authenticate(socketId, channelName)
        return ResponseEntity.ok(auth)
    }
}
