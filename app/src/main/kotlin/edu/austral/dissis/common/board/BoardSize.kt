package edu.austral.dissis.common.board

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

    override fun toString(): String {
        return "BoardSize(columns=$columns, rows=$rows)"
    }
}
