package edu.austral.dissis.common.game

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.board.Coordinate
import edu.austral.dissis.common.executor.MoveExecutor
import edu.austral.dissis.common.executor.NormalExecutor
import edu.austral.dissis.common.wincondition.WinningConditionValidator
import edu.austral.dissis.chess.wincondition.NormalCheckMateValidator
import edu.austral.dissis.common.piece.Team
import edu.austral.dissis.common.result.rule.InvalidResult
import edu.austral.dissis.common.result.rule.ValidResult
import edu.austral.dissis.common.result.rule.ValidWithExecutionResult
import edu.austral.dissis.common.result.action.Action
import edu.austral.dissis.common.result.move.EndOfGameResult
import edu.austral.dissis.common.result.move.MoveResult
import edu.austral.dissis.common.result.move.SuccessfulResult
import edu.austral.dissis.common.result.move.UnsuccessfulResult
import edu.austral.dissis.common.rule.Rule

class Game(
    private var board: Board,
    private val turn: Team = Team.WHITE,
    private val rules: List<Rule>,
    private val winningConditionValidator: WinningConditionValidator = NormalCheckMateValidator(),
    private val moveExecutor: MoveExecutor = NormalExecutor()
) {

    fun move(from: Coordinate, to: Coordinate): MoveResult {
        val move = Move(from, to, turn)

        val gameValidationResult = validateGameRules(move)
        if (gameValidationResult !is SuccessfulResult) return gameValidationResult

        val pieceValidationResult = validatePieceRules(move)
        if (pieceValidationResult !is SuccessfulResult) return pieceValidationResult

        if (isCheckMate(pieceValidationResult.game.getBoard())) return EndOfGameResult(turn)

        return pieceValidationResult
    }

    fun getBoard(): Board {
        return this.board
    }

    fun getTurn(): Team {
        return this.turn
    }

    private fun validateGameRules(move: Move): MoveResult {
        for (rule in rules){
            return when (val result = rule.validateMove(move, board)){
                ValidResult -> continue
                is ValidWithExecutionResult -> continue
                is InvalidResult -> UnsuccessfulResult(result.getMessage())
            }
        }
       return SuccessfulResult(this)
    }

    private fun validatePieceRules(move: Move): MoveResult {
        val piece = board.getPiece(move.getFrom()) ?: throw NoSuchElementException("No piece found")

        return when (val result = piece.validateMove(move, board)){
            ValidResult -> executeSingleMove(move, board)
            is ValidWithExecutionResult -> executeActions(result.getActions(), move, board)
            is InvalidResult -> UnsuccessfulResult(result.getMessage())
        }
    }

    private fun executeSingleMove(move: Move, board: Board): SuccessfulResult {
        val modifiedBoard: Board = moveExecutor.executeSingleMove(move, board)
        return SuccessfulResult(
            Game(
                modifiedBoard,
                getEnemyTeam(),
                rules,
                winningConditionValidator,
                moveExecutor
            )
        )
    }

    private fun executeActions(actions: List<Action>, move: Move, board: Board): SuccessfulResult {
        var modifiedBoard: Board = moveExecutor.executeSingleMove(move, board)
            modifiedBoard = moveExecutor.executeActions(actions, move, modifiedBoard)
        return SuccessfulResult(
            Game(
                modifiedBoard,
                getEnemyTeam(),
                rules,
                winningConditionValidator,
                moveExecutor
            )
        )
    }

    private fun getEnemyTeam(): Team {
        return when(turn){
            Team.WHITE -> Team.BLACK
            Team.BLACK -> Team.WHITE
        }
    }

    private fun isCheckMate(board: Board): Boolean{
        return winningConditionValidator.isWin(board, getEnemyTeam(), rules)
    }
}
