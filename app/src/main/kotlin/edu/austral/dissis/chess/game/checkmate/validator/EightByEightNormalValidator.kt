package edu.austral.dissis.chess.game.checkmate.validator
//
//import edu.austral.dissis.chess.board.Board
//import edu.austral.dissis.chess.board.Coordinate
//import edu.austral.dissis.chess.game.Move
//import game.rule.validator.CheckValidator
//import edu.austral.dissis.chess.result.validation.ValidResult
//
//class EightByEightNormalValidator(
//    private val checkValidator: CheckValidator
//) : CheckMateValidator {
//    override fun isCheckmate(move: Move): Boolean {
//        // We leave the board with the new move, assuming the move is valid
//        val board = move.getBoard().movePiece(move.getFrom(), move.getTo())
//
//
//        val pieceCoordinates: List<Coordinate> = board.getOccupiedPositions()
//        for (pieceCoordinate in pieceCoordinates) {
//            val validMoves = findAllValidMoves(pieceCoordinate, board)
//            for (validMove in validMoves) {
//                if (!checkValidator.validate(validMove)) {
//                    return false
//                }
//            }
//        }
//
//        return false
//    }
//
//    private fun findAllValidMoves(pieceCoordinate: Coordinate, board: Board): List<Move>{
//        val piece = board.getPiece(pieceCoordinate) ?: throw NoSuchElementException("No piece found")
//        val team = piece.team
//        var validMoves = emptyList<Move>()
//        for(i in 0..7){
//            for(j in 0..7){
//                val to = Coordinate(i,j)
//                val move = Move(board, pieceCoordinate, to, piece, team, emptyList())
//                if(piece.validateMove(move) is ValidResult){
//                    validMoves = validMoves.plus(move)
//                }
//            }
//        }
//        return validMoves
//    }
//}
