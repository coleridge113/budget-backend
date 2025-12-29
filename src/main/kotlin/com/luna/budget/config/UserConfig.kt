package com.luna.budget.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.provisioning.InMemoryUserDetailsManager

@Configuration
class UserConfig(
    private val passwordEncoder: PasswordEncoder
) {
    @Bean
    fun userDetailService(): UserDetailsService {
        val user = User.withUsername("testuser")
            .password(passwordEncoder.encode("password"))
            .roles("USER")
            .build()

        val admin = User.withUsername("admin")
            .password(passwordEncoder.encode("adminpass"))
            .roles("ADMIN")
            .build()

        return InMemoryUserDetailsManager(user, admin)
    }
}
