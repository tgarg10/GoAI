/**
 * Interface for everybody's final projects
 */

 public interface FinalProject {
    /**
     * Place a new piece on the board to win the Short Game
     * 'X' for Player 1, or 'O' for Player 2
     * 
     * @param b The board on which to make the move
     * @param player 1 for 'X' or 2 for 'O'
     * 
     * @return An int[2] array containing [row, column] of where player makes a move
     * 
     * rows and columns are indexed from 0
     * 
     * This method is intended to try to win the game by obtaining the first 5-in-a-row
     */
    public int[] playShortGame(char[][] b, int player);


    /**
     * Place a new piece on the board to win the Long Game
     * 'X' for Player 1, or 'O' for Player 2
     * 
     * @param b The board on which to make the move
     * @param player 1 for 'X' or 2 for 'O'
     * 
     * @return An int[2] array containing [row, column] of where player makes a move
     * 
     * rows and columns are indexed from 0
     * 
     * This method is intended to try to win the game by obtaining as many 5-in-a-rows
     * as possible.
     */
    public int[] playLongGame(char[][] b, int player);


    /**
     * Returns 0 if either no player has already won the game, 
     * or if there are no more moves possible on the board.
     * 
     * If someone has won the game, it returns 1 (X) or 2 (O) 
     * depending on which Player won.
     * 
     * @param b The board
     * @return The winner of the game, or 0 if neither player has won
     */
    public int isShortGameOver(char[][] b);


    /**
     * Returns 0 if either no player has already won the game, 
     * or if there are no more moves possible on the board.
     * 
     * If someone has won the game, it returns 1 (X) or 2 (O) 
     * depending on which Player won.
     * 
     * @param b The board
     * @return The winner of the game, or 0 if neither player has won
     */
    public int isLongGameOver(char[][] b);

 }
