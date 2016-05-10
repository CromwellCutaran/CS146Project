import java.util.*;


public class Solver {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the initial board, one row at a time.\n" +
                           "Use '0' to represent the empty tile.");

        Board board = new Board();

        board.showBoard();

        Solver solution = new Solver(board);
    }

    public Solver(Board board) {

    }
}
