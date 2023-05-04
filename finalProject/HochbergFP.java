import java.util.Random;

public class HochbergFP implements FinalProject {

    @Override
    public int[] playShortGame(char[][] b, int player) {
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
