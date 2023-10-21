package edu.austral.dissis.common.rule.compound

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.game.Move
import edu.austral.dissis.common.result.rule.InvalidResult
import edu.austral.dissis.common.result.rule.RuleResult
import edu.austral.dissis.common.result.rule.ValidResult
import edu.austral.dissis.common.result.rule.ValidWithExecutionResult
import edu.austral.dissis.common.rule.Rule

class Or(
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
