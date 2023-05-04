import java.util.HashMap;

public class TanishFP implements FinalProject {

    @Override
    public int[] playShortGame(char[][] b, int player) {

        class convertToString {
            
            private char[][] b;
            private char P;

            public convertToString(char[][] b2, char c) {
                this.b = b2;
                this.P = c;
            }

            public String[] convertToRowString() {
                String[] row = new String[20];
                String S = "";
                for (int i = 0; i < 20; i++) {
                    S = "";
                    for (int j = 0; j < 20; j++) {
                        if (b[i][j] == P) S += "1";
                        else if (b[i][j] == '.') S += "0";
                        else S += "2";
                    }
                    row[i] = S;
                }
                return row;
                
            }

            public String[] convertToColumnString() {
                String[] column = new String[20];
                String S = "";
                for (int i = 0; i < 20; i++) {
                    S = "";
                    for (int j = 0; j < 20; j++) {
                        if (b[j][i] == P) S += "1";
                        else if (b[j][i] == '.') S += "0";
                        else S += "2";
                    }
                    column[i] = S;
                }
                return column;
                
            }

            // From bottom left to top right --> /
            public String[] convertToDiagonal1String() {
                String[] diagonal = new String[31];
                String S = "";
                int a = 0;
                for (int i = 4; i < 20; i++) {
                    S = "";
                    for (int j = i; j >= 0; j--) {
                        if (b[j][i-j] == P) S += "1";
                        else if (b[j][i-j] == '.') S += "0";
                        else S += "2";
                    }
                    diagonal[a] = S;
                    a++;
                }

                for (int i = 19; i > 4; i--) {
                    S = "";
                    for (int j = 1; j <= i; j++) {
                        if (b[j][i-j+1] == P) S += "1";
                        else if (b[j][i-j+1] == '.') S += "0";
                        else S += "2";
                    }
                    diagonal[a] = S;
                    a++;
                }

                return diagonal;
            }

            // From top left to bottom right --> \
            public String[] convertToDiagonal2String() {
                String[] diagonal = new String[31];
                String S = "";
                int a = 0;
                for (int i = 0; i < 16; i++) {
                    S = "";
                    for (int j = 0; j < 20 - i; j++) {
                        if (b[j][i+j] == P) S += "1";
                        else if (b[j][i+j] == '.') S += "0";
                        else S += "2";
                    }
                    diagonal[a] = S;
                    a++;
                }

                for (int i = 0; i < 15; i++) {
                    S = "";
                    for (int j = 0; j < 20 - i - 1; j++) {
                        if (b[i+j+1][j] == P) S += "1";
                        else if (b[i+j+1][j] == '.') S += "0";
                        else S += "2";
                    }
                    diagonal[a] = S;
                    a++;
                }
                return diagonal;
            }
        
            public String[][] getStrings() {
                String[][] returnArray = new String[4][]; 
                returnArray[0] = convertToRowString();
                returnArray[1] = convertToColumnString();
                returnArray[2] = convertToDiagonal1String();
                returnArray[3] = convertToDiagonal2String();
                return returnArray;
            }
        }

        class BestMove {
            private char[][] board;
            private int p; 
            private int depth;
            private int staticDepth;

            public BestMove(char[][] b, int player, int depth) {
                this.board = b;
                this.p = player;
                this.depth = depth;
                this.staticDepth = depth;
            }

            public int miniMax(int depth, char[][] currentBoard, int turnPlayer) {

                // Setting Opponent in current game simulation
                int opponentPlayer;
                char plyr;
                if (turnPlayer == 1) {
                    opponentPlayer = 2;
                    plyr = 'X';
                } 
                else {
                    opponentPlayer = 1;
                    plyr = 'O';
                }
                
                // Opponent wins
                if (isShortGameOver(currentBoard) == opponentPlayer) return Integer.MIN_VALUE;
                // Current Player Wins
                if (isShortGameOver(currentBoard) == turnPlayer) return Integer.MAX_VALUE;
                // If Draw
                if (isShortGameOver(currentBoard) == 0) return 0;

                // Base Case
                if (depth == 0) return calculateScore(b, plyr);

                // Making numbers 1 & 2 into 'X' & 'O'
                char tPlayer; // Player whose turn it is in the Game / Simulated Game
                char oPlayer; // Player who's the opponent

                if (turnPlayer == 1) {
                    tPlayer = 'X';
                    oPlayer = 'O';
                } else {
                    tPlayer = 'O';
                    oPlayer = 'X';
                }

                // Playing all possible moves in current combination

                if (depth % 2 == staticDepth % 2) {
                    int curMax = Integer.MIN_VALUE;
                    for (int i = 0; i < 20; i++) {
                        for (int j = 0; j < 20; j++) {
                            if (currentBoard[i][j] == '.') {
                                currentBoard[i][j] = tPlayer;
                                int miniMaxValue = miniMax(depth - 1, currentBoard, oPlayer);
                                if (miniMaxValue >= curMax) {
                                    curMax = miniMaxValue;
                                }
                                currentBoard[i][j] = '.';
                            }
                        }
                    }
                    return curMax;
                } else {
                    int curMin = Integer.MAX_VALUE;
                    for (int i = 0; i < 20; i++) {
                        for (int j = 0; j < 20; j++) {
                            if (currentBoard[i][j] == '.') {
                                currentBoard[i][j] = tPlayer;
                                int miniMaxValue = - miniMax(depth - 1, currentBoard, oPlayer);
                                if (miniMaxValue <= curMin) {
                                    curMin = miniMaxValue;
                                }
                                currentBoard[i][j] = '.';
                            }
                        }
                    }
                    return curMin;
                }
                
            }

            private int calculateScore(char[][] b, char player) {

                int score = 0;

                
                HashMap<String, Integer> scoreDictionary = new HashMap<>();
                scoreDictionary.put("11111", 100);
                scoreDictionary.put("11110", 100);
                scoreDictionary.put("11101", 100);
                scoreDictionary.put("11011", 100);
                scoreDictionary.put("10111", 100);
                scoreDictionary.put("01111", 100);
                scoreDictionary.put("011100", 100);
                scoreDictionary.put("001110", 100);
                scoreDictionary.put("010110", 100);
                scoreDictionary.put("011010", 100);
                

                // String[] patternList = {"11111", "11110", "11101", "11011", "10111", "01111", "011100", "001110", "010110", "011010"};

                convertToString cTS = new convertToString(b, player);
                String[][] rv = cTS.getStrings();
                for (String[] stringArray : rv) {
                    for (String checkString : stringArray) {
                        for (String pattern : scoreDictionary.keySet()) {
                            if (checkString.contains(pattern)) score += scoreDictionary.get(pattern);
                        }
                    }
                }
                return score;
            }

            public int[] calculatingBestMove() {
                // Making numbers 1 & 2 into 'X' & 'O'
                char tPlayer; // Player whose turn it is in the Game / Simulated Game
                char oPlayer; // Player who's the opponent

                if (p == 1) {
                    tPlayer = 'X';
                    oPlayer = 'O';
                } else {
                    tPlayer = 'O';
                    oPlayer = 'X';
                }

                // Playing all possible moves in current combination
                int curMax = Integer.MIN_VALUE;
                int[] moves = new int[] {0, 0};
                for (int i = 0; i < 20; i++) {
                    for (int j = 0; j < 20; j++) {
                        if (board[i][j] == '.') {
                            board[i][j] = tPlayer;
                            int miniMaxValue = miniMax(depth - 1, board, oPlayer);
                            if (miniMaxValue >= curMax) {
                                curMax = miniMaxValue;
                                moves = new int[] {i, j};
                            }
                            board[i][j] = '.';
                        }
                    }
                }
                return moves;
            }
        }

        BestMove findBestMove = new BestMove(b, player, 2);
        int[] move = findBestMove.calculatingBestMove();

        return move;
    }

    @Override
    public int[] playLongGame(char[][] b, int player) {
        // TODO Auto-generated method stub
        return new int[] {0, 0};
    }

    @Override
    public int isShortGameOver(char[][] arr) {
        // This method was taken from Hochberg.java

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
        // TODO Auto-generated method stub
        return 0;
    }
    
}