import java.util.*;

public class Board {

    int[][] board;

    public Board() {
        board = new int[3][3];
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c] = (new Scanner(System.in).nextInt());
            }
        }
    }

    public void showBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]==0 ? "  " : board[i][j] + " ");
            }
            System.out.println(" ");
        }
    }
}
