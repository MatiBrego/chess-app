package edu.austral.dissis.common.result.move

import edu.austral.dissis.common.piece.Team

data class EndOfGameResult(val winner: Team) : MoveResult
