import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;

public class TicTacToeGUI extends JFrame implements ActionListener {

    private JButton[][] cells;
    private char[][] board;
    private char currentPlayer = 'O';
    private char botPlayer = 'X';
    private FinalProject fp;
    private String[] args;

    public void readFPfromFile() {
        String name = "";
        if (args.length == 0) {
            name = "MinimaxFP";
        } else {
            name = args[0];
        }

        FinalProject fp;
        try {
            fp = (FinalProject) Class.forName(name).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
        | InvocationTargetException | NoSuchMethodException | SecurityException
        | ClassNotFoundException e) {
            fp = null;
            System.out.println("Error!!!");
            e.printStackTrace();
        }
        this.fp = fp;
    }

    public TicTacToeGUI(String[] args) {
        this.args = args;
        readFPfromFile();
        cells = new JButton[20][20];
        board = new char[20][20];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                board[i][j] = '.';
            }
        }

        setTitle("Tic Tac Toe 20x20");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 1200);
        setLayout(new GridLayout(20, 20));

        for (int row = 0; row < 20; row++) {
            for (int col = 0; col < 20; col++) {
                cells[row][col] = new JButton();
                cells[row][col].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
                cells[row][col].addActionListener(this);
                add(cells[row][col]);
            }
        }
        
        // int[] mv = fp.playShortGame(board, 1);
        // board[mv[0]][mv[1]] = botPlayer;
        // cells[mv[0]][mv[1]].setText(Character.toString(botPlayer));
        // cells[mv[0]][mv[1]].setEnabled(false);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton cell = (JButton) e.getSource();
        int row = -1, col = -1;

        // Find the row and column of the clicked cell
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (cell == cells[i][j]) {
                    row = i;
                    col = j;
                    break;
                }
            }
        }

        // Add the player's piece to the board
        if (row != -1 && col != -1 && board[row][col] == '.') {
            board[row][col] = currentPlayer;
            cell.setText(Character.toString(currentPlayer));
            cell.setEnabled(false);

            // Switch to the other player
            //currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }

        // int[] mv = fp.playShortGame(board, 2);
        int[] mv = fp.playLongGame(board, 2);
        board[mv[0]][mv[1]] = botPlayer;
        cells[mv[0]][mv[1]].setText(Character.toString(botPlayer));
        cells[mv[0]][mv[1]].setEnabled(false);

    }

    public static void main(String[] args) {
        new TicTacToeGUI(args);
    }
}
