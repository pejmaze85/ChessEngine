package com.chessAI.board;

import java.util.*;

import static java.lang.System.*;

public class BoardUtils {

    public static final boolean[] FIRST_COLUMN = initColumn(0);
    public static final boolean[] SECOND_COLUMN = initColumn(1);
    public static final boolean[] SEVENTH_COLUMN = initColumn(6);
    public static final boolean[] EIGHTH_COLUMN = initColumn(7);

    public static final boolean[] SECOND_ROW = initRow(8);
    public static final boolean[] SEVENTH_ROW = initRow(48);

    public static int NUM_TILES = 64;
    public static int NUM_TILE_PER_ROW = 8;


    private static boolean[] initColumn(int colNum){
        final boolean[] column = new boolean[64];
        do {
            column[colNum] = true;
            colNum += 8;
        }while(colNum < 64);
        return column;
    }

    private static boolean[] initRow(int rowNum) {
        boolean[] row = new boolean[64];
        for (int i = 0; i < row.length; i++) {
            row[i] = false;
        }
        do {
            row[rowNum] = true;
            rowNum++;
        } while (rowNum % 8 != 0);
        return row;
    }

    private BoardUtils(){

        throw new RuntimeException("NOPE");
    }
    public static boolean isValidTileCoordinate(int coord) {
        if (0 <= coord && coord < 64){
            return true;

        }else {
            return false;
        }
    }
}
