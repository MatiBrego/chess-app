package edu.austral.dissis.common.rule.compound

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.game.Move
import edu.austral.dissis.common.result.rule.InvalidResult
import edu.austral.dissis.common.result.rule.RuleResult
import edu.austral.dissis.common.result.rule.ValidResult
import edu.austral.dissis.common.result.rule.ValidWithExecutionResult
import edu.austral.dissis.common.rule.Rule
import java.lang.IllegalStateException

class And(
    private val rules: List<Rule>
): Rule {
    override fun validateMove(move: Move, board: Board): RuleResult {
        var validResult: RuleResult = ValidResult
        for (rule in rules){
            val newResult = rule.validateMove(move, board)
            if(newResult is InvalidResult){
                return newResult
            }
            validResult = updateValidResult(validResult, newResult)
        }
        return validResult
    }

    private fun updateValidResult(previousResult: RuleResult, newResult: RuleResult): RuleResult {
        return when(previousResult){
            ValidResult -> {
                when(newResult){
                    ValidResult -> previousResult
                    is ValidWithExecutionResult -> newResult
                    is InvalidResult -> throw IllegalStateException("New result should not be invalid in AND rule")
                }
            }

            is ValidWithExecutionResult -> {
                when(newResult){
                    ValidResult -> previousResult
                    is ValidWithExecutionResult -> ValidWithExecutionResult(
                        previousResult.getActions() + newResult.getActions()
                    )
                    is InvalidResult -> throw IllegalStateException("New result should not be invalid in AND rule")
                }
            }
            is InvalidResult -> {
                throw IllegalStateException("Previous result should not be invalid in AND rule")
            }
        }
    }
}
