package edu.austral.dissis.app

import edu.austral.dissis.chess.factory.createNormalChessGame
import edu.austral.dissis.server.GameServer

fun main() {
    GameServer(createNormalChessGame())
}