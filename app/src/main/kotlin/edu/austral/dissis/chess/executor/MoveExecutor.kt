package edu.austral.dissis.chess.executor

import edu.austral.dissis.chess.board.Board
import edu.austral.dissis.chess.game.Move
import edu.austral.dissis.chess.result.action.Action

interface MoveExecutor {
    fun executeSingleMove(move: Move, board: Board): Board
    fun executeActions(actions: List<Action>, move: Move, board: Board): Board
}
