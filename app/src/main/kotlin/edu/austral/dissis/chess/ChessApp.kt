/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package edu.austral.dissis.chess

import edu.austral.dissis.chess.factory.createAlternativeDimensionsBoard
import edu.austral.dissis.common.adapter.Adapter
import edu.austral.dissis.chess.factory.createAlternativeGame
import edu.austral.dissis.chess.factory.createGameWithSpecialSizedBoard
import edu.austral.dissis.chess.factory.createNormalChessGame
import edu.austral.dissis.chess.gui.CachedImageResolver
import edu.austral.dissis.chess.gui.DefaultImageResolver
import edu.austral.dissis.chess.gui.GameView
import javafx.application.Application
import javafx.application.Application.launch
import javafx.scene.Scene
import javafx.stage.Stage


fun main() {
    launch(ChessGameApplication::class.java)
}

class ChessGameApplication : Application() {
    private val gameEngine = Adapter(createGameWithSpecialSizedBoard())
    private val imageResolver = CachedImageResolver(DefaultImageResolver())

    companion object {
        const val GameTitle = "Chess"
    }

    override fun start(primaryStage: Stage) {
        primaryStage.title = GameTitle

        val root = GameView(gameEngine, imageResolver)
        primaryStage.scene = Scene(root)

        primaryStage.show()
    }
}
