import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class FPTester {
    
    public static void main(String[] args) {

        String name = "";
        if (args.length == 0) {
            name = "TanishFP";
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
            e.printStackTrace();
        }

        char[][] board = {
            {'X','O','X','.','X','O','.','.','X','X','.','X','X','X','.','X','X','.','X','O'},
            {'X','O','X','.','X','O','.','.','X','X','.','X','X','X','.','X','O','.','X','O'},
            {'X','O','X','.','X','O','X','.','X','X','.','X','X','X','.','X','X','O','X','O'},
            {'X','O','X','.','X','O','.','.','X','X','.','X','X','X','.','X','X','.','O','O'},
            {'X','O','X','.','X','O','.','.','X','X','.','X','X','X','.','X','X','.','X','O'},
            {'X','O','X','.','X','O','.','.','O','X','.','X','X','X','.','X','X','.','X','O'},
            {'X','O','X','.','X','O','.','.','X','X','.','X','X','X','.','X','X','.','X','O'},
            {'X','O','X','.','X','O','.','.','X','X','.','X','X','X','.','X','X','.','X','O'},
            {'X','O','X','.','X','O','.','.','X','X','.','X','X','X','.','X','X','.','X','O'},
            {'X','O','X','.','X','O','.','.','X','O','.','X','X','X','.','X','X','.','X','O'},
            {'X','O','X','.','X','O','.','.','X','X','.','X','X','X','.','X','X','.','X','O'},
            {'X','O','X','.','X','O','.','.','X','X','.','X','X','X','.','X','X','.','X','O'},
            {'X','O','X','.','X','O','X','.','X','X','.','X','X','X','.','X','X','.','X','O'},
            {'X','O','X','.','X','O','.','.','X','X','.','X','X','X','.','X','X','.','X','O'},
            {'X','O','X','.','X','O','.','.','X','X','.','X','X','X','.','X','X','.','X','O'},
            {'O','O','X','.','X','O','.','.','X','X','.','X','X','X','.','X','X','.','X','O'},
            {'X','O','X','.','X','O','X','.','X','X','.','X','X','X','.','X','X','.','X','O'},
            {'X','.','O','O','X','O','.','.','X','X','.','X','X','X','.','X','X','.','X','O'},
            {'X','O','X','O','X','O','.','.','X','X','.','X','X','X','.','X','X','.','X','O'},
            {'X','O','X','.','O','O','.','.','X','X','.','X','X','X','.','X','X','.','X','O'}};


        char[][] board2 = new char[20][20];

        
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                board2[i][j] = '.';
            }
        }
        
        
        // FinalProject tanishTest = new TanishFP();
        // tanishTest.playShortGame(board2, 0);

        String inpmv = "";
        Scanner scan = new Scanner(System.in);
        while (inpmv != "exit") {
            int[] mv = fp.playShortGame(board2, 1);
            board2[mv[0]][mv[1]] = 'X';
            System.out.println("   1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 17 18 19 20");
            char[] alphs = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T'};

            for (int i = 0; i < 20; i++) {
                System.out.print(alphs[i]+"  ");
                for (int j = 0; j < 20; j++) {
                    System.out.print(board2[i][j]+"  ");
                }
                System.out.println(alphs[i]);
            }
            System.out.println("   1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 17 18 19 20");
            inpmv = scan.nextLine();
            board2 = playMove(board2, inpmv);
        }
    }

    public static char[][] playMove(char[][] b, String mv) {
        
        int r = (int) mv.charAt(0) - 'A';
        int c = Integer.parseInt(mv.substring(1))-1;
        b[r][c] = 'O';
        return b;
    }

}
