package org.example.javasuitejavafx;

public class TicTacToe {
    private static char[][] board = new char[3][3]; // 3x3 grid
    private static char player;
    public static void togglePlayer() {
        player = (player == 'X' ? 'O' : 'X');

    }
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        } // fill board with empty characters



    }


}
