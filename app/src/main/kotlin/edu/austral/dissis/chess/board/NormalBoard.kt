package edu.austral.dissis.chess.board

import edu.austral.dissis.chess.piece.Piece

class NormalBoard(
    private val positions: Map<Coordinate, Piece>,
    private val boardSize: BoardSize = BoardSize(8, 8)
): Board {

    override fun movePiece(from: Coordinate, to: Coordinate): Board {
        val piece: Piece = positions[from]?: throw NoSuchElementException("No piece at $from")

        return NormalBoard(positions + Pair(to, piece) - from)
    }

    override fun removePiece(from: Coordinate): Board {
        return NormalBoard(positions - from)
    }

    override fun getPiece(coordinate: Coordinate): Piece? {
        return positions[coordinate]
    }

    override fun addPiece(coordinate: Coordinate, piece: Piece): NormalBoard {
        return NormalBoard(positions + Pair(coordinate, piece))
    }

    override fun hasCoordinate(coordinate: Coordinate): Boolean {
        return positions.containsKey(coordinate)
    }

    override fun print() {
        val board = Array(8) { Array(8) { "---------" } }

        positions.forEach { (coordinate, piece) ->
            board[coordinate.row][coordinate.column] = piece.toString()
        }

        board.forEach { row ->
            row.forEach { print("$it ") }
            println()
        }
    }

    override fun getOccupiedPositions(): List<Coordinate> {
        return positions.keys.toList()
    }

    override fun getColumnQuantity(): Int {
        return boardSize.getColumns()
    }

    override fun getRowQuantity(): Int {
        return boardSize.getRows()
    }
}
