package edu.austral.dissis.chess.piece

import edu.austral.dissis.common.board.Coordinate
import edu.austral.dissis.chess.factory.queenInCenter
import org.junit.jupiter.api.Test
import edu.austral.dissis.chess.util.game.TestGameGenerator

class QueenMovementTest {
    private val generator = TestGameGenerator()

    @Test
    fun `test valid queen moves for queen in center without obstacle`(){
        val game = generator.generate(queenInCenter(), listOf())
        val kingPosition = Coordinate(3, 3)
    }
}
