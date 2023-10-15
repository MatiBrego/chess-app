package edu.austral.dissis.chess.factory

import edu.austral.dissis.chess.board.Board
import edu.austral.dissis.chess.board.NormalBoard
import edu.austral.dissis.chess.game.Game
import edu.austral.dissis.chess.game.checkmate.NormalValidator
import edu.austral.dissis.chess.piece.Team
import edu.austral.dissis.chess.rule.Rule
import edu.austral.dissis.chess.rule.game.*
import edu.austral.dissis.chess.startingposition.NormalStartingPosition

fun createNormalGame(): Game {
    return Game(
        board = createBoard(),
        turn = Team.WHITE,
        createNormalRules(),
        NormalValidator()
    )
}

private fun createBoard(): Board {
    return NormalBoard(
        NormalStartingPosition().generate()
    )
}

private fun createNormalRules(): List<Rule> {
    return listOf(
        IsYourTurnValidator(),
        IsOccupiedByTeamValidator(),
        IsNotCheckValidator()
    )
}
