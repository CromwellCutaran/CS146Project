import java.util.*;

public class Solver {

    private int moves;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the initial board, one integer at a time,\n" +
                           "in the following order (use '0' to represent the empty tile):\n" +
                           "- - ->\n" +
                           "- ->\n" +
                           "->\n");
        Board initial = new Board(true);
        System.out.println("Initial board:");
        testNeighbors(initial);
        Solver solution = new Solver(initial);
    }

    /**
     * Constructor for a board's solution. Finds the actual solution
     * of a board, adding to a List1 containing every
     * state of the board seen along the way.
     * @param initial Board to be solved
     */
    public Solver(Board initial) {

        PriorityQueue<Board> boardQueue;
        ArrayList<Board> solution = new ArrayList<>();
        Board prevBoard = null;
        int statesReached = 0;

        // initial state: prevBoard = null, initial = initial board
        while(!initial.isGoal())
        {
            //generate neighbor boards
            initial.setNeighbors();

            boardQueue = new PriorityQueue<>(new BoardComparator());
            //put the neighbor boards in the priority queue
            for (Board neighbor : initial.getNeighbors())
            {
                boardQueue.add(neighbor);
                statesReached++;
            }

            //if the lowest priority board is equals to the previous board,
            //dequeue it and find another board with the lowest priority.
            //And the board will be added to the array list of solution Board.
            if (boardQueue.peek().isEqual(prevBoard))
                boardQueue.remove();
            solution.add(boardQueue.poll());


            //change the state: prevBoard = initial,
            //initial = the new Board which added to the solution recently
            prevBoard = initial;
            initial = solution.get(solution.size()-1);

            //now check whether initial reached to the goal for while loop
        }

        for (Board reached : solution)
        {
            reached.showBoard();
            System.out.println();
        }

        System.out.println("States enqueued: " + statesReached);
        System.out.println("Number of moves: " + solution.get(solution.size()-1).getMoves());
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
     * Tester to show that BoardComparator is indeed working properly
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
        for (int i = 0; i < 2; i++) { // gets [1, 2] of b2
            System.out.println(b.blank.toString());
        }
    }

    /**
     * Tester for implementation of finding adjacent states
     * @param b target state
     */
    public static void testNeighbors(Board b) {
        System.out.println("Initial:");
        b.showBoard();
        System.out.println();

        b.setNeighbors();
        for (Board neighbor : b.getNeighbors()) {
            neighbor.showBoard();
            System.out.println("Moves: " + neighbor.getMoves());
            System.out.println();
        }
    }
}