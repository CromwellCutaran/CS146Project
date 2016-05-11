/**
 * TODO: Implement attaching # of moves to newly found neighbors also exclude previous states
 */

import java.util.*;

public class Solver {

    private int moves;

    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        System.out.println("Enter the initial board, one row at a time.\n" +
//                           "Use '0' to represent the empty tile.");

//        int[][] b1 = {{ 1, 2, 3, },
//                      { 4, 0, 5, },
//                      { 6, 7, 8} };
//        Board board1 = new Board(b1);
//        testNeighbors(board1);

/*        int[][] b2 = {{ 1, 2, 3, },
                      { 4, 5, 0, },
                      { 6, 7, 8} };
        Board board2 = new Board(b2);
        testNeighbors(board2);

        Board board3 = board2.getNeighbors().get(0);
        testNeighbors(board3);*/

//        Solver solution = new Solver(board1);

        int[][] board = {{ 1, 8, 2, },
                { 0, 4, 3, },
                { 7, 6, 5} };



        Board initial = new Board(board);
        initial.showBoard();
        System.out.println();
        Solver solution = new Solver(initial);

    }

    /**
     * Constructor for a board's solution. Finds the actual solution
     * of a board, adding to an ArrayList<Board> containing every
     * state of the board seen along the way.
     * @param initial Board to be solved
     */
    public Solver(Board initial) {

        PriorityQueue<Board> boardQueue;
        ArrayList<Board> solution = new ArrayList<>();
        Board prevBoard = null;

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

        for (Board aBoard : solution)
        {
            aBoard.showBoard();
            System.out.println(aBoard.getMoves());
            System.out.println();
        }



    }

        /*
        Add 'initial' state to 'boardQueue'. While state dequeued is not
        goal state, add neighboring states to queue and continue loop.
        Add every state dequeued to an ArrayList<Board> solution as each
        is one board closer to goal state.
         */


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
        for (int i = 0; i < 2; i++) { // gets [1, 2] of b2
            System.out.println(b.blank.toString());
        }
    }

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