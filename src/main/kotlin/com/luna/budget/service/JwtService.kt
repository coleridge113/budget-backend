package com.luna.budget.service

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.Date
import javax.crypto.SecretKey

@Service
class JwtService(@Value("\${jwt.secret}") secret: String) {
    private val key: SecretKey = Keys.hmacShaKeyFor(secret.toByteArray())
    private val expirationMillis = 15 * 60 * 1000 // 15 mins

    fun generateToken(username: String, roles: List<String>): String {
        return Jwts.builder()
            .subject(username)
            .claim("roles", roles)
            .issuedAt(Date())
            .expiration(Date(System.currentTimeMillis() + expirationMillis))
            .signWith(key)
            .compact()
    }

    fun extractUsername(token: String): String {
        return Jwts.parser().verifyWith(key).build()
            .parseEncryptedClaims(token).payload.subject
    }
}
