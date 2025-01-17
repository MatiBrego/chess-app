package edu.austral.dissis.checkers.piece

import edu.austral.dissis.checkers.factory.manInCenter
import edu.austral.dissis.checkers.factory.manWith3Captures
import edu.austral.dissis.checkers.factory.manWithOneCapture
import edu.austral.dissis.checkers.turn.CheckersTurn
import edu.austral.dissis.common.board.Coordinate
import edu.austral.dissis.common.game.TestGameGenerator
import edu.austral.dissis.common.piece.Team
import edu.austral.dissis.common.result.move.SuccessfulResult
import edu.austral.dissis.common.result.move.UnsuccessfulResult
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class ManMovementTest {
    private val gameGenerator = TestGameGenerator()

    @Test
    fun `test man normal valid moves`(){
        val game = gameGenerator.generate(manInCenter())
        val manPosition = Coordinate(3, 3)

        assertTrue(game.move(manPosition, Coordinate(4, 4)) is SuccessfulResult)
        assertTrue(game.move(manPosition, Coordinate(4, 2)) is SuccessfulResult)
    }

    @Test
    fun `test man normal invalid`(){
        val game = gameGenerator.generate(manInCenter())
        val manPosition = Coordinate(3, 3)

        // Forward
        assertTrue(game.move(manPosition, Coordinate(5, 5)) is UnsuccessfulResult)
        assertTrue(game.move(manPosition, Coordinate(5, 1)) is UnsuccessfulResult)
        assertTrue(game.move(manPosition, Coordinate(4, 3)) is UnsuccessfulResult)

        // Backward
        assertTrue(game.move(manPosition, Coordinate(2, 4)) is UnsuccessfulResult)
        assertTrue(game.move(manPosition, Coordinate(2, 2)) is UnsuccessfulResult)
        assertTrue(game.move(manPosition, Coordinate(2, 3)) is UnsuccessfulResult)
    }

    @Test
    fun `test man capture`(){
        val singleWhiteCapture = gameGenerator.generate(manWithOneCapture())
        val whiteManPosition = Coordinate(3, 3)
        assertTrue(singleWhiteCapture.move(whiteManPosition, Coordinate(5, 5)) is SuccessfulResult)

        val singleBlackCapture = gameGenerator.generate(manWithOneCapture(), turnManager = CheckersTurn(Team.BLACK))
        val blackManPosition = Coordinate(4, 4)
        assertTrue (singleBlackCapture.move(blackManPosition, Coordinate(2, 2)) is SuccessfulResult)


        val multiCapture = gameGenerator.generate(manWith3Captures(), turnManager = CheckersTurn(Team.WHITE))
        val multiCapturePosition = Coordinate(1, 1)
        val capture1 = multiCapture.move(multiCapturePosition, Coordinate(3, 3))
        assertTrue(capture1 is SuccessfulResult)

        val capture2 = capture1.game.move(Coordinate(3, 3), Coordinate(5, 5))
        assertTrue(capture2 is SuccessfulResult)

        val capture3 = capture2.game.move(Coordinate(5, 5), Coordinate(7, 3))
        assertTrue(capture3 is SuccessfulResult)
    }
}