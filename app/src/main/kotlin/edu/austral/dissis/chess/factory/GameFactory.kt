package edu.austral.dissis.chess.factory

import edu.austral.dissis.chess.rule.game.IsNotCheckValidator
import edu.austral.dissis.chess.wincondition.NormalCheckMateValidator
import edu.austral.dissis.common.executor.NormalExecutor
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.piece.Team
import edu.austral.dissis.common.rule.Rule
import edu.austral.dissis.common.rule.game.IsOccupiedByTeamValidator
import edu.austral.dissis.common.rule.game.IsYourTurnValidator
import edu.austral.dissis.common.rule.game.PieceIsPresentValidator
import edu.austral.dissis.common.turn.OneEach
import edu.austral.dissis.common.wincondition.EatAllEnemyPiecesValidator

object NormalChessGame {
    operator fun invoke(): Game {
        return Game(
            board = createNormalStartingBoard(),
            turnManager = OneEach(Team.WHITE),
            rules = createNormalRules(),
            winningConditionValidator = NormalCheckMateValidator(),
            moveExecutor = NormalExecutor()
        )
    }
}

fun createNormalChessGame(): Game {
    return Game(
        board = createNormalStartingBoard(),
        turnManager = OneEach(Team.WHITE),
        rules = createNormalRules(),
        winningConditionValidator = NormalCheckMateValidator(),
        moveExecutor = NormalExecutor()
    )
}

fun createAlternativeGame(): Game {
    return Game(
        board = createBoardWithSpecialPieces(),
        turnManager = OneEach(Team.WHITE),
        rules = createAlternativeRules(),
        winningConditionValidator = EatAllEnemyPiecesValidator(),
        moveExecutor = NormalExecutor()
    )
}

fun createGameWithSpecialSizedBoard(): Game{
    return Game(
        board = createAlternativeDimensionsBoard(),
        turnManager = OneEach(Team.WHITE),
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
