package edu.austral.dissis.common.executor

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.game.Move
import edu.austral.dissis.common.result.action.Action

interface MoveExecutor {
    fun executeSingleMove(move: Move, board: Board): Board
    fun executeActions(actions: List<Action>, move: Move, board: Board): Board
}
