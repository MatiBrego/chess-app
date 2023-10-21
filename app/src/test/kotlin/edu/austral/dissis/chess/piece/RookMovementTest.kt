package edu.austral.dissis.chess.piece

import edu.austral.dissis.common.board.Coordinate
import edu.austral.dissis.chess.factory.rookInCenter
import edu.austral.dissis.chess.factory.rookInCenterBlocked
import edu.austral.dissis.common.result.move.UnsuccessfulResult
import edu.austral.dissis.common.result.move.SuccessfulResult
import edu.austral.dissis.chess.util.game.TestGameGenerator
import kotlin.test.Test
import kotlin.test.assertTrue

class RookMovementTest {
    private val gameGenerator = TestGameGenerator()
    @Test
    fun `test valid rook moves for rook in center without obstacle`() {
        val game = gameGenerator.generate(rookInCenter())
        val rookPosition = Coordinate(3, 3)

        // Valid moves along the column (vertical movement)
        assertTrue(game.move(rookPosition, Coordinate(0,3)) is SuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(1,3)) is SuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(2,3)) is SuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(4,3)) is SuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(5,3)) is SuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(6,3)) is SuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(7,3)) is SuccessfulResult)

        // Valid moves along the row (horizontal movement)
        assertTrue(game.move(rookPosition, Coordinate(3,0)) is SuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(3,1)) is SuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(3,2)) is SuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(3,4)) is SuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(3,5)) is SuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(3,6)) is SuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(3,7)) is SuccessfulResult)

    }

    @Test
    fun `test invalid rook moves for rook in center without obstacle`() {
        val game = gameGenerator.generate(rookInCenter())
        val rookPosition = Coordinate(3, 3)

        // Invalid moves in the first row (You've covered this row, but just for completeness)
        assertTrue(game.move(rookPosition, Coordinate(0, 1)) is UnsuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(1, 1)) is UnsuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(2, 1)) is UnsuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(4, 1)) is UnsuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(5, 1)) is UnsuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(6, 1)) is UnsuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(7, 1)) is UnsuccessfulResult)

        // Invalid moves in the second row
        assertTrue(game.move(rookPosition, Coordinate(0, 2)) is UnsuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(1, 2)) is UnsuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(2, 2)) is UnsuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(4, 2)) is UnsuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(5, 2)) is UnsuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(6, 2)) is UnsuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(7, 2)) is UnsuccessfulResult)


        // Invalid moves in the fourth row
        assertTrue(game.move(rookPosition, Coordinate(0, 4)) is UnsuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(1, 4)) is UnsuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(2, 4)) is UnsuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(4, 4)) is UnsuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(5, 4)) is UnsuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(6, 4)) is UnsuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(7, 4)) is UnsuccessfulResult)

        // Invalid moves in the fifth row
        assertTrue(game.move(rookPosition, Coordinate(0, 5)) is UnsuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(1, 5)) is UnsuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(2, 5)) is UnsuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(4, 5)) is UnsuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(5, 5)) is UnsuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(6, 5)) is UnsuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(7, 5)) is UnsuccessfulResult)

        // Invalid moves in the sixth row
        assertTrue(game.move(rookPosition, Coordinate(0, 6)) is UnsuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(1, 6)) is UnsuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(2, 6)) is UnsuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(4, 6)) is UnsuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(5, 6)) is UnsuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(6, 6)) is UnsuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(7, 6)) is UnsuccessfulResult)

        // Invalid moves in the seventh row
        assertTrue(game.move(rookPosition, Coordinate(0, 7)) is UnsuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(1, 7)) is UnsuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(2, 7)) is UnsuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(4, 7)) is UnsuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(5, 7)) is UnsuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(6, 7)) is UnsuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(7, 7)) is UnsuccessfulResult)
    }

    @Test
    fun `test invalid rook moves for rook in center with obstacles in all directions`(){
        val game = gameGenerator.generate(rookInCenterBlocked())
        val rookPosition = Coordinate(3, 3)

        // Invalid moves along the column (vertical movement)
        assertTrue(game.move(rookPosition, Coordinate(0,3)) is UnsuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(1,3)) is UnsuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(5,3)) is UnsuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(6,3)) is UnsuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(7,3)) is UnsuccessfulResult)

        // Invalid moves along the row (horizontal movement)
        assertTrue(game.move(rookPosition, Coordinate(3,0)) is UnsuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(3,1)) is UnsuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(3,5)) is UnsuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(3,6)) is UnsuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(3,7)) is UnsuccessfulResult)

        // Valid moves (Eating)
        assertTrue(game.move(rookPosition, Coordinate(2,3)) is SuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(4,3)) is SuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(3,2)) is SuccessfulResult)
        assertTrue(game.move(rookPosition, Coordinate(3,4)) is SuccessfulResult)
    }

}
