package edu.austral.dissis.chess.result.move

import edu.austral.dissis.chess.piece.Team

data class EndOfGameResult(val winner: Team) : MoveResult
