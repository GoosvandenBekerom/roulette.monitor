package com.goosvandenbekerom.roulette.monitor.rabbit

import com.google.protobuf.Message
import com.goosvandenbekerom.roulette.monitor.domain.ErrorEntry
import com.goosvandenbekerom.roulette.monitor.proto.RouletteProto.*
import com.goosvandenbekerom.roulette.monitor.repositories.ErrorRepository
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class MessageListeners {
    @Autowired private lateinit var repo: ErrorRepository

    @RabbitListener(queues = [RabbitConfig.queueName], containerFactory = "listenerFactory")
    fun handleMessage(msg: Message) {
        when (msg) {
            is Error -> repo.save(ErrorEntry(msg.message, msg.context, msg.username))
            else -> println("Unsupported message received, ignoring")
        }
    }
}