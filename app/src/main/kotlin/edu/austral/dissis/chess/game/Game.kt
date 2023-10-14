package edu.austral.dissis.chess.game

import edu.austral.dissis.chess.board.Board
import edu.austral.dissis.chess.board.Coordinate
import edu.austral.dissis.chess.piece.Piece
import edu.austral.dissis.chess.piece.Team
import edu.austral.dissis.chess.result.move.*
import edu.austral.dissis.chess.result.rule.CheckMateResult
import edu.austral.dissis.chess.result.rule.InvalidResult
import edu.austral.dissis.chess.result.rule.ValidResult
import edu.austral.dissis.chess.result.rule.ValidWithExecutionResult
import edu.austral.dissis.chess.rule.Rule

class Game(
    private var board: Board,
    private val turn: Team = Team.WHITE,
    private val histories: Map<Piece, List<Move>>,
    private val rules: List<Rule>,
    private val checkMateRule: Rule,
) {

    fun move(from: Coordinate, to: Coordinate): MoveResult {
        val piece = this.board.getPiece(from) ?: return NoPieceInCoordinateResult

        val move = Move(this.board, from, to, piece, turn,histories[piece] ?: listOf())

        for (rule in rules){
            return when (val result = rule.validateMove(move, board)){
                ValidResult -> continue
                is ValidWithExecutionResult -> continue
                is CheckMateResult -> EndOfGameResult(turn)
                is InvalidResult -> GameRuleViolationResult(result.getMessage())

            }
        }

        val nextTurn: Team = when(turn){
            Team.WHITE -> Team.BLACK
            Team.BLACK -> Team.WHITE
        }

        when(val result = piece.validateMove(move)) {
            is InvalidResult -> {
                return PieceRuleViolationResult(result.getMessage())
            }
            ValidResult -> {
                // continue
            }
            CheckMateResult -> {
                return EndOfGameResult(turn)
            }
            is ValidWithExecutionResult -> {
                // continue
            }
        }
        val newHistory = histories[piece]?: listOf()
        return SuccessfulResult(
            Game(
                board.movePiece(from, to),
                nextTurn,
                histories + Pair(piece, newHistory + move),
                rules,
                checkMateRule
            )
        )
    }

    fun getBoard(): Board {
        return this.board
    }

    fun getTurn(): Team {
        return this.turn
    }
}
