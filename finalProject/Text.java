import java.util.HashMap;

public class Text  {

public int[] playLongGame(char[][] b, int player) {
        
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
                    n = 0;
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
                    n = 0;
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
                    n = 0;
                }

                for (int i = 1; i < 16; i++) {
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
                    n = 0;
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
                    n = 0;
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
                    n = 0;
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
            
            public BestMove(char[][] b2, int depth, int player) {
                this.board = b2;
                this.dpth = depth;
                this.p = player;

                // Five in a Row
                this.scoreDictionary.put("11111", 100000); 
                // Live Four
                this.scoreDictionary.put("11110", 100000); 
                this.scoreDictionary.put("11101", 100000); 
                this.scoreDictionary.put("11011", 100000);
                this.scoreDictionary.put("10111", 100000);
                this.scoreDictionary.put("01111", 100000);
                // Potential Four Three
                this.scoreDictionary.put("011100", 5000);
                this.scoreDictionary.put("001110", 5000);
                this.scoreDictionary.put("010110", 5000);
                this.scoreDictionary.put("011010", 5000);
                // Live Three
                this.scoreDictionary.put("00111", 1000);
                this.scoreDictionary.put("01011", 1000);
                this.scoreDictionary.put("01110", 1000);
                this.scoreDictionary.put("01101", 1000);
                this.scoreDictionary.put("10011", 1000);
                this.scoreDictionary.put("10110", 1000);
                this.scoreDictionary.put("10101", 1000);
                this.scoreDictionary.put("11001", 1000);
                this.scoreDictionary.put("11010", 1000);
                this.scoreDictionary.put("11100", 1000);
                // Live Twos
                this.scoreDictionary.put("00011", 100);
                this.scoreDictionary.put("00110", 100);
                this.scoreDictionary.put("00101", 100);
                this.scoreDictionary.put("01001", 100);
                this.scoreDictionary.put("01100", 100);
                this.scoreDictionary.put("01010", 100);
                this.scoreDictionary.put("10001", 100);
                this.scoreDictionary.put("10010", 100);
                this.scoreDictionary.put("10100", 100);
                this.scoreDictionary.put("11000", 100);
                // Live Ones
                this.scoreDictionary.put("00001", 10);
                this.scoreDictionary.put("00010", 10);
                this.scoreDictionary.put("00100", 10);
                this.scoreDictionary.put("01000", 10);
                this.scoreDictionary.put("10000", 10);

                // --------------

                // Five in a Row
                this.scoreDictionary.put("22222", -100000); 
                // Live Four
                this.scoreDictionary.put("22220", -100000); 
                this.scoreDictionary.put("22202", -100000); 
                this.scoreDictionary.put("22022", -100000);
                this.scoreDictionary.put("20222", -100000);
                this.scoreDictionary.put("02222", -100000);
                // Potential Four Three
                this.scoreDictionary.put("022200", -5000);
                this.scoreDictionary.put("002220", -5000);
                this.scoreDictionary.put("020220", -5000);
                this.scoreDictionary.put("022020", -5000);
                // Live Three
                this.scoreDictionary.put("00222", -1000);
                this.scoreDictionary.put("02022", -1000);
                this.scoreDictionary.put("02202", -1000);
                this.scoreDictionary.put("02220", -1000);
                this.scoreDictionary.put("20022", -1000);
                this.scoreDictionary.put("20220", -1000);
                this.scoreDictionary.put("20202", -1000);
                this.scoreDictionary.put("22002", -1000);
                this.scoreDictionary.put("22020", -1000);
                this.scoreDictionary.put("22200", -1000);
                // Live Twos
                this.scoreDictionary.put("00022", -100);
                this.scoreDictionary.put("00220", -100);
                this.scoreDictionary.put("00202", -100);
                this.scoreDictionary.put("02002", -100);
                this.scoreDictionary.put("02200", -10);
                this.scoreDictionary.put("02020", -100);
                this.scoreDictionary.put("20002", -100);
                this.scoreDictionary.put("20020", -100);
                this.scoreDictionary.put("20200", -100);
                this.scoreDictionary.put("22000", -100);
                // Live Ones
                this.scoreDictionary.put("00002", -10);
                this.scoreDictionary.put("00020", -10);
                this.scoreDictionary.put("00200", -10);
                this.scoreDictionary.put("02000", -10);
                this.scoreDictionary.put("20000", -10);
                
            }

            // Current Board - Simulated board
            // Depth - depth
            // Alpha - Highest/Lowest Value from Previous Iteration
            // Player - 1 or 2
            public int miniMax(char[][] currentBoard, int depth, int alpha, int beta, int player, boolean maxPlayer) {

                // char[][] currentBoard = Arrays.copyOf(brd, 20);

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
                

                if (isLongGameOver(currentBoard) != -1) {
                    // Current Player wins
                    if (isLongGameOver(currentBoard) == p) return 200000;
                    // Opponent Wins
                    else return -200000;
                }

                // Base Case
                if (depth == 0) {
                    int score = calculateScore(b, tPlayer);
                    if (maxPlayer) return score;
                    else return -score;
                }

                // Playing all possible moves in current combination
                if (maxPlayer) {
                    int curMax = Integer.MIN_VALUE;
                    int curLongGameScore = longGameScore(b, tPlayer);
                    int miniMaxValue = curMax;
                    // double value = curMax;
                    for (int i = 0; i < 20; i++) {
                        for (int j = 0; j < 20; j++) {
                            if (currentBoard[i][j] == '.' && !checkNotValid(currentBoard, i, j))  {
                                // System.out.println(curMax + ": " + i + " " + j);
                                currentBoard[i][j] = tPlayer;
                                int simLongGameScore = longGameScore(b, tPlayer);
                                if (simLongGameScore > curLongGameScore) miniMaxValue = - (simLongGameScore - curLongGameScore) * 200000; 
                                else miniMaxValue = miniMax(currentBoard, depth - 1, alpha, beta, opponentPlayer, false);
                                // if (miniMaxValue >= 0) value = Math.pow(miniMaxValue;
                                //else value = - Math.pow(-miniMaxValue, depth + 10);
                                
                                curMax = Math.max(miniMaxValue, curMax);
                                alpha = Math.max(alpha, curMax);
                                currentBoard[i][j] = '.';
                                if (alpha >= beta) return curMax;
                            }
                        }
                    }
                    return curMax;
                } else {
                    int curMin = Integer.MAX_VALUE; // Getting the best move for itself
                    int curLongGameScore = longGameScore(b, tPlayer);
                    int miniMaxValue = curMin;
                    // double value = curMin;
                    for (int i = 0; i < 20; i++) {
                        for (int j = 0; j < 20; j++) {
                            if (currentBoard[i][j] == '.' && !checkNotValid(currentBoard, i, j)) {
                                // System.out.println(curMin + ": " + i + " " + j);
                                currentBoard[i][j] = tPlayer;
                                int simLongGameScore = longGameScore(b, player);
                                if (simLongGameScore > curLongGameScore) miniMaxValue = + (simLongGameScore - curLongGameScore) * 100000; 
                                else miniMaxValue = miniMax(currentBoard, depth - 1, alpha, beta, opponentPlayer, false);
                                // if (miniMaxValue >= 0) value = Math.pow(miniMaxValue, depth + 10);
                                // else value = - Math.pow(-miniMaxValue, depth + 10);
                                // System.out.println(value);
                                curMin = Math.min(miniMaxValue, curMin);
                                beta = Math.min(beta, curMin);
                                currentBoard[i][j] = '.';
                                if (beta <= alpha) return curMin;
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
                    // System.out.println(stringArray);
                    String stringArray2 = stringArray.replaceAll("11111", ""); 
                    stringArray2.replaceAll("22222", "");
                        for (String pattern : scoreDictionary.keySet()) {
                            c = stringArray2.split(pattern, -1).length-1; 
                            if (c>0) {
                                // System.out.println(pattern + " " + checkString);
                                score += c * scoreDictionary.get(pattern);
                            }
                        }
                    }
                
                /*
                for (String stringArray : rv) {
                    // Five
                    if (stringArray.contains("11111")) score += 100000; 
                    // Live Four
                    if (stringArray.contains("011110")) score += 15000; 
                    // Live Three, Dead four
                    if ((stringArray.contains("00111") || stringArray.contains("01011") || stringArray.contains("01101") || stringArray.contains("01110") || stringArray.contains("10110") || stringArray.contains("11100") || stringArray.contains("11001") || stringArray.contains("11010"))
                        || stringArray.contains("11110") || stringArray.contains("11101") || stringArray.contains("11011") || stringArray.contains("10111") || stringArray.contains("01111"))
                         score += 10000; 
                    // Two Live Threes
                    int n = 0;
                    if (stringArray.contains("00111")) n += 1;
                    if (stringArray.contains("01011")) n += 1;
                    if (stringArray.contains("01101")) n += 1;
                    if (stringArray.contains("01110")) n += 1;
                    if (stringArray.contains("10110")) n += 1;
                    if (stringArray.contains("11100")) n += 1;
                    if (stringArray.contains("11001")) n += 1;
                    if (stringArray.contains("11010")) n += 1;
                    if (n > 1) score += 5000;
                    
                    // Dead four
                    if (stringArray.contains("11110") || stringArray.contains("11101") || stringArray.contains("11011") || stringArray.contains("10111") || stringArray.contains("01111")) 
                        score += 1000; 

                    // ------------------ //
                    /*
                    // Five
                    if (stringArray.contains("22222")) score -= 100000; 
                    // Live Four
                    if (stringArray.contains("022220")) score -= 15000; 
                    // Live Three, Dead four
                    if ((stringArray.contains("00222") || stringArray.contains("02022") || stringArray.contains("02202") || stringArray.contains("02220") || stringArray.contains("20220") || stringArray.contains("22200") || stringArray.contains("22002") || stringArray.contains("22020"))
                        || stringArray.contains("22220") || stringArray.contains("22202") || stringArray.contains("22022") || stringArray.contains("20222") || stringArray.contains("02222"))
                            score -= 10000; 
                    // Two Live Threes
                    n = 0;
                    if (stringArray.contains("00222")) n += 1;
                    if (stringArray.contains("02022")) n += 1;
                    if (stringArray.contains("02202")) n += 1;
                    if (stringArray.contains("02220")) n += 1;
                    if (stringArray.contains("20220")) n += 1;
                    if (stringArray.contains("22200")) n += 1;
                    if (stringArray.contains("22002")) n += 1;
                    if (stringArray.contains("22020")) n += 1;
                    if (n > 2) score -= 5000;
                    
                    // Dead four
                    if (stringArray.contains("22220") || stringArray.contains("22202") || stringArray.contains("22022") || stringArray.contains("20222") || stringArray.contains("02222")) 
                        score -= 1000; 
                    
                }  
                */

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
                int curMax = Integer.MIN_VALUE; // Getting the best move for itself
                int alpha = Integer.MIN_VALUE;
                int beta = Integer.MAX_VALUE;
                int[] moves = new int[] {10, 10};
                for (int i = 0; i < 20; i++) {
                    for (int j = 0; j < 20; j++) {
                        if (board[i][j] == '.' && !checkNotValid(board, i, j))  {   
                            // System.out.println(i + " " + j);
                            // for (int h = 0; h < 20; h++) { for (int k = 0; k < 20; k++) System.out.print(board[h][k] + " "); System.out.println(); }
                            board[i][j] = tPlayer;
                            int miniMaxValue = miniMax(board, dpth, alpha, beta, op, false);
                            if (miniMaxValue > curMax) {
                                curMax = miniMaxValue;
                                beta = curMax;
                                moves = new int[] {i, j};
                            }
                            board[i][j] = '.';
                        }
                    }
                }
                return moves;
            }
        }

        BestMove findBestMove = new BestMove(b, 1, player);
        int[] move = findBestMove.calculatingBestMove();
        
        return move;
    }

public int longGameScore(char[][] b, char tPlayer) {
    return 0;
}

public int isLongGameOver(char[][] currentBoard) {
    return 0;
}
}