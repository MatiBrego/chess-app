package edu.austral.dissis.chess.rule.compound

import edu.austral.dissis.chess.board.Board
import edu.austral.dissis.chess.board.NormalBoard
import edu.austral.dissis.chess.game.Move
import edu.austral.dissis.chess.result.rule.*
import edu.austral.dissis.chess.rule.Rule

class OrRule(
    private val rules: List<Rule>
): Rule {
    override fun validateMove(move: Move, board: Board): RuleResult {
        for (rule in rules){
            return when(val result = rule.validateMove(move, board)){
                ValidResult -> result
                is ValidWithExecutionResult -> result
                is InvalidResult -> continue
            }
        }
        return InvalidResult()
    }
}
