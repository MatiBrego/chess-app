package edu.austral.dissis.common.board

import edu.austral.dissis.common.piece.Piece

class Board(
    private val positions: Map<Coordinate, Piece>,
    private val boardSize: BoardSize = BoardSize(8, 8)
){
    fun movePiece(from: Coordinate, to: Coordinate): Board {
        val piece: Piece = positions[from]?: throw NoSuchElementException("No piece at $from")

        return Board(positions + Pair(to, piece.copy(
            moveCount = piece.getMoveCount() + 1,
            id = piece.getId()
        )
        ) - from)
    }

    fun removePiece(from: Coordinate): Board {
        return Board(positions - from)
    }

    fun getPiece(coordinate: Coordinate): Piece? {
        return positions[coordinate]
    }

    fun addPiece(coordinate: Coordinate, piece: Piece): Board {
        return Board(positions + Pair(coordinate, piece))
    }

    fun hasCoordinate(coordinate: Coordinate): Boolean {
        return positions.containsKey(coordinate)
    }

    fun getOccupiedPositions(): List<Coordinate> {
        return positions.keys.toList()
    }

    fun getColumnQuantity(): Int {
        return boardSize.getColumns()
    }

    fun getRowQuantity(): Int {
        return boardSize.getRows()
    }

    fun isInBounds(coordinate: Coordinate): Boolean {
        return coordinate.row in 1 until getRowQuantity() && coordinate.column in 1 until getColumnQuantity()
    }
}
