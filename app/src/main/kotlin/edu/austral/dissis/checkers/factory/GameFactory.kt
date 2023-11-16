package edu.austral.dissis.checkers.factory

import edu.austral.dissis.checkers.turn.CheckersTurn
import edu.austral.dissis.common.executor.NormalExecutor
import edu.austral.dissis.common.game.Game
import edu.austral.dissis.common.piece.Team
import edu.austral.dissis.common.rule.Rule
import edu.austral.dissis.common.rule.game.IsNotOccupiedValidator
import edu.austral.dissis.common.rule.game.IsYourTurnValidator
import edu.austral.dissis.common.rule.game.PieceIsPresentValidator
import edu.austral.dissis.common.wincondition.EatAllEnemyPiecesValidator

fun createCheckersNormalGame(): Game {
    return Game(
        board = createCheckersStartingBoard(),
        turnManager = CheckersTurn(Team.WHITE),
        rules = createNormalRules(),
        winningConditionValidator = EatAllEnemyPiecesValidator(),
        moveExecutor = NormalExecutor()
    )
}

fun createNormalRules(): List<Rule>{
    return listOf(
        PieceIsPresentValidator(),
        IsYourTurnValidator(),
        IsNotOccupiedValidator(),
    )
}