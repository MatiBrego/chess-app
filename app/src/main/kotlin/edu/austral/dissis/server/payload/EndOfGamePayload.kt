package edu.austral.dissis.server.payload

import edu.austral.dissis.common.piece.Team

data class EndOfGamePayload(
    val winner: Team
) : NewEventPayload