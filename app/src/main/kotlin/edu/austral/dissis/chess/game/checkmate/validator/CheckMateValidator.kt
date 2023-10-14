package edu.austral.dissis.chess.game.checkmate.validator

import edu.austral.dissis.chess.game.Move

interface CheckMateValidator {
    fun isCheckmate(move: Move): Boolean
}
