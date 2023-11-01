package edu.austral.dissis.common.board

import edu.austral.dissis.chess.factory.createPawn
import edu.austral.dissis.common.piece.Team
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class TestBoard {

    @Test
    fun `test invalid board size`() {
        val boardSize = BoardSize(8, 8)
        assertThrows<IllegalArgumentException> {Board(mapOf(Coordinate(9, 9) to createPawn(Team.WHITE)), boardSize)}
    }
}