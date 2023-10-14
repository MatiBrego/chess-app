package edu.austral.dissis.chess.piece

import edu.austral.dissis.chess.board.Coordinate
import org.junit.jupiter.api.Test
import edu.austral.dissis.chess.util.game.TestGameGenerator
import util.startingposition.queen.QueenInCenter

class QueenMovementTest {
    val generator = TestGameGenerator()

    @Test
    fun `test valid queen moves for queen in center without obstacle`(){
        val game = generator.generate(QueenInCenter(), listOf())
        val kingPosition = Coordinate(3, 3)
    }
}
