package edu.austral.dissis.common.board

import edu.austral.dissis.common.piece.Piece

class Board(
    private val positions: Map<Coordinate, Piece>,
    private val boardSize: BoardSize = BoardSize(8, 8)
) {

    init {
        checkBoardSize(positions, boardSize)
    }

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

    fun getBoardSize(): BoardSize{
        return boardSize
    }

    fun getPositions(): Map<Coordinate, Piece>{
        return positions
    }

    fun getColumnQuantity(): Int {
        return boardSize.getColumns()
    }

    fun getRowQuantity(): Int {
        return boardSize.getRows()
    }

    fun isInBounds(coordinate: Coordinate): Boolean {
        return coordinate.row in 1 .. getRowQuantity() && coordinate.column in 1 .. getColumnQuantity()
    }

    private fun checkBoardSize(positions: Map<Coordinate, Piece>, boardSize: BoardSize) {
        for(position in positions.keys){
            if(!isInBounds(position)){
                throw IllegalArgumentException("Position $position is out of bounds for board size $boardSize")
            }
        }
    }
}
