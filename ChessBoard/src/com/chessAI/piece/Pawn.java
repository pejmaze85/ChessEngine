package com.chessAI.piece;

import com.chessAI.Alliance;
import com.chessAI.board.Board;
import com.chessAI.board.BoardUtils;
import com.chessAI.board.Move;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Pawn extends Piece {

    private final static int[] CANDIDATE_MOVE_COORDINATE = {8, 16, 7, 9};

    public Pawn(Alliance pieceAlliance, int piecePosition) {
        super(PieceType.PAWN, piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {

        final List<Move> legalMoves = new ArrayList<>();
        for (final int currentCanidateOffset : CANDIDATE_MOVE_COORDINATE) {

            int candidateDestination = this.piecePosition + (currentCanidateOffset * this.getPieceAlliance().getDirection());

            if (!BoardUtils.isValidTileCoordinate(candidateDestination)) {
                continue;
            }

            if (currentCanidateOffset == 8 && !board.getTile(candidateDestination).isTileOccupied()) {
                // ADD SHIT HERE
                legalMoves.add(new Move.MajorMove(board, this, candidateDestination));

            } else if (currentCanidateOffset == 16 && this.isFirstMove() &&
                    (BoardUtils.SECOND_ROW[this.piecePosition] && pieceAlliance.isBlack()) ||
                    (BoardUtils.SEVENTH_ROW[this.piecePosition] && pieceAlliance.isWhite())) {

                final int behindCanDest = this.piecePosition + (this.pieceAlliance.getDirection() * 8);
                if (!board.getTile(behindCanDest).isTileOccupied() &&
                        board.getTile(candidateDestination).isTileOccupied()) {
                    legalMoves.add(new Move.MajorMove(board, this, candidateDestination));
                }

            } else if (currentCanidateOffset == 7 &&
                    !((BoardUtils.EIGHTH_COLUMN[this.piecePosition] && this.pieceAlliance.isWhite() ||
                            BoardUtils.FIRST_COLUMN[this.piecePosition] && this.pieceAlliance.isBlack()))) {
                if (board.getTile(candidateDestination).isTileOccupied()) {
                    final Piece pieceOnCandidate = board.getTile(candidateDestination).getPiece();
                    if (this.pieceAlliance != pieceOnCandidate.getPieceAlliance()) {
                        //ADD ATTACK MOVE
                        legalMoves.add(new Move.MajorMove(board, this, candidateDestination));
                    }
                }

            } else if (currentCanidateOffset == 9 &&
                    !((BoardUtils.FIRST_COLUMN[this.piecePosition] && this.pieceAlliance.isWhite() ||
                            BoardUtils.EIGHTH_COLUMN[this.piecePosition] && this.pieceAlliance.isBlack()))) {
                if (board.getTile(candidateDestination).isTileOccupied()) {
                    final Piece pieceOnCandidate = board.getTile(candidateDestination).getPiece();
                    if (this.pieceAlliance != pieceOnCandidate.getPieceAlliance()) {
                        //ADD ATTACK MOVE
                        legalMoves.add(new Move.MajorMove(board, this, candidateDestination));
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }

    @Override
    public String toString() {
        return PieceType.PAWN.toString();
    }
}