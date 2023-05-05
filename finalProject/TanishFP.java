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

            public String convertToRowString() {
                String S = "";
                int n = 0;
                for (int i = 0; i < 20; i++) {
                    n = 0;
                    for (int j = 0; j < 20; j++) {
                        if (b[i][j] == P) {
                            n = 0;
                            S += "1";
                        }
                        else if (b[i][j] == '.') {
                            n++;
                            if (n > 2) continue;
                            S += "0";
                        }
                        else {
                            n = 0;
                            S += "2";
                        } 
                    }
                    S += " ";
                }
                return S;    
            }

            public String convertToColumnString() {
                String S = "";
                int n = 0;
                for (int i = 0; i < 20; i++) {
                    for (int j = 0; j < 20; j++) {
                        if (b[j][i] == P){
                            n = 0;
                            S += "1";
                        }
                        else if (b[j][i] == '.') {
                            n++;
                            if (n > 2) continue;
                            S += "0";
                        }
                        else {
                            n = 0;
                            S += "2";
                        } 
                    }
                    S += " ";
                }
                return S;   
            }

            // ChatGPT wrote this function
            // Generate all the Diagonals
            /*
            public String[] generateLongDiagonals() {
                String[] longDiagonals = new String[62];
                int idx = 0;
                // Check diagonals starting from top-left corner
                for (int k = 4; k <= 20 - 1; k++) {
                    StringBuilder diagonal = new StringBuilder();
                    for (int i = 0; i <= k; i++) {
                        int j = k - i;
                        if (i < 20 && j < 20) {
                            diagonal.append(b[i][j]);
                        }
                    }
                    if (diagonal.length() > 4) {
                        longDiagonals[idx] = (diagonal.toString());
                        idx++;
                    }
                }
            
                // Check diagonals starting from top-right corner
                for (int k = 20 - 5; k >= 0; k--) {
                    StringBuilder diagonal = new StringBuilder();
                    for (int i = 0; i < 20 && i + k < 20; i++) {
                        int j = 20 - 1 - k - i;
                        diagonal.append(b[i][j]);
                    }
                    if (diagonal.length() > 5) {
                        longDiagonals[idx] = (diagonal.toString());
                        idx++;
                    }
                }
            
                // Check diagonals starting from bottom-left corner
                for (int k = 4; k <= 20 - 5; k++) {
                    StringBuilder diagonal = new StringBuilder();
                    for (int i = 20 - 1; i >= k; i--) {
                        int j = 20 - 1 - i + k;
                        if (j < 20) {
                            diagonal.append(b[i][j]);
                        }
                    }
                    if (diagonal.length() > 4) {
                        longDiagonals[idx] = (diagonal.toString());
                        idx++;
                    }
                }
            
                // Check diagonals starting from bottom-right corner
                for (int k = 20 - 5; k >= 0; k--) {
                    StringBuilder diagonal = new StringBuilder();
                    for (int i = 20 - 1; i >= 0 && i + (20 - 1 - k) >= 20 - 5; i--) {
                        int j = 20 - 1 - (i + (20 - 1 - k) - (20 - 5));
                        if (j >= 0) {
                            diagonal.append(b[i][j]);
                        }
                    }
                    if (diagonal.length() > 4) {
                        longDiagonals[idx] = (diagonal.toString());
                        idx++;
                    }
                }
            
                return longDiagonals;
            }
            */

            // From bottom left to top right --> /
            public String convertToDiagonal1String() {
                String S = "";
                int n = 0;
                for (int i = 4; i < 20; i++) {
                    for (int j = i; j >= 0; j--) {
                        if (b[j][i-j] == P){
                            n = 0;
                            S += "1";
                        }
                        else if (b[j][i-j] == '.') {
                            n++;
                            if (n > 2) continue;
                            S += "0";
                        }
                        else {
                            n = 0;
                            S += "2";
                        } 
                    }
                    S += " ";
                }

                for (int i = 1; i < 16; i++) {
                    S = "";
                    for (int j = i; j < 20; j++) {
                        if (b[19-j+i][j] == P){
                            n = 0;
                            S += "1";
                        }
                        else if (b[19-j+i][j] == '.') {
                            n++;
                            if (n > 2) continue;
                            S += "0";
                        }
                        else {
                            n = 0;
                            S += "2";
                        } 
                    }
                    S += " ";
                }

                return S;
            }

            // From top left to bottom right --> \
            public String convertToDiagonal2String() {
                String S = "";
                int n = 0;

                for (int i = 0; i < 15; i++) {
                    for (int j = 0; j < 20 - i - 1; j++) {
                        if (b[i+j+1][j] == P){
                            n = 0;
                            S += "1";
                        }
                        else if (b[i+j+1][j] == '.') {
                            n++;
                            if (n > 2) continue;
                            S += "0";
                        }
                        else {
                            n = 0;
                            S += "2";
                        } 
                    }
                    S += " ";
                }

                for (int i = 0; i < 16; i++) {
                    for (int j = 0; j < 20 - i; j++) {
                        if (b[j][i+j] == P){
                            n = 0;
                            S += "1";
                        }
                        else if (b[j][i+j] == '.') {
                            n++;
                            if (n > 2) continue;
                            S += "0";
                        }
                        else {
                            n = 0;
                            S += "2";
                        } 
                    }
                    S += " ";
                }
                return S;
            }
        
            public String[] getStrings() {
                String[] returnArray = new String[4]; 
                returnArray[0] = convertToRowString();
                returnArray[1] = convertToColumnString();
                // returnArray[2] = generateLongDiagonals();
                returnArray[2] = convertToDiagonal1String();
                returnArray[3] = convertToDiagonal2String();
                return returnArray;
            }
        }

        class BestMove {
            private char[][] board;
            private int p; 
            private int dpth;

            HashMap<String, Integer> scoreDictionary = new HashMap<>();
            
            public BestMove(char[][] b, int depth, int player) {
                this.board = b;
                this.dpth = depth;
                this.p = player;
                this.scoreDictionary.put("11111", 200); // Five in a Row
                this.scoreDictionary.put("11110", 100); // Live Four
                this.scoreDictionary.put("11101", 100); // Live Four
                this.scoreDictionary.put("11011", 100);
                this.scoreDictionary.put("10111", 100);
                this.scoreDictionary.put("01111", 100);
                this.scoreDictionary.put("011100", 100);
                this.scoreDictionary.put("001110", 100);
                this.scoreDictionary.put("010110", 100);
                this.scoreDictionary.put("011010", 100);
            }

            // Current Board - Simulated board
            // Depth - depth
            // Alpha - Highest/Lowest Value from Previous Iteration
            // Player - 1 or 2
            public int miniMax(char[][] currentBoard, int depth, int alpha, int beta, int player) {

                // Setting Opponent in current game simulation
                int opponentPlayer = 1;

                // Making numbers 1 & 2 into 'X' & 'O'
                char tPlayer; // Player whose turn it is in the Game / Simulated Game
                // char oPlayer; // Player who's the opponent

                if (player == 1) {
                    tPlayer = 'X';
                    opponentPlayer = 2;
                } else {
                    tPlayer = 'O';
                }

                // Base Case
                if (depth == 0) {
                    int score = calculateScore(b, tPlayer);
                    if (p == player) return score;
                    else return -score;
                }

                if (isShortGameOver(currentBoard) != 0 || boardIsFull(currentBoard)) {
                    // Current Player wins
                    if (isShortGameOver(currentBoard) == p) return 200;
                    // Board filled up
                    else if (isShortGameOver(currentBoard) == 0) return 0;
                    // Opponent Wins
                    else return -200;
                }
                
                // Playing all possible moves in current combination
                if (p == player) {
                    int curMax = Integer.MIN_VALUE;
                    for (int i = 0; i < 20; i++) {
                        for (int j = 0; j < 20; j++) {
                            if (currentBoard[i][j] == '.' && !checkNotValid(currentBoard, i, j))  {
                                // System.out.println(i + " " + j);
                                currentBoard[i][j] = tPlayer;
                                int miniMaxValue = miniMax(currentBoard, depth - 1, alpha, beta, opponentPlayer);
                                if (miniMaxValue > curMax) {
                                    curMax = miniMaxValue;
                                }
                                if (beta <= alpha) return curMax;
                                currentBoard[i][j] = '.';
                            }
                        }
                    }
                    return curMax;
                } else {
                    int curMin = Integer.MAX_VALUE;
                    for (int i = 0; i < 20; i++) {
                        for (int j = 0; j < 20; j++) {
                            if (currentBoard[i][j] == '.' && !checkNotValid(currentBoard, i, j)) {
                                // System.out.println(i + " " + j);
                                currentBoard[i][j] = tPlayer;
                                int miniMinValue = miniMax(currentBoard, depth - 1, alpha, beta, opponentPlayer);
                                if (miniMinValue < curMin) {
                                    curMin = miniMinValue;
                                }
                                if (beta <= alpha) return curMin;
                                currentBoard[i][j] = '.';
                            }
                        }
                    }
                    return curMin;
                }
            }

            public boolean checkNotValid(char[][] cB, int i, int j) {
                if (i > 0 && cB[i-1][j] != '.') return false;
                if (i < 19 && cB[i+1][j] != '.') return false;
                if (j > 0 && cB[i][j-1] != '.') return false;
                if (j < 19 && cB[i][j+1] != '.') return false;
                if (i > 0 && j > 0 && cB[i-1][j-1] != '.') return false;
                if (i < 19 && j > 0 && cB[i+1][j-1] != '.') return false;
                if (i > 0 && j < 19 && cB[i-1][j+1] != '.') return false;
                if (i < 19 && j < 19 && cB[i+1][j+1] != '.') return false;
                return true;
            }

            public int calculateScore(char[][] b, char player) {

                int score = 0;
                
                // String[] patternList = {"11111", "11110", "11101", "11011", "10111", "01111", "011100", "001110", "010110", "011010"};

                convertToString cTS = new convertToString(b, player);
                String[] rv = cTS.getStrings();
                int c = 0;
                for (String stringArray : rv) {
                        for (String pattern : scoreDictionary.keySet()) {
                            c = stringArray.split(pattern, -1).length-1;
                            if (c>0) {
                                // System.out.println(pattern + " " + checkString);
                                score += c* scoreDictionary.get(pattern);
                                System.out.println(score);
                            }
                        }
                    }
                return score;
            }

            public int[] calculatingBestMove() {

                // Making numbers 1 & 2 into 'X' & 'O'
                char tPlayer; // Player whose turn it is in the Game / Simulated Game
                int op = 1; // Opponent Player
                if (p == 1) {
                    tPlayer = 'X';
                    op = 2;
                } else {
                    tPlayer = 'O';
                }

                // Playing all possible moves in current combination
                int curMax = Integer.MIN_VALUE;
                int[] moves = new int[] {0, 0};
                for (int i = 0; i < 20; i++) {
                    for (int j = 0; j < 20; j++) {
                        if (board[i][j] == '.' && !checkNotValid(board, i, j))  {   
                            // System.out.println(i + " " + j);
                            board[i][j] = tPlayer;
                            int miniMaxValue = miniMax(board, dpth, Integer.MIN_VALUE, Integer.MAX_VALUE, op);
                            if (miniMaxValue > curMax) {
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

        BestMove findBestMove = new BestMove(b, 3, player);
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
        if(!boardIsFull(b)) return -1;

        int score1 = longGameScore(b, 1);
        int score2 = longGameScore(b, 2);
        if(score1 == score2) return 0;
        return score1 > score2 ? 1 : 2;
    }

    private boolean boardIsFull(char[][] board) {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (board[i][j] == '.')
                    return false;
            }
        }
        return true;
    }

    private int longGameScore(char[][] b, int p) {
		char c = (p == 1) ? 'X' : 'O';
        int score = 0;

        // Vertical
        for(int j = 0; j < 20; j++){
            int rl = 0; // run length
            for(int i = 0; i <= 20; i++){
                if(i < 20 && b[i][j] == c){
                    rl++;
                } else {
                    score += (rl / 5);
                    rl = 0;
                }
            }
        }
        //System.out.println(c + ", V, Long Game Score: " + score);

        // Horizontal
        for(int i = 0; i < 20; i++){
            int rl = 0; // run length
            for(int j = 0; j <= 20; j++){
                if(j < 20 && b[i][j] == c){
                    rl++;
                } else {
                    score += (rl / 5);
                    rl = 0;
                }
            }
        }
        //System.out.println(c + ", H, Long Game Score: " + score);

        // Southeast
        for(int s = -15; s <= 15; s++){
            int rl = 0; // run length
            for(int j = Math.max(-s, 0); j <= 20 && s+j <= 20; j++){
                int i = s + j;
                if(i < 20 && j < 20 && b[i][j] == c){
                    rl++;
                } else {
                    score += (rl / 5);
                    rl = 0;
                }
            }
        }
        //System.out.println(c + ", S, Long Game Score: " + score);

        // Northeast
        for(int s = -15; s <= 15; s++){
            int rl = 0; // run length
            for(int j = Math.max(-s, 0); j <= 20 && s+j <= 20; j++){
                int i = 19 - (s + j);
                if(i >= 0 && i < 20 && j < 20 && b[i][j] == c){
                    rl++;
                } else {
                    score += (rl / 5);
                    rl = 0;
                }
            }
        }
        //System.out.println(c + ", N, Long Game Score: " + score);
        return score;
	}
    
}