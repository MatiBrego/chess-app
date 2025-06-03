package edu.austral.dissis.common.game

import edu.austral.dissis.chess.wincondition.NormalCheckMateValidator
import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.board.Coordinate
import edu.austral.dissis.common.executor.MoveExecutor
import edu.austral.dissis.common.executor.NormalExecutor
import edu.austral.dissis.common.piece.Team
import edu.austral.dissis.common.result.action.Action
import edu.austral.dissis.common.result.move.EndOfGameResult
import edu.austral.dissis.common.result.move.MoveResult
import edu.austral.dissis.common.result.move.SuccessfulResult
import edu.austral.dissis.common.result.move.UnsuccessfulResult
import edu.austral.dissis.common.result.rule.InvalidResult
import edu.austral.dissis.common.result.rule.ValidResult
import edu.austral.dissis.common.rule.Rule
import edu.austral.dissis.common.turn.OneEach
import edu.austral.dissis.common.turn.Turn
import edu.austral.dissis.common.wincondition.WinningConditionValidator

class Game(
    private var board: Board,
    private val turnManager: Turn = OneEach(Team.WHITE),
    private val rules: List<Rule>,
    private val winningConditionValidator: WinningConditionValidator = NormalCheckMateValidator(),
    private val moveExecutor: MoveExecutor = NormalExecutor()
) {

    fun move(from: Coordinate, to: Coordinate): MoveResult {
        val move = Move(from, to, turnManager.getCurrentTeam())

        val gameValidationResult = validateGameRules(move)
        if (gameValidationResult !is SuccessfulResult) return gameValidationResult

        val pieceValidationResult = validatePieceRules(move)
        if (pieceValidationResult !is SuccessfulResult) return pieceValidationResult

        val turnValidationResult = validateTurnRules(move)
        if (turnValidationResult !is SuccessfulResult) return turnValidationResult

        if (isCheckMate(pieceValidationResult.game.getBoard()))
            return EndOfGameResult(turnManager.getCurrentTeam(), pieceValidationResult.game.getBoard())

        return pieceValidationResult
    }

    fun getBoard(): Board {
        return this.board
    }

    fun getTurn(): Team {
        return this.turnManager.getCurrentTeam()
    }

    private fun validateGameRules(move: Move): MoveResult {
        for (rule in rules){
            return when (val result = rule.validateMove(move, board)){
                is ValidResult -> continue
                is InvalidResult -> UnsuccessfulResult(result.getMessage())
            }
        }
       return SuccessfulResult(this)
    }

    private fun validatePieceRules(move: Move): MoveResult {
        val piece = board.getPiece(move.getFrom()) ?: throw NoSuchElementException("No piece found")

        return when (val result = piece.validateMove(move, board)){
            is ValidResult -> doMove(move, board, result.getActions())
            is InvalidResult -> UnsuccessfulResult(result.getMessage())
        }
    }

    private fun doMove(move: Move, board: Board, actions: List<Action>): SuccessfulResult {
        var modifiedBoard: Board = moveExecutor.executeSingleMove(move, board)
            modifiedBoard = moveExecutor.executeActions(actions, move, modifiedBoard)
        return SuccessfulResult(
            Game(
                modifiedBoard,
                turnManager.getNextTurn(move, this.board),
                rules,
                winningConditionValidator,
                moveExecutor
            )
        )
    }

    private fun getEnemyTeam(): Team {
        return when(turnManager.getCurrentTeam()){
            Team.WHITE -> Team.BLACK
            Team.BLACK -> Team.WHITE
        }
    }

    private fun isCheckMate(board: Board): Boolean{
        return winningConditionValidator.isWin(board, getEnemyTeam(), rules)
    }

    private fun validateTurnRules(move: Move): MoveResult {
        return when (val ruleResult = turnManager.validateTurnRules(move, this.board)){
            is ValidResult -> SuccessfulResult(this)
            is InvalidResult -> UnsuccessfulResult(ruleResult.getMessage())
        }
    }
}
