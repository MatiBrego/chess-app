package edu.austral.dissis.chess.rule.compound

import edu.austral.dissis.chess.board.Board
import edu.austral.dissis.chess.board.NormalBoard
import edu.austral.dissis.chess.game.Move
import edu.austral.dissis.chess.result.rule.*
import edu.austral.dissis.chess.rule.Rule
import java.lang.IllegalStateException

class AndRule(
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
                    is ValidWithExecutionResult -> previousResult
                    is InvalidResult -> throw IllegalStateException("New result should not be invalid in AND rule")
                }
            }
            is InvalidResult -> {
                throw IllegalStateException("Previous result should not be invalid in AND rule")
            }
        }
    }
}
