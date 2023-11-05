package edu.austral.dissis.server

import edu.austral.dissis.common.board.BoardSize
import edu.austral.dissis.common.board.Coordinate
import edu.austral.dissis.common.piece.Team

data class InitPayload(
    val boardSize: BoardSize,
    val pieces: List<PiecePayload>,
    val team: Team
)

data class MovePayload(
    val from: Coordinate,
    val to: Coordinate
)

sealed interface NewEventPayload;

data class SuccessfulMovePayload(
    val turn: Team,
    val piecePayload: List<PiecePayload>
) : NewEventPayload

data class EndOfGamePayload(
    val winner: Team
) : NewEventPayload

data class UnsuccessfulMovePayload(
    val message: String
) : NewEventPayload

data class PiecePayload(
    val id: String,
    val team: Team,
    val position: Coordinate,
    val pieceId: String
)