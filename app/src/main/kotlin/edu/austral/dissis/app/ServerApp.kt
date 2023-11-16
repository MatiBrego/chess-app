package edu.austral.dissis.app

import edu.austral.dissis.checkers.factory.createCheckersNormalGame
import edu.austral.dissis.server.GameServer

fun main() {
    GameServer(createCheckersNormalGame())
}