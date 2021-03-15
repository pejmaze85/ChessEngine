package com.chessAI.player;

import com.chessAI.Alliance;
import com.chessAI.board.Board;
import com.chessAI.board.Move;
import com.chessAI.piece.Piece;

import java.util.Collection;

public class BlackPlayer extends Player {
    public BlackPlayer(Board board, Collection<Move> whiteStandardLegalMoves,
                                    Collection<Move> blackStandardLegalMoves) {

        super(board, blackStandardLegalMoves, whiteStandardLegalMoves);
    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getBlackPieces();
    }

    @Override
    public Alliance getAlliance() {
        return Alliance.BLACK;
    }

    @Override
    public Player getOpponent() {
        return this.board.whitePlayer();
    }
}
