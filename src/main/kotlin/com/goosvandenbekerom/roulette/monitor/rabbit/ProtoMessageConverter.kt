package com.goosvandenbekerom.roulette.monitor.rabbit

import com.google.protobuf.GeneratedMessageV3
import com.goosvandenbekerom.roulette.monitor.proto.RouletteProto.*
import org.springframework.amqp.core.Message
import org.springframework.amqp.core.MessageDeliveryMode
import org.springframework.amqp.core.MessageProperties
import org.springframework.amqp.support.converter.AbstractMessageConverter
import org.springframework.http.MediaType

class ProtoMessageConverter: AbstractMessageConverter() {
    override fun createMessage(o: Any, properties: MessageProperties): Message {
        o as GeneratedMessageV3

        val bytes = o.toByteArray()
        properties.contentLength = bytes.size.toLong()
        properties.contentType = ProtoMessage.MEDIA_TYPE
        properties.setHeader(ProtoMessage.TYPE_KEY, o::class.java.simpleName)
        properties.deliveryMode = MessageDeliveryMode.NON_PERSISTENT

        return Message(bytes, properties)
    }

    override fun fromMessage(message: Message): Any {
        val type = message.messageProperties.headers[ProtoMessage.TYPE_KEY]
        return when(type) {
            Error::class.java.simpleName -> Error.parseFrom(message.body)
            else -> throw Exception("Unsupported message type received. [$type]")
        }
    }

    object ProtoMessage {
        const val TYPE_KEY = "type"
        const val MEDIA_TYPE = MediaType.APPLICATION_OCTET_STREAM_VALUE
    }
}