package edu.austral.dissis.chess.factory

import edu.austral.dissis.chess.board.Board
import edu.austral.dissis.chess.executor.NormalExecutor
import edu.austral.dissis.chess.game.Game
import edu.austral.dissis.chess.game.checkmate.NormalCheckMateValidator
import edu.austral.dissis.chess.piece.Team
import edu.austral.dissis.chess.rule.Rule
import edu.austral.dissis.chess.rule.game.*

fun createNormalGame(): Game {
    return Game(
        board = createBoard(),
        turn = Team.WHITE,
        rules = createNormalRules(),
        checkMateRule = NormalCheckMateValidator(),
        moveExecutor = NormalExecutor()
    )
}

private fun createBoard(): Board {
    return createBoardWithSpecialPieces()
}

private fun createNormalRules(): List<Rule> {
    return listOf(
        PieceIsPresentValidator(),
        IsYourTurnValidator(),
        IsOccupiedByTeamValidator(),
        IsNotCheckValidator()
    )
}
