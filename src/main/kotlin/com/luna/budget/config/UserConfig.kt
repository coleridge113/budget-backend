package com.luna.budget.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
class UserConfig {
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun userDetailService(): UserDetailsService {
        val guest = User.withUsername("guest")
            .password(passwordEncoder().encode("password").also { println("Encoded guest password: $it") })
            .roles("USER")
            .build()

        val admin = User.withUsername("admin")
            .password(passwordEncoder().encode("adminpass"))
            .roles("ADMIN")
            .build()

        val postman = User.withUsername("postman")
            .password(passwordEncoder().encode("password"))
            .roles("USER") 
            .build()

        return InMemoryUserDetailsManager(guest, admin, postman)
    }

    @Bean
    fun authenticationProvider(userDetailsService: UserDetailsService, passwordEncoder: PasswordEncoder): AuthenticationProvider {
        val authProvider = DaoAuthenticationProvider()
        authProvider.setUserDetailsService(userDetailsService)
        authProvider.setPasswordEncoder(passwordEncoder)
        return authProvider
    }
}
