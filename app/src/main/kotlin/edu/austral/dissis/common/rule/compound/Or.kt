package edu.austral.dissis.common.rule.compound

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.game.Move
import edu.austral.dissis.common.result.action.Action
import edu.austral.dissis.common.result.rule.InvalidResult
import edu.austral.dissis.common.result.rule.RuleResult
import edu.austral.dissis.common.result.rule.ValidResult
import edu.austral.dissis.common.rule.Rule

class Or(
    private val rules: List<Rule>,
    private val actions: List<Action> = emptyList()
): Rule {

    fun withActions(actions: List<Action>): Rule {
        return Or(rules, actions)
    }

    override fun validateMove(move: Move, board: Board): RuleResult {
        for (rule in rules){
            return when(val result = rule.validateMove(move, board)){
                is ValidResult -> ValidResult(result.getActions() + actions)
                is InvalidResult -> continue
            }
        }
        return InvalidResult()
    }
}
