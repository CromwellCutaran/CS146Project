import java.util.*;

public class Board {

    private int[][] board;
    private ArrayList<Board> neighbors = new ArrayList<>();
    private int hPriority;
    Position blank;
    int moves;

    /**
     * initial Board constructor
     * @param t boolean denoting input constructor
     */
    public Board(Boolean t) {
        board = new int[3][3];
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c] = (new Scanner(System.in).nextInt());
                blank = new Position(r, c);
            }
        }
        moves = 0;
    }

    /**
     * Currently tester class for new Board states
     * @param b
     */
    public Board(int[][] b) {
        board = b;
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
                if (board[r][c] == 0)
                    blank = new Position(r, c);
        moves = 0;
    }

    public Board() {
        board = new int[3][3];
    }

    /**
     * Prints board in grid format
     * with blank tile
     */
    public void showBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]==0 ? "  " : board[i][j] + " ");
            }
            System.out.println(" ");
        }
    }

    /**
     * Builds ArrayList<Board> neighbors for current Board
     */
    public void setNeighbors() {
        Position[] possible = new Position[]
                    { new Position(blank, -1, 0),
                      new Position(blank, 0, 1),
                      new Position(blank, 1, 0),
                      new Position(blank, 0, -1) };
        String[] directions = {"n", "e", "s", "w"};

        for (int i = 0; i < 4; i++) {
            this.testSwaps(possible[i], directions[i]);
        }
    }

    public ArrayList<Board> getNeighbors() { return neighbors; }

    /**
     * Checks every possible swap and does so if valid
     * @param tile tile to swap blank with
     * @param dir direction to swap blank tile (used to choose bounds test)
     */
    public void testSwaps(Position tile, String dir) {
        switch (dir) {
            case "n": if (tile.r >= 0) neighbors.add(swap(tile));
                      break;
            case "e": if (tile.c <= 2) neighbors.add(swap(tile));
                      break;
            case "s": if (tile.r <= 2) neighbors.add(swap(tile));
                      break;
            case "w": if (tile.c >= 0) neighbors.add(swap(tile));
                      break;
        }

    }

    /**
     * @param tile tile to swap blank with
     * @return
     */
    public Board swap(Position tile) {
        Board neighbor = new Board();
        neighbor.moves = this.moves + 1;
        // copy current Board to neighbor Board
        // can't do a simple int[][] neighbor.board = board
        // because then both boards would share the same object
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                neighbor.board[i][j] = this.board[i][j];

        // swap blank with target tile
        neighbor.board[blank.r][blank.c] = board[tile.r][tile.c];
        neighbor.board[tile.r][tile.c] = 0;
        neighbor.blank = new Position(tile.r, tile.c);
        return neighbor;
    }

    /**
     * Checks whether or not the given
     * Board is at the goal state
     * @return t/f
     */
    public boolean isGoal() {
        int[][] goal = {{ 1, 2, 3, },
                        { 4, 5, 6, },
                        { 7, 8, 0} };

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] != goal[i][j]) return false;
        return true;
    }

    /**
     * Checks whether or not the given
     * Board is equal to 'this' Board
     * @param b Board to be compared
     * @return t/f
     */
    public boolean isEqual(Board b) {

        if (b != null) {
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    if (board[i][j] != b.board[i][j]) return false;
        } else {
            return false;
        }
        return true;
    }

    public int getMoves() { return moves; }

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
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] != goal[i][j])
                    hPriority++;
        hPriority += this.moves;
        return hPriority;
    }

    class Position {
        public int r;
        public int c;

        public Position(int row, int col) {
            r = row;
            c = col;
        }

        public Position(Position p, int dr, int dc) {
            r = p.r + dr;
            c = p.c + dc;
        }

        public String toString() {
            return r + " " + c;
        }
    }
}
