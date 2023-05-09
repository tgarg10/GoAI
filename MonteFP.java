import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;



public class MonteFP implements FinalProject{

    @Override
    public int[] playShortGame(char[][] b, int player) {

        class GomokuSimulator {
            static final int EMPTY = -1;
            static final int O = 0;
            static final int X = 1;
        
            static final int DRAW = 2;
            static final int GAME_CONTINUES = -2;

            //ArrayList<ArrayList<Integer>> winningMoves;

            Random rand;

            GomokuSimulator(){
                rand = new Random();
                rand.setSeed(1);
                setWinningMoves(); //not in yet
            }

            //This will ALWAYS be the case 
            //But fun to put in :)
            public static int ROWS = 20;
            public static int COLS = 20;
            public static int WINNING_LENGTH = 5; 

            //Creates an Array List of Array Lists of potential winning sequences 
            //In this way it won't even have to check in a for loop it can just
            //do it speedrun style in an array
            private void setWinningMoves() {

                winningMoves = new ArrayList<ArrayList<Integer>>();
                int boardSize = ROWS * COLS;
                for (int i = 0; i < boardSize; i++) {
                    // horizontal
                    if (i % COLS <= COLS - WINNING_LENGTH) {
                        ArrayList<Integer> move = new ArrayList<Integer>();
                        for (int j = 0; j < WINNING_LENGTH; j++) {
                            move.add(i + j);
                        }
                        winningMoves.add(move);
                    }
                    // vertical
                    if (i / COLS <= ROWS - WINNING_LENGTH) {
                        ArrayList<Integer> move = new ArrayList<Integer>();
                        for (int j = 0; j < WINNING_LENGTH; j++) {
                            move.add(i + j * COLS);
                        }
                        winningMoves.add(move);
                    }
                    // diagonal \
                    if (i % COLS <= COLS - WINNING_LENGTH && i / COLS <= ROWS - WINNING_LENGTH) {
                        ArrayList<Integer> move = new ArrayList<Integer>();
                        for (int j = 0; j < WINNING_LENGTH; j++) {
                            move.add(i + j * (COLS + 1));
                        }
                        winningMoves.add(move);
                    }
                    // diagonal /
                    if (i % COLS >= WINNING_LENGTH - 1 && i / COLS <= ROWS - WINNING_LENGTH) {
                        ArrayList<Integer> move = new ArrayList<Integer>();
                        for (int j = 0; j < WINNING_LENGTH; j++) {
                            move.add(i + j * (COLS - 1));
                        }
                        winningMoves.add(move);
                    }
                }

                //REMOVING SOON!!
                System.out.println("Total winning moves: " + winningMoves.size()); 
                System.out.println(winningMoves); 
            }
            
            
            int simulateGameFromLeafNode(Node n){

                int[][] currentGameState = n.b.clone();  //This needs work

                while(true){
                    ArrayList<Integer> moves = getAllpossibleMoves(currentGameState);
                    int randomMoveIndex = rand.nextInt(moves.size());
                    int moveToMake = moves.get(randomMoveIndex); //this won't work yet since not 1D array wait but it is hmmmmm
                    currentGameState[moveToMake] = player; 
                    int won = checkWinOrDraw(currentGameState, player);//ahhhhhhhhhhhh

                }

            }

            ArrayList<Integer> getAllpossibleMoves(int [][] gameState){
                int rows = GomokuSimulator.ROWS;
                int cols = GomokuSimulator.COLS;
                int winLength = GomokuSimulator.WINNING_LENGTH;

                ArrayList<Integer> allPossibleMoves = new ArrayList<Integer>(rows*cols);

                //checks to see if there is an empty spot ('.')
                for(int i = 0; i < rows; i++){
                    for(int j = 0; j < cols; j++){
                        if (gameState[i][j] == '.'){
                            allPossibleMoves.add(i*j); 
                        }
                    }
                }
                return allPossibleMoves; 


            }

        

            //NOT FINISHED
        int checkWinOrDraw(int [][] gameState, int player) {
            int rows = GomokuSimulator.ROWS;
            int cols = GomokuSimulator.COLS;
            int winLength = GomokuSimulator.WINNING_LENGTH;


             forLoop1 : for (ArrayList<Integer> w: winningMoves){
                int n = 0;
                for(int i = 0; i < rows; i++){
                    for(int j = 0; j < cols; j++){
                        if(gameState[i][j] != player){
                            continue forLoop1;
                        }else n++; 
                    } 
                }
                }

             }
        }
    




        int row, col;
        do {
            Random r = new Random();
            row = r.nextInt(20);
            col = r.nextInt(20);
        } while (b[row][col] != '.');

        return new int[] { row, col };
    }




























    @Override
    public int[] playLongGame(char[][] b, int player) {
        Random r = new Random();
        int row = r.nextInt(20);
        int col = r.nextInt(20);
        return new int[] { row, col };
    }

    @Override
    public int isShortGameOver(char[][] arr) {
        // This method was largely written by ChatGPT!
        // Check for five consecutive Xs or Os horizontally
        int hasXorO = 0;
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 16; j++) {
                if (arr[i][j] == 'X' && arr[i][j + 1] == 'X' && arr[i][j + 2] == 'X' && arr[i][j + 3] == 'X'
                        && arr[i][j + 4] == 'X') {
                    hasXorO = 1;
                    break;
                } else if (arr[i][j] == 'O' && arr[i][j + 1] == 'O' && arr[i][j + 2] == 'O' && arr[i][j + 3] == 'O'
                        && arr[i][j + 4] == 'O') {
                    hasXorO = 2;
                    break;
                }
            }
            if (hasXorO != 0) {
                break;
            }
        }

        // Check for five consecutive Xs or Os vertically
        if (hasXorO == 0) {
            for (int i = 0; i < 16; i++) {
                for (int j = 0; j < 20; j++) {
                    if (arr[i][j] == 'X' && arr[i + 1][j] == 'X' && arr[i + 2][j] == 'X' && arr[i + 3][j] == 'X'
                            && arr[i + 4][j] == 'X') {
                        hasXorO = 1;
                        break;
                    } else if (arr[i][j] == 'O' && arr[i + 1][j] == 'O' && arr[i + 2][j] == 'O' && arr[i + 3][j] == 'O'
                            && arr[i + 4][j] == 'O') {
                        hasXorO = 2;
                        break;
                    }
                }
                if (hasXorO != 0) {
                    break;
                }
            }
        }

        // Check for five consecutive Xs or Os diagonally
        if (hasXorO == 0) {
            for (int i = 0; i < 16; i++) {
                for (int j = 0; j < 16; j++) {
                    if (arr[i][j] == 'X' && arr[i + 1][j + 1] == 'X' && arr[i + 2][j + 2] == 'X' && arr[i + 3][j + 3] == 'X'
                            && arr[i + 4][j + 4] == 'X') {
                        hasXorO = 1;
                        break;
                    } else if (arr[i][j] == 'O' && arr[i + 1][j + 1] == 'O' && arr[i + 2][j + 2] == 'O'
                            && arr[i + 3][j + 3] == 'O' && arr[i + 4][j + 4] == 'O') {
                        hasXorO = 2;
                        break;
                    } else if (arr[i][j + 4] == 'X' && arr[i + 1][j + 3] == 'X' && arr[i + 2][j + 2] == 'X'
                            && arr[i + 3][j + 1] == 'X' && arr[i + 4][j] == 'X') {
                        hasXorO = 1;
                        break;
                    } else if (arr[i][j + 4] == 'O' && arr[i + 1][j + 3] == 'O' && arr[i + 2][j + 2] == 'O'
                            && arr[i + 3][j + 1] == 'O' && arr[i + 4][j] == 'O') {
                        hasXorO = 2;
                        break;
                    }
                }
                if (hasXorO != 0) {
                    break;
                }
            }
        }
        return hasXorO;

    }

    @Override
    public int isLongGameOver(char[][] b) {
        return 0;
    }
}
