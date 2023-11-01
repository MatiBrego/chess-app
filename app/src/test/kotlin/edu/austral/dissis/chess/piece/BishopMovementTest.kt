package edu.austral.dissis.chess.piece

import edu.austral.dissis.common.board.Coordinate
import edu.austral.dissis.chess.factory.bishopInCenter
import edu.austral.dissis.chess.factory.bishopInCenterBlocked
import edu.austral.dissis.common.result.move.UnsuccessfulResult
import edu.austral.dissis.common.result.move.SuccessfulResult
import edu.austral.dissis.common.game.TestGameGenerator
import kotlin.test.Test
import kotlin.test.assertTrue

class BishopMovementTest {

    private val gameGenerator = TestGameGenerator()

    @Test
    fun `test valid bishop moves for bishop in center without obstacle`() {
        val game = gameGenerator.generate(bishopInCenter(), emptyList())
        val bishopPosition = Coordinate(3, 3)

        // Valid moves along the primary diagonal (from top-left to bottom-right)

        // Up and left
        assertTrue(game.move(bishopPosition, Coordinate(2,2)) is SuccessfulResult)
        assertTrue(game.move(bishopPosition, Coordinate(1,1)) is SuccessfulResult)

        // Down and right
        assertTrue(game.move(bishopPosition, Coordinate(4,4)) is SuccessfulResult)
        assertTrue(game.move(bishopPosition, Coordinate(5,5)) is SuccessfulResult)
        assertTrue(game.move(bishopPosition, Coordinate(6,6)) is SuccessfulResult)
        assertTrue(game.move(bishopPosition, Coordinate(7,7)) is SuccessfulResult)

        // Valid moves along the secondary diagonal (from bottom-left to top-right)

        // Up and right
        assertTrue(game.move(bishopPosition, Coordinate(4,2)) is SuccessfulResult)
        assertTrue(game.move(bishopPosition, Coordinate(5,1)) is SuccessfulResult)

        // Down and left
        assertTrue(game.move(bishopPosition, Coordinate(2,4)) is SuccessfulResult)
        assertTrue(game.move(bishopPosition, Coordinate(1,5)) is SuccessfulResult)

    }

    @Test
    fun `test invalid bishop moves for bishop in center without obstacle`() {
        val game = gameGenerator.generate(bishopInCenter(), emptyList())
        val bishopPosition = Coordinate(3, 3)

        // Invalid moves for row 0
        for (col in listOf(2, 3, 4, 5, 7)) {
            assertTrue(game.move(bishopPosition, Coordinate(0, col)) is UnsuccessfulResult)
        }

        // Invalid moves for row 1
        for (col in listOf(2, 3, 4, 6, 7)) {
            assertTrue(game.move(bishopPosition, Coordinate(1, col)) is UnsuccessfulResult)
        }

        // Invalid moves for row 2
        for (col in listOf(1, 3, 6, 7)) {
            assertTrue(game.move(bishopPosition, Coordinate(2, col)) is UnsuccessfulResult)
        }

        // Invalid moves for row 3 (bishop's own row)
        for (col in listOf(1, 2, 4, 5, 6, 7)) {
            assertTrue(game.move(bishopPosition, Coordinate(3, col)) is UnsuccessfulResult)
        }

        // Invalid moves for row 4
        for (col in listOf(0, 1, 3, 6, 7)) {  // Exclude Coordinate(4, 4)
            assertTrue(game.move(bishopPosition, Coordinate(4, col)) is UnsuccessfulResult)
        }

        // Invalid moves for rows 5
        for (col in listOf(2, 3, 4, 6, 7)) {
            assertTrue(game.move(bishopPosition, Coordinate(5, col)) is UnsuccessfulResult)
        }

        // Invalid moves for row 6
        for (col in listOf(2, 3, 4, 5, 7)) {
            assertTrue(game.move(bishopPosition, Coordinate(6, col)) is UnsuccessfulResult)
        }

        // Invalid moves for row 7
        for (col in listOf(1, 2, 3, 4, 5, 6)) {
            assertTrue(game.move(bishopPosition, Coordinate(7, col)) is UnsuccessfulResult)
        }


    }

    @Test
    fun `test invalid bishop moves for bishop in center with obstacles in all directions`(){
        val game = gameGenerator.generate(bishopInCenterBlocked())
        val bishopPosition = Coordinate(3, 3)

        // Invalid moves along the primary diagonal (from top-left to bottom-right)

        // Up and left
        assertTrue(game.move(bishopPosition, Coordinate(1,1)) is UnsuccessfulResult)

        // Down and right
        assertTrue(game.move(bishopPosition, Coordinate(5,5)) is UnsuccessfulResult)
        assertTrue(game.move(bishopPosition, Coordinate(6,6)) is UnsuccessfulResult)
        assertTrue(game.move(bishopPosition, Coordinate(7,7)) is UnsuccessfulResult)

        // Invalid moves along the secondary diagonal (from bottom-left to top-right)

        // Up and right
        assertTrue(game.move(bishopPosition, Coordinate(5,1)) is UnsuccessfulResult)

        // Down and left
        assertTrue(game.move(bishopPosition, Coordinate(1,5)) is UnsuccessfulResult)
    }
}
