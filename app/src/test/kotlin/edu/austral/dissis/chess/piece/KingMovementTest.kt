package edu.austral.dissis.chess.piece

import edu.austral.dissis.chess.board.Coordinate
import edu.austral.dissis.chess.factory.kingInCenter
import edu.austral.dissis.chess.result.move.PieceRuleViolationResult
import edu.austral.dissis.chess.result.move.SuccessfulResult
import edu.austral.dissis.chess.util.game.TestGameGenerator
import kotlin.test.Test
import kotlin.test.assertTrue

class KingMovementTest {
    private val gameGenerator = TestGameGenerator()

    @Test
    fun `test valid king moves for king in center without obstacle`(){
        val game = gameGenerator.generate(kingInCenter(), emptyList())
        val kingPosition = Coordinate(3, 3)

        assertTrue(game.move(kingPosition, Coordinate(2, 3)) is SuccessfulResult)
        assertTrue(game.move(kingPosition, Coordinate(4, 3)) is SuccessfulResult)
        assertTrue(game.move(kingPosition, Coordinate(3, 2)) is SuccessfulResult)
        assertTrue(game.move(kingPosition, Coordinate(3, 4)) is SuccessfulResult)
        assertTrue(game.move(kingPosition, Coordinate(2, 2)) is SuccessfulResult)
        assertTrue(game.move(kingPosition, Coordinate(4, 4)) is SuccessfulResult)
        assertTrue(game.move(kingPosition, Coordinate(2, 4)) is SuccessfulResult)
        assertTrue(game.move(kingPosition, Coordinate(4, 2)) is SuccessfulResult)
    }

    @Test
    fun `test invalid king moves for king in center without obstacle`(){
        val game = gameGenerator.generate(kingInCenter(), emptyList())
        val kingPosition = Coordinate(3, 3)

        assertTrue(game.move(kingPosition, Coordinate(3, 0)) is PieceRuleViolationResult) // Column 1 triggers castling
        assertTrue(game.move(kingPosition, Coordinate(3, 6)) is PieceRuleViolationResult) // Column 5 triggers castling
        assertTrue(game.move(kingPosition, Coordinate(5, 3)) is PieceRuleViolationResult)
        assertTrue(game.move(kingPosition, Coordinate(1, 3)) is PieceRuleViolationResult)

        assertTrue(game.move(kingPosition, Coordinate(1, 1)) is PieceRuleViolationResult)
    }
}
