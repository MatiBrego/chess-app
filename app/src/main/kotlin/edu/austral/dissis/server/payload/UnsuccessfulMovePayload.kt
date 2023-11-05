package edu.austral.dissis.server.payload

data class UnsuccessfulMovePayload(
    val message: String
) : NewEventPayload