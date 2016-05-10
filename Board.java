import java.util.*;

public class Board {

    int[][] previous;
    int[][] board;
    int hPriority;

    /**
     * initial Board constructor
     */
    public Board() {
        board = new int[3][3];
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c] = (new Scanner(System.in).nextInt());
            }
        }
    }

    /**
     * Currently tester class for new Board states
     * @param b
     */
    public Board(int[][] b) { board = b; }

    public void showBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]==0 ? "  " : board[i][j] + " ");
            }
            System.out.println(" ");
        }
    }

    /**
     * Hamming priority function defines the priority of a state
     * as the number of blocks in the wrong position, plus the number
     * of moves made so far TODO: add number of moves made
     * @return Hamming priority
     */
    public int getHammingPriority() {

        int[][] goal = {{ 1, 2, 3, },
                        { 4, 5, 6, },
                        { 7, 8, 0} };
        hPriority = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] != goal[i][j])
                    hPriority++;
            }
        }
        return hPriority;
    }
}
