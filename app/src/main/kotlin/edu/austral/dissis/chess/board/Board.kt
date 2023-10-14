package edu.austral.dissis.chess.board

import edu.austral.dissis.chess.piece.Piece

interface Board {
    fun movePiece(from: Coordinate, to: Coordinate): Board
    fun getPiece(coordinate: Coordinate): Piece?
    fun hasCoordinate(coordinate: Coordinate): Boolean
    fun print()
    fun getOccupiedPositions(): List<Coordinate>
    fun getColumnQuantity(): Int
    fun getRowQuantity(): Int
}
