package edu.austral.dissis.server.payload

import edu.austral.dissis.common.piece.Team

data class SuccessfulMovePayload(
    val turn: Team,
    val piecePayload: List<PiecePayload>
) : NewEventPayload