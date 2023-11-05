package edu.austral.dissis.server.payload

import edu.austral.dissis.common.board.Coordinate

data class MovePayload(
    val from: Coordinate,
    val to: Coordinate
)