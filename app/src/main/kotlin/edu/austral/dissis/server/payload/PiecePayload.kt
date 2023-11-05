package edu.austral.dissis.server.payload

import edu.austral.dissis.common.board.Coordinate
import edu.austral.dissis.common.piece.Team

data class PiecePayload(
    val id: String,
    val team: Team,
    val position: Coordinate,
    val pieceId: String
)