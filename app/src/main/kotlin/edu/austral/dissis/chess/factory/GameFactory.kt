package edu.austral.dissis.chess.factory

import edu.austral.dissis.chess.board.Board
import edu.austral.dissis.chess.executor.NormalExecutor
import edu.austral.dissis.chess.game.Game
import edu.austral.dissis.chess.wincondition.NormalCheckMateValidator
import edu.austral.dissis.chess.piece.Team
import edu.austral.dissis.chess.rule.Rule
import edu.austral.dissis.chess.rule.game.*
import edu.austral.dissis.chess.wincondition.EatAllEnemyPiecesValidator

fun createNormalGame(): Game {
    return Game(
        board = createNormalStartingBoard(),
        turn = Team.WHITE,
        rules = createNormalRules(),
        winningConditionValidator = NormalCheckMateValidator(),
        moveExecutor = NormalExecutor()
    )
}

fun createAlternativeGame(): Game{
    return Game(
        board = createBoardWithSpecialPieces(),
        turn = Team.WHITE,
        rules = createAlternativeRules(),
        winningConditionValidator = EatAllEnemyPiecesValidator(),
        moveExecutor = NormalExecutor()
    )
}

private fun createNormalRules(): List<Rule> {
    return listOf(
        PieceIsPresentValidator(),
        IsYourTurnValidator(),
        IsOccupiedByTeamValidator(),
        IsNotCheckValidator()
    )
}

private fun createAlternativeRules(): List<Rule>{
    return listOf(
        PieceIsPresentValidator(),
        IsYourTurnValidator(),
        IsOccupiedByTeamValidator(),
    )
}
