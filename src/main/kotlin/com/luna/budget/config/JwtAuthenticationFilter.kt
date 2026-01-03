package com.luna.budget.config

import com.luna.budget.service.JwtService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val jwtService: JwtService,
    private val userDetailsService: UserDetailsService
) : OncePerRequestFilter() {

    private val logger = LoggerFactory.getLogger(JwtAuthenticationFilter::class.java)

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        logger.info("JwtAuthenticationFilter: Processing request for ${request.requestURI}")
        val authHeader = request.getHeader("Authorization")

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            logger.warn("JwtAuthenticationFilter: No JWT token found or invalid format in Authorization header.")
            filterChain.doFilter(request, response)
            return
        }

        val jwt = authHeader.substring(7)
        logger.info("JwtAuthenticationFilter: Extracted JWT: $jwt")
        val username = jwtService.extractUsername(jwt)
        logger.info("JwtAuthenticationFilter: Extracted username: $username")

        if (SecurityContextHolder.getContext().authentication == null) {
            val userDetails = this.userDetailsService.loadUserByUsername(username)
            logger.info("JwtAuthenticationFilter: Loaded UserDetails for $username")
            if (jwtService.validateToken(jwt, userDetails)) {
                val authToken = UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.authorities
                )
                authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authToken
                logger.info("JwtAuthenticationFilter: Successfully authenticated user: $username")
            } else {
                logger.warn("JwtAuthenticationFilter: JWT token validation failed for user: $username")
            }
        } else {
            logger.info("JwtAuthenticationFilter: User $username already authenticated or no username found.")
        }
        filterChain.doFilter(request, response)
    }
}
