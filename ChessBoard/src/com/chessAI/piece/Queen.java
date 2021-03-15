package com.chessAI.piece;

import com.chessAI.Alliance;
import com.chessAI.board.Board;
import com.chessAI.board.BoardUtils;
import com.chessAI.board.Move;
import com.chessAI.board.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Queen extends Piece{

    private final static int[] CANDIDATE_MOVE_COORDINATES = {-9, -8, -7,-1, 1, 7, 8, 9};

    public Queen(Alliance pieceAlliance, int piecePosition) {
        super(PieceType.QUEEN, piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {

        final List<Move> legalMoves = new ArrayList<>();

        for(final int candidateCoordinateOffset: CANDIDATE_MOVE_COORDINATES){

            int candidateDestinationCoordinate = this.piecePosition;

            while(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
                candidateDestinationCoordinate += candidateCoordinateOffset;

                if (isFirstColumnExclusion(candidateDestinationCoordinate, candidateCoordinateOffset) ||
                        isEighthColumnExclusion(candidateDestinationCoordinate, candidateCoordinateOffset)){
                    break;
                }

                if(BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)){
                    final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);
                    if(!candidateDestinationTile.isTileOccupied()){
                        legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
                    }else{
                        final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                        final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
                        if (this.pieceAlliance != pieceAlliance){
                            legalMoves.add(new Move.AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
                        }
                        break;
                    }
                }
            }
        }

        return ImmutableList.copyOf(legalMoves);
    }

    @Override
    public String toString(){
        return PieceType.QUEEN.toString();
    }

    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset){
        if (0 <= currentPosition && currentPosition < 64) {
            return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -1 || candidateOffset == -9 || candidateOffset == 7);
        }else{
            return false;
        }

    }
    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset) {
        if (0 <= currentPosition && currentPosition < 64) {
            return BoardUtils.EIGHTH_COLUMN[currentPosition] && (candidateOffset == -7 || candidateOffset == 1 || candidateOffset == 9);

        } else {
            return false;
        }
    }
}
