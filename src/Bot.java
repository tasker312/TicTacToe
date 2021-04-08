import java.awt.*;

public class Bot {
    public static final char botMove = TicTacToe.player2;
    public static final char playerMove = TicTacToe.player1;
    private final Difficulty difficulty;
    private final char[][] board;

    public Bot(Difficulty difficulty, char[][] board) {
        this.difficulty = difficulty;
        this.board = board;
    }

    public Point getMove(){
        Point move = null;
        switch (difficulty) {
            case EASY:
                move = getMoveEasy();
                break;
            case MEDIUM:
                move = getMoveMedium();
                break;
        }
        return move;
    }

    private Point getMoveEasy() {
        int x, y;
        do {
            x = (int) (Math.random() * board.length);
            y = (int) (Math.random() * board.length);
        } while (board[x][y] != TicTacToe.NULL_CHAR);
        return new Point(x, y);
    }

    private Point getMoveMedium() {
        //check move that wins
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == TicTacToe.NULL_CHAR) {
                    board[i][j] = botMove;
                    if (TicTacToe.checkWinner(board) == botMove) {
                        board[i][j] = TicTacToe.NULL_CHAR;
                        return new Point(i, j);
                    }
                    board[i][j] = TicTacToe.NULL_CHAR;
                }
            }
        }
        //check move that doesn't lose
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == TicTacToe.NULL_CHAR) {
                    board[i][j] = playerMove;
                    if (TicTacToe.checkWinner(board) == playerMove) {
                        board[i][j] = TicTacToe.NULL_CHAR;
                        return new Point(i, j);
                    }
                    board[i][j] = TicTacToe.NULL_CHAR;
                }
            }
        }
        //get random move
        return getMoveEasy();
    }
}
