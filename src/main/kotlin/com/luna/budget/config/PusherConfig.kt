package com.luna.budget.config

import com.pusher.rest.Pusher
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PusherConfig {
    @Value("\${PUSHER_APP_ID}")
    lateinit var appId: String

    @Value("\${PUSHER_KEY}")
    lateinit var key: String

    @Value("\${PUSHER_SECRET}")
    lateinit var secret: String

    @Value("\${PUSHER_CLUSTER}")
    lateinit var cluster: String

    @Bean
    fun pusher(): Pusher {
        return Pusher(appId, key, secret).apply {
            setCluster(cluster)
        }
    }
}
