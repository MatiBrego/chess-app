package edu.austral.dissis.server.payload

import edu.austral.dissis.common.board.BoardSize
import edu.austral.dissis.common.piece.Team

data class InitPayload(
    val boardSize: BoardSize,
    val pieces: List<PiecePayload>,
    val team: Team
)