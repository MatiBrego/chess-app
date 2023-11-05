package edu.austral.dissis.chess.factory

import edu.austral.dissis.chess.piece.ChessPieceType
import edu.austral.dissis.common.piece.Piece
import edu.austral.dissis.common.piece.Team
import edu.austral.dissis.common.rule.compound.Or

fun createRook(team: Team): Piece {
    return Piece(
        ChessPieceType.ROOK,
        Or(
            listOf(
                moveHorizontally(),
                moveVertically()
            )
        ),
        team
    )
}

fun createBishop(team: Team): Piece {
    return Piece(
        ChessPieceType.BISHOP,
        moveDiagonally(),
        team
    )
}

fun createQueen(team: Team): Piece {
    return Piece(
        ChessPieceType.QUEEN,
        Or(
            listOf(
                moveVertically(),
                moveHorizontally(),
                moveDiagonally()
            )
        ),
        team
    )
}

fun createKing(team: Team): Piece {
    return Piece(
        ChessPieceType.KING,
        Or(
            listOf(
                move1(),
                castleLeft(),
                castleRight()
            )
        ),
        team
    )
}

fun createKnight(team: Team): Piece {
    return Piece(
        ChessPieceType.KNIGHT,
        moveL(),
        team
    )
}

fun createPawn(team: Team): Piece {
    return Piece(
        ChessPieceType.PAWN,
        Or(
            listOf(
                crown(team),
                pawnCapture(),
                pawnMoveForwardFirstMove(),
                pawnMoveForward(),
            )
        ),
        team
    )
}

fun createArchbishop(team: Team): Piece {
    return Piece(
        ChessPieceType.ARCHBISHOP,
        Or(
            listOf(
                moveDiagonally(),
                moveL()
            )
        ),
        team
    )
}

fun createChancellor(team: Team): Piece {
    return Piece(
        ChessPieceType.CHANCELLOR,
        Or(
            listOf(
                moveHorizontally(),
                moveVertically(),
                moveL()
            )
        ),
        team
    )
}
