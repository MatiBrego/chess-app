package edu.austral.dissis.chess.game

import edu.austral.dissis.chess.board.Board
import edu.austral.dissis.chess.board.Coordinate
import edu.austral.dissis.chess.game.checkmate.CheckMateValidator
import edu.austral.dissis.chess.piece.Piece
import edu.austral.dissis.chess.piece.Team
import edu.austral.dissis.chess.result.action.*
import edu.austral.dissis.chess.result.move.*
import edu.austral.dissis.chess.result.rule.InvalidResult
import edu.austral.dissis.chess.result.rule.ValidResult
import edu.austral.dissis.chess.result.rule.ValidWithExecutionResult
import edu.austral.dissis.chess.rule.Rule

class Game(
    private var board: Board,
    private val turn: Team = Team.WHITE,
    private val rules: List<Rule>,
    private val checkMateRule: CheckMateValidator,
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
                is InvalidResult -> GameRuleViolationResult(result.getMessage())
            }
        }
       return SuccessfulResult(this)
    }

    private fun validatePieceRules(move: Move): MoveResult {
        val piece = board.getPiece(move.getFrom()) ?: throw NoSuchElementException("No piece found")

        return when (val result = piece.validateMove(move, board)){
            ValidResult -> executeActions(listOf(ApplyMove(RelativePosition(), RelativePosition(move.getTo().row - move.getFrom().row, move.getTo().column - move.getFrom().column))), move.getFrom())
            is ValidWithExecutionResult -> executeActions(result.getActions(), move.getFrom())
            is InvalidResult -> PieceRuleViolationResult(result.getMessage())
        }
    }

    private fun executeActions(actions: List<Action>, from: Coordinate): MoveResult {
        var modifiedBoard: Board = board
        for (action in actions){
            modifiedBoard = when (action){
                is ApplyMove  ->
                    applyMove(action.getFrom(from), action.getTo(from), modifiedBoard)
                is RemovePiece  ->
                    removePiece(action.getFrom(from), modifiedBoard)
                is ConvertPiece ->
                    convertPiece(action.getFrom(from), modifiedBoard, action.getNewPiece())
            }
        }
        return SuccessfulResult(
            Game(
                modifiedBoard,
                getEnemyTeam(),
                rules,
                checkMateRule
            )
        )
    }

    private fun applyMove(from: Coordinate, to: Coordinate, modifiedBoard: Board): Board {
        return modifiedBoard.movePiece(from, to)
    }

    private fun removePiece(coordinate: Coordinate, modifiedBoard: Board): Board {
        return modifiedBoard.removePiece(coordinate)
    }

    private fun convertPiece(coordinate: Coordinate, modifiedBoard: Board,newPiece: Piece): Board {
        val oldPiece: Piece = modifiedBoard.getPiece(coordinate) ?: throw NoSuchElementException("No piece found")
        return modifiedBoard.addPiece(coordinate, oldPiece.copy(
            pieceType = newPiece.pieceType,
            team = newPiece.team,
            pieceRule = newPiece.pieceRule,
            id = oldPiece.getId()
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
        return checkMateRule.isCheckmate(board, getEnemyTeam(), rules)
    }
}
