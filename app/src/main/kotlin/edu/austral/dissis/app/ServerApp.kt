package edu.austral.dissis.app

import edu.austral.dissis.chess.factory.NormalChessGame
import edu.austral.dissis.server.GameServer

fun main() {
    GameServer(NormalChessGame())
}