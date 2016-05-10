/**
 * TODO: How to implement storing previous state and adjacency list for current state
 */

import java.util.*;


public class Solver {

    private int moves;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the initial board, one row at a time.\n" +
                           "Use '0' to represent the empty tile.");

        Board initial = new Board();

        initial.showBoard();

        Solver solution = new Solver(initial);

    }

    /**
     * Constructor for a board's solution. Finds the actual solution
     * of a board, adding to an ArrayList<Board> containing every
     * state of the board seen along the way.
     * @param initial Board to be solved
     */
    public Solver(Board initial) {

        PriorityQueue<Board> boardQueue = new PriorityQueue<>(new BoardComparator());

    }

    /**
     * @return # of moves taken to reach solution
     */
    public int moves() {
        return moves;
    }

    /**
     *  Since we use a PriorityQueue to decide which board state to dequeue next,
     *  we must use a custom Comparator with which we can instantiate our
     *  PriorityQueue. BoardComparator uses the Hamming priority function
     *  implemented in Board.java to return a state's priority.
     */
    static class BoardComparator implements Comparator<Board> {

        public int compare(Board b1, Board b2) {
            return b1.getHammingPriority() - b2.getHammingPriority();
        }
    }

    /**
     * Test to show that BoardComparator is indeed working properly
     */
    public static void testPriorityQueue() {
        BoardComparator bc = new BoardComparator();

        PriorityQueue<Board> boardQueue = new PriorityQueue<>(bc);

        int[][] b1 = {{ 1, 2, 3, },
                      { 4, 0, 5, },
                      { 6, 7, 8} };

        int[][] b2 = {{ 1, 2, 3, },
                      { 4, 5, 0, },
                      { 6, 7, 8} };

        Board board1 = new Board(b1);
        Board board2 = new Board(b2);

        boardQueue.add(board1);
        boardQueue.add(board2);

        System.out.println(board1.getHammingPriority() + "\n" + board2.getHammingPriority());

        Board b = boardQueue.poll(); // should give b2 to b

        b.showBoard();
    }
}
