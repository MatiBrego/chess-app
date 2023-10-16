package edu.austral.dissis.chess.piece

import edu.austral.dissis.chess.board.Coordinate
import edu.austral.dissis.chess.factory.pawnInCenter
import edu.austral.dissis.chess.factory.pawnInCenterBlocked
import edu.austral.dissis.chess.game.Game
import edu.austral.dissis.chess.result.move.MoveResult
import edu.austral.dissis.chess.result.move.PieceRuleViolationResult
import edu.austral.dissis.chess.result.move.SuccessfulResult
import edu.austral.dissis.chess.util.game.TestGameGenerator
import kotlin.test.Test
import kotlin.test.assertTrue

class PawnMovementTest {
    private val gameGenerator = TestGameGenerator()

    @Test
    fun `test valid pawn moves for pawn in center without obstacle`() {
        val game = gameGenerator.generate(pawnInCenter())
        val pawnPosition = Coordinate(3, 3)

        // Valid moves for white pawn
        assertTrue(game.move(pawnPosition, Coordinate(4, 3)) is SuccessfulResult)
    }

    @Test
    fun `test invalid pawn moves for pawn in center without obstacle`(){
        val game = gameGenerator.generate(pawnInCenter())
        val pawnPosition = Coordinate(3, 3)

        // invalid moves for white pawn (Some)
        assertTrue(game.move(pawnPosition, Coordinate(4, 4)) is PieceRuleViolationResult)
        assertTrue(game.move(pawnPosition, Coordinate(3, 4)) is PieceRuleViolationResult)
        assertTrue(game.move(pawnPosition, Coordinate(2, 4)) is PieceRuleViolationResult)
        assertTrue(game.move(pawnPosition, Coordinate(2, 3)) is PieceRuleViolationResult)
        assertTrue(game.move(pawnPosition, Coordinate(2, 2)) is PieceRuleViolationResult)
        assertTrue(game.move(pawnPosition, Coordinate(3, 2)) is PieceRuleViolationResult)
        assertTrue(game.move(pawnPosition, Coordinate(4, 2)) is PieceRuleViolationResult)
    }

    @Test
    fun `test first move pawn rule`(){
        val game = gameGenerator.generate(pawnInCenter())
        val startingPawnPosition = Coordinate(3, 3)
        val afterMovePawnPosition = Coordinate(4, 3)

        val resultAfterPawnMove = game.move(startingPawnPosition, afterMovePawnPosition)
        val gameAfterPawnMove: Game;
        when (resultAfterPawnMove){
            is SuccessfulResult -> gameAfterPawnMove = resultAfterPawnMove.game
            else -> throw RuntimeException("Valid move was unsuccessful")
        }

        assertTrue(game.move(startingPawnPosition, Coordinate(5, 3)) is SuccessfulResult)
        assertTrue(game.move(startingPawnPosition, Coordinate(6, 3)) is PieceRuleViolationResult)
        assertTrue(gameAfterPawnMove.move(afterMovePawnPosition, Coordinate(6, 3)) is PieceRuleViolationResult)
    }

    @Test
    fun `test pawn diagonal move rule`(){
        val game = gameGenerator.generate(pawnInCenterBlocked())
        val pawnPosition = Coordinate(3, 3)

        assertTrue(game.move(pawnPosition, Coordinate(4, 3)) is PieceRuleViolationResult)
        assertTrue(game.move(pawnPosition, Coordinate(5, 3)) is PieceRuleViolationResult)

        assertTrue(game.move(pawnPosition, Coordinate(4, 4)) is SuccessfulResult)
        assertTrue(game.move(pawnPosition, Coordinate(4, 2)) is SuccessfulResult)
    }
}
