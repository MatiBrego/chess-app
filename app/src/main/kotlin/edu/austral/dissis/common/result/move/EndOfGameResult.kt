package edu.austral.dissis.common.result.move

import edu.austral.dissis.common.board.Board
import edu.austral.dissis.common.piece.Team

data class EndOfGameResult(val winner: Team, val finalBoard: Board) : MoveResult
