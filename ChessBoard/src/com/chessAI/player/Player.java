package com.chessAI.player;

import com.chessAI.Alliance;
import com.chessAI.board.Board;
import com.chessAI.board.Move;
import com.chessAI.piece.King;
import com.chessAI.piece.Piece;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Player {
        protected final Board board;
        protected final King playerKing;
        protected final Collection<Move> legalMoves;
        private final boolean isInCheck;


        Player(final Board board, final Collection<Move> legalMoves, final Collection<Move> opponentMoves){
            this.board = board;
            this.playerKing = establishKing();
            this.legalMoves = legalMoves;
            this.isInCheck = !Player.calculateAttacksOnTile(this.playerKing.getPiecePosition(), opponentMoves).isEmpty();
        }

    private static Collection<Move> calculateAttacksOnTile(int piecePosition, Collection<Move> moves) {
            final List<Move> attackMoves = new ArrayList<>();
            for(final Move move : moves){
                if(piecePosition == move.getDestinationCoordinate()){
                    attackMoves.add(move);
                }
            }
            return ImmutableList.copyOf(attackMoves);
    }

    private King establishKing(){
        for(final Piece piece : getActivePieces()){
            if( piece.getPieceType().isKing()){
                return (King) piece;
            }
        }
        throw new RuntimeException("NO VALID KING FOUND");
    }

    public boolean isMoveLega(final Move move){
            return this.legalMoves.contains(move);
    }

    public boolean isInCheck(){
            return this.isInCheck;
    }

    public boolean isInCheckMate(){
            return false;
    }

    public boolean isInStaleMate(){
            return false;
    }

    public boolean isCastled(){
            return false;
    }

    public MoveTransition makeMove(final Move move){
            return null;
    }

    public abstract Collection<Piece> getActivePieces();
    public abstract Alliance getAlliance();
    public abstract Player getOpponent();

}

