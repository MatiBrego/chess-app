package edu.austral.dissis.chess.board

class BoardSize(
    private val columns: Int,
    private val rows: Int
) {
    fun getColumns(): Int {
        return columns
    }

    fun getRows(): Int {
        return rows
    }
}
