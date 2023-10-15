package edu.austral.dissis.chess.game

import edu.austral.dissis.chess.board.Board
import edu.austral.dissis.chess.board.Coordinate
import edu.austral.dissis.chess.game.checkmate.CheckMateValidator
import edu.austral.dissis.chess.piece.Piece
import edu.austral.dissis.chess.piece.Team
import edu.austral.dissis.chess.result.action.Action
import edu.austral.dissis.chess.result.action.ApplyMove
import edu.austral.dissis.chess.result.action.ConvertPiece
import edu.austral.dissis.chess.result.action.RemovePiece
import edu.austral.dissis.chess.result.move.*
import edu.austral.dissis.chess.result.rule.InvalidResult
import edu.austral.dissis.chess.result.rule.ValidResult
import edu.austral.dissis.chess.result.rule.ValidWithExecutionResult
import edu.austral.dissis.chess.rule.Rule

class Game(
    private var board: Board,
    private val turn: Team = Team.WHITE,
    private val histories: Map<Piece, List<Move>>,
    private val rules: List<Rule>,
    private val checkMateRule: CheckMateValidator,
) {

    fun move(from: Coordinate, to: Coordinate): MoveResult {
        val piece = this.board.getPiece(from) ?: return NoPieceInCoordinateResult

        val move = Move(this.board, from, to, piece, turn,histories[piece] ?: listOf())

        val gameValidationResult = validateGameRules(move)
        if (gameValidationResult !is SuccessfulResult) return gameValidationResult

        val pieceValidationResult = validatePieceRules(move, piece)
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

    private fun validatePieceRules(move: Move, piece: Piece): MoveResult {
        return when (val result = piece.validateMove(move)){
            ValidResult -> executeActions(listOf(ApplyMove(move.getFrom(), move.getTo())))
            is ValidWithExecutionResult -> executeActions(result.getActions())
            is InvalidResult -> PieceRuleViolationResult(result.getMessage())
        }
    }

    private fun executeActions(actions: List<Action>): MoveResult {
        var modifiedBoard: Board = board
        var modifiedHistories = histories
        for (action in actions){
            when (action){
               is ApplyMove  -> {
                   modifiedBoard = applyMove(action.getFrom(), action.getTo())
                   val piece = modifiedBoard.getPiece(action.getTo()) ?: throw NoSuchElementException("No piece found")
                   modifiedHistories = modifiedHistories + Pair(piece, (histories[piece]?: listOf()) + Move(
                       modifiedBoard,
                       action.getFrom(),
                       action.getTo(),
                       piece,
                       turn,
                       histories[piece] ?: listOf()
                   ))
               }
               is RemovePiece  ->
                   modifiedBoard = removePiece(action.getFrom())

               is ConvertPiece ->
                   modifiedBoard = convertPiece(action.getFrom(), action.getNewPiece())
            }
        }
        return SuccessfulResult(
            Game(
                modifiedBoard,
                getEnemyTeam(),
                modifiedHistories,
                rules,
                checkMateRule
            )
        )
    }

    private fun applyMove(from: Coordinate, to: Coordinate): Board {
        return board.movePiece(from, to)
    }

    private fun removePiece(coordinate: Coordinate): Board {
        return board.removePiece(coordinate)
    }

    private fun convertPiece(coordinate: Coordinate, newPiece: Piece): Board {
        val oldPiece: Piece = board.getPiece(coordinate) ?: throw NoSuchElementException("No piece found")
        return board.addPiece(coordinate, Piece(newPiece.name, newPiece.pieceRule, newPiece.team, oldPiece.getId()))
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
