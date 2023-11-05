package edu.austral.dissis.client

import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.netty.client.NettyClientBuilder
import java.net.InetSocketAddress

fun main() {
    val client = NettyClientBuilder.createDefault().withAddress(
        InetSocketAddress(8080)
    ).build()
    client.connect()

    client.send(Message("test", Unit))
}