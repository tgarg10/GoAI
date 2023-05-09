import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * This searches the current directory (where this Java file lives) for
 * files ending in "FP.java" and plays them head-to-head against each other.
 */

public class GraderH2H {
    private DSArrayList<FinalProject> allFinalProjects;
    private DSArrayList<String> allFinalProjectNames;
    private String[][] oneResults; // Results of single head-to-head games
    private int[][][] manyResults; // Results of many head-to-head games
    char[][] board;
    private String longOrShort; // From GraderGUI
    private int globalTimer;

    public static void main(String[] args) {
        GraderH2H g = new GraderH2H();
        g.init();
        //g.playOneGameH2H("short");
        //g.playOneGameH2H("long");
        //g.playManyH2H();
        g.playBracket("short");
    }

    public void init() {
        allFinalProjects = new DSArrayList<>();
        allFinalProjectNames = new DSArrayList<>();

        File folder = new File(".");
        File[] listOfFiles = folder.listFiles();
        allFinalProjects = new DSArrayList<>();
        allFinalProjectNames = new DSArrayList<>();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile() && listOfFiles[i].getName().endsWith("FP.java")) {
                // System.out.println("File " + listOfFiles[i].getName());
                try {
                    String name = listOfFiles[i].getName();
                    String[] nameParts = name.split("\\.");
                    FinalProject fp = (FinalProject) Class.forName(nameParts[0]).getDeclaredConstructor().newInstance();
                    allFinalProjects.add(fp);
                    allFinalProjectNames.add(nameParts[0]);
                } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                        | InvocationTargetException | NoSuchMethodException | SecurityException
                        | ClassNotFoundException e) {
                    e.printStackTrace();
                }

            } else if (listOfFiles[i].isDirectory()) {
                // System.out.println("Directory " + listOfFiles[i].getName());
            }
        }
        System.out.println("Competitors: " + allFinalProjectNames);
    }

    public void playManyH2H(String lOrS) {
        int numRounds = 100;
        int numPlayers = allFinalProjects.length();
        System.out.println("Number of players: " + numPlayers);

        // array of results
        manyResults = new int[numPlayers][numPlayers][3];

        for (int game1 = 0; game1 < numPlayers; game1++) {
            String Player1Name = allFinalProjectNames.get(game1);
            for (int game2 = 0; game2 < numPlayers; game2++) {
                String Player2Name = allFinalProjectNames.get(game2);
                System.out.printf("Playing %-15s vs. %-15s %d times: ", Player1Name, Player2Name, numRounds);
                int[] record = new int[4];
                this.globalTimer = 0;
                for (int i = 0; i < numRounds; i++) {
                    int winner;
                    if (lOrS.equals("short"))
                        winner = playShort(game1, game2);
                    else
                        winner = playLong(game1, game2);
                    record[winner]++;
                }
                record[3] = this.globalTimer;
                System.out.println(Arrays.toString(record));
                manyResults[game1][game2] = record;
            }
        }
    }


    public void playOneGameH2H(String lOrS) {
        this.longOrShort = lOrS;
        int numGames = allFinalProjects.length();
        oneResults = new String[numGames][numGames]; // For the GUI

        System.out.println("Number of players: " + numGames);
        for (int game1 = 0; game1 < numGames; game1++) {
            String Player1Name = allFinalProjectNames.get(game1);
            for (int game2 = 0; game2 < numGames; game2++) {
                String Player2Name = allFinalProjectNames.get(game2);
                System.out.println("Playing " + Player1Name + " vs. " + Player2Name + ". " + lOrS + " game.");
                int winner = lOrS.equals("short") ? playShort(game1, game2) : playLong(game1, game2);
                String winnerName = allFinalProjectNames.get(winner == 1 ? game1 : game2);
                if (winner == 0)
                    winnerName = "Tie.";
                oneResults[game1][game2] = drawBoard(this.board, winnerName);
            }
        }
    }

    /**
     * Build a random bracket and play the binary-ish tree
     */
    private void playBracket(String lors) {
        this.longOrShort = lors; 
        int numGames = allFinalProjects.length();
        DSArrayList<Integer> survivors = new DSArrayList<>();
        int maxNumGames = 30; // Tie after this many games
        int threshold = 5; // Win by this many games
        int numRounds = 1; // To stop when there's no clear winner.

        // Add all indices to survivors
        for(int i = 0; i < numGames; i++) survivors.add(i);

        System.out.println("Playing Bracketed Tournament");

        while(survivors.length() > 1 && numRounds < 10){
            System.out.printf("Round %d has %d players.\n", numRounds, survivors.length());

            // Build pairings by shuffling the survivors
            survivors.shuffle();
            DSArrayList<Integer> losers = new DSArrayList<>();
            
            // Play the pairs and kill the losers
            for(int i = 0; i < survivors.length() / 2; i++){
                int game1 = survivors.get(2*i);
                int game2 = survivors.get(2*i+1);
                String Player1Name = allFinalProjectNames.get(game1);
                String Player2Name = allFinalProjectNames.get(game2);
                int[] record = playBracketSeries(lors, game1, game2, maxNumGames, threshold);
                System.out.printf("%s as Player 1: (%d, %d, %d), %s as Player 1: (%d, %d, %d)\n", 
                    Player1Name, record[1], record[2], record[0], Player2Name, record[5], record[6], record[4]);
                int p1wins = record[1] + record[6];
                int p2wins = record[2] + record[5];
                if(p1wins >= p2wins + threshold){
                    losers.add(game2);
                    System.out.println("---" + Player2Name + " has been eliminated");
                }
                if(p2wins >= p1wins + threshold){
                    losers.add(game1);
                    System.out.println("---" + Player1Name + " has been eliminated");
                }
            }
            if(survivors.length() % 2 == 1){
                System.out.println("+++" + allFinalProjectNames.get(survivors.get(survivors.length() - 1)) + " gets a Bye");
            }

            // Remove the losers
            for(int i = 0; i < losers.length(); i++) survivors.removeItem(losers.get(i));
            System.out.println("");

            // Count rounds
            numRounds++;
        }

    }

    /**
     * Returns [P1 wins with game1 as P1, P2 wins with game1 as P1, Ties with game1 as P1, time, ...P2...]
     * @param game1
     * @param game2
     * @param maxNumGames
     * @param threshold
     * @return
     */
    private int[] playBracketSeries(String lOrS, int game1, int game2, int maxNumGames, int threshold) {
        int[] rv = new int[8];
        int p1 = 0, p2 = 0;

        for (int i = 0; i < maxNumGames; i++) {
            if(i % 2 == 0){
                p1 = game1;
                p2 = game2;
            } else {
                p1 = game2;
                p2 = game1;
            }
            int winner;
            if (lOrS.equals("short"))
                winner = playShort(p1, p2);
            else
                winner = playLong(p1, p2);
            rv[4*(i%2) + winner]++;

            // See if we should terminate early
            int p1wins = rv[1] + rv[6];
            int p2wins = rv[2] + rv[5];
            if(Math.abs(p1wins - p2wins) >= threshold) break;
        }

        return rv;
    }


    /**
     * Play a single game between two players, with g1 going first.
     * 
     * @param g1 The index of the first player
     * @param g2 The index of the second player
     * 
     * @return 0 for a Tie, 1 if g1 wins, 2 if g2 wins. -1 if the game is not over.
     * 
     *         Note that as a side effect, this fills the global board[][] array.
     */
    private int playShort(int g1, int g2) {
        FinalProject game1 = allFinalProjects.get(g1);
        FinalProject game2 = allFinalProjects.get(g2);
        board = new char[20][20];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                board[i][j] = '.';
            }
        }

        int turn = 1;
        int[] move;
        while (isShortGameOver(board) == -1) {
            //System.out.println(drawBoard(board, "Spying"));
            long startTime = System.currentTimeMillis();
            if (turn == 1) {
                move = game1.playShortGame(board, turn);
            } else {
                move = game2.playShortGame(board, turn);
            }
            this.globalTimer += (int)(System.currentTimeMillis() - startTime);
            int row = move[0];
            int col = move[1];
            char c = board[row][col];
            if (c != 'X' && c != 'O') {
                board[row][col] = (turn == 1 ? 'X' : 'O');
            } else {
                // System.out.println("Player " + turn + " has made an ILLEGAL MOVE!");
            }
            turn = 3 - turn;
        }

        int winner = isShortGameOver(board);
        String winnerName = allFinalProjectNames.get(winner == 1 ? g1 : g2);
        if (winner == 0)
            winnerName = "Tie.";
        //System.out.println(drawBoard(board, winnerName));

        return winner;
    }


    /**
     * Play a single game between two players, with g1 going first.
     * 
     * @param g1 The index of the first player
     * @param g2 The index of the second player
     * 
     * @return 0 for a Tie, 1 if g1 wins, 2 if g2 wins.
     * 
     *         Note that as a side effect, this fills the global board[][] array.
     */
    private int playLong(int g1, int g2) {
        FinalProject game1 = allFinalProjects.get(g1);
        FinalProject game2 = allFinalProjects.get(g2);
        board = new char[20][20];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                board[i][j] = '.';
            }
        }

        int turn = 1;
        int[] move;
        while (isLongGameOver(board) == -1) {
            if (turn == 1) {
                move = game1.playLongGame(board, turn);
            } else {
                move = game2.playLongGame(board, turn);
            }
            int row = move[0];
            int col = move[1];
            char c = board[row][col];
            if (c != 'X' && c != 'O') {
                board[row][col] = (turn == 1 ? 'X' : 'O');
            } else {
                //System.out.println("Player " + turn + " has made an ILLEGAL MOVE!");
            }
            turn = 3 - turn;
        }

        int winner = isLongGameOver(board);
        String winnerName = allFinalProjectNames.get(winner == 1 ? g1 : g2);
        if (winner == 0)
            winnerName = "Tie.";
        //System.out.println(drawBoard(board, winnerName));

        return winner;
    }


    public String drawBoard(char[][] board, String winnerName) {
        int winner = this.longOrShort.equals("short") ? isShortGameOver(board) : isLongGameOver(board);
        String rv = String.format("Player %d (%s) has won the game\n", winner, winnerName);
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                rv = rv + board[i][j] + " ";
            }
            rv = rv + "\n";
        }
        return rv;
    }


    /**
     * Return 1 if Player 1 has won, 2 if Player 2 has won,
     * 0 if the game has ended in a tie, and -1 if the game is still going.
     * 
     * @param board the board
     * @return
     */
    public int isShortGameOver(char[][] board) {
        // This method was largely written by ChatGPT!
        // Check for five consecutive Xs or Os horizontally
        int hasXorO = -1;
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 16; j++) {
                if (board[i][j] == 'X' && board[i][j + 1] == 'X' && board[i][j + 2] == 'X' && board[i][j + 3] == 'X'
                        && board[i][j + 4] == 'X') {
                    hasXorO = 1;
                    break;
                } else if (board[i][j] == 'O' && board[i][j + 1] == 'O' && board[i][j + 2] == 'O'
                        && board[i][j + 3] == 'O'
                        && board[i][j + 4] == 'O') {
                    hasXorO = 2;
                    break;
                }
            }
            if (hasXorO != -1) {
                break;
            }
        }

        // Check for five consecutive Xs or Os vertically
        if (hasXorO == -1) {
            for (int i = 0; i < 16; i++) {
                for (int j = 0; j < 20; j++) {
                    if (board[i][j] == 'X' && board[i + 1][j] == 'X' && board[i + 2][j] == 'X' && board[i + 3][j] == 'X'
                            && board[i + 4][j] == 'X') {
                        hasXorO = 1;
                        break;
                    } else if (board[i][j] == 'O' && board[i + 1][j] == 'O' && board[i + 2][j] == 'O'
                            && board[i + 3][j] == 'O'
                            && board[i + 4][j] == 'O') {
                        hasXorO = 2;
                        break;
                    }
                }
                if (hasXorO != -1) {
                    break;
                }
            }
        }

        // Check for five consecutive Xs or Os diagonally
        if (hasXorO == -1) {
            for (int i = 0; i < 16; i++) {
                for (int j = 0; j < 16; j++) {
                    if (board[i][j] == 'X' && board[i + 1][j + 1] == 'X' && board[i + 2][j + 2] == 'X'
                            && board[i + 3][j + 3] == 'X'
                            && board[i + 4][j + 4] == 'X') {
                        hasXorO = 1;
                        break;
                    } else if (board[i][j] == 'O' && board[i + 1][j + 1] == 'O' && board[i + 2][j + 2] == 'O'
                            && board[i + 3][j + 3] == 'O' && board[i + 4][j + 4] == 'O') {
                        hasXorO = 2;
                        break;
                    } else if (board[i][j + 4] == 'X' && board[i + 1][j + 3] == 'X' && board[i + 2][j + 2] == 'X'
                            && board[i + 3][j + 1] == 'X' && board[i + 4][j] == 'X') {
                        hasXorO = 1;
                        break;
                    } else if (board[i][j + 4] == 'O' && board[i + 1][j + 3] == 'O' && board[i + 2][j + 2] == 'O'
                            && board[i + 3][j + 1] == 'O' && board[i + 4][j] == 'O') {
                        hasXorO = 2;
                        break;
                    }
                }
                if (hasXorO != -1) {
                    break;
                }
            }
        }

        if (boardIsFull(board))
            return 0;

        return hasXorO;

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


    public int isLongGameOver(char[][] b) {
        if(!boardIsFull(b)) return -1;

        int score1 = longGameScore(b, 1);
        int score2 = longGameScore(b, 2);
        if(score1 == score2) return 0;
        return score1 > score2 ? 1 : 2;
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


	// Getter
    public DSArrayList<FinalProject> getAllFinalProjects() {
        return allFinalProjects;
    }

    // Getter
    public DSArrayList<String> getAllFinalProjectNames() {
        return allFinalProjectNames;
    }

    // Getter
    public int[][][] getManyResults() {
        return manyResults;
    }

    // Getter
    public String[][] getOneResults() {
        return oneResults;
    }

    // Setter
    public void setLongOrShort(String longOrShort) {
        this.longOrShort = longOrShort;
    }

}