import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TicTacToe {
    public static final char NULL_CHAR = '\u0000';
    public static final char player1 = 'X';
    public static final char player2 = 'O';
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private final char[][] board;

    private char lastMove;

    public TicTacToe() {
        this.board = new char[3][3];
        lastMove = player2;
        game();
    }

    private void game() {
        System.out.println(this.toString());
         do {
            Point move = getMoveByConsole();
            setMove(move);
            System.out.println(this.toString());
        } while (checkWinner() == 0);
        System.out.println(getWinner());
    }

    private String getWinner() {
        int winner = checkWinner();
        switch (winner) {
            case 'X':
                return "X - win";
            case 'O':
                return "O - win";
            case 1:
                return "Draw";
            default:
                throw new IllegalStateException("Unexpected value: " + winner);
        }
    }

    /*
    (int) 'X' - X win
    (int) 'O' - O win
           1  - draw
           0  - not completed
     */
    private int checkWinner() {
        //rows
        for (int i = 0; i < board.length; i++) {
            boolean win = true;
            for (int j = 1; j < board.length; j++) {
                if (board[i][j] == NULL_CHAR || board[i][j] != board[i][0]) {
                    win = false;
                    break;
                }
            }
            if (win) return board[i][0];
        }
        //columns
        for (int j = 0; j < board.length; j++) {
            boolean win = true;
            for (int i = 0; i < board.length; i++) {
                if (board[i][j] == NULL_CHAR || board[i][j] != board[0][j]) {
                    win = false;
                    break;
                }
            }
            if (win) return board[j][0];
        }
        //diagonals
        boolean win = true;
        for (int i = 1; i < board.length; i++) {
            if (board[i][i] == NULL_CHAR || board[i][i] != board[0][0]) {
                win = false;
                break;
            }
        }
        if (win) return board[0][0];

        win = true;
        for (int i = 1; i < board.length; i++) {
            if (board[i][board.length - i - 1] == NULL_CHAR || board[i][board.length - i - 1] != board[0][2]) {
                win = false;
                break;
            }
        }
        if (win) return board[0][2];

        //board filled
        if (isBoardFilled())
            return 1;

        return 0;
    }

    private boolean isBoardFilled() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == NULL_CHAR)
                    return false;
            }
        }
        return true;
    }

    private void setMove(Point move) {
        if (lastMove == player1) {
            board[move.x][move.y] = 'O';
            lastMove = player2;
        } else {
            board[move.x][move.y] = 'X';
            lastMove = player1;
        }
    }

    private Point getMoveByConsole() {
        try {
            String[] move = reader.readLine().split("\\s");
            int x = Integer.parseInt(move[0]);
            int y = Integer.parseInt(move[1]);
            if (!isMoveAvailable(x, y)) {
                throw new Exception();
            }
            return new Point(x, y);
        } catch (Exception e) {
            System.out.println("Wrong input! Repeat your turn");
            return getMoveByConsole();
        }
    }

    private boolean isMoveAvailable(int x, int y) {
        return x>= 0 && y>= 0 && x < board.length && y < board.length && board[x][y] == NULL_CHAR;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("._____.\n");
        for (int i = 0; i < board.length; i++) {
            sb.append("|");
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == NULL_CHAR) {
                    sb.append(" |");
                } else {
                    sb.append(board[i][j]).append("|");
                }
            }
            sb.append("\n");
        }
        sb.append("˙¯¯¯¯¯˙");
        return sb.toString();
    }
}
