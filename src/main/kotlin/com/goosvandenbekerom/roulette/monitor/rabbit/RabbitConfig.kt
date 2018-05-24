package com.goosvandenbekerom.roulette.monitor.rabbit

import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitConfig {
    companion object {
        const val topicExchangeName = "roulette-dealer-exchange"
        const val routingKey = "error"
        const val queueName = "roulette-error"
    }

    @Bean
    fun queue() = Queue(queueName, true)

    @Bean
    fun exchange() = TopicExchange(topicExchangeName)

    @Bean
    fun binding() = BindingBuilder.bind(queue()).to(exchange()).with(routingKey)!!

    @Bean
    fun converter() = ProtoMessageConverter()

    @Bean
    fun listenerFactory(cf: ConnectionFactory, configurer: SimpleRabbitListenerContainerFactoryConfigurer): SimpleRabbitListenerContainerFactory {
        val factory = SimpleRabbitListenerContainerFactory()
        configurer.configure(factory, cf)
        factory.setMessageConverter(converter())
        return factory
    }
}