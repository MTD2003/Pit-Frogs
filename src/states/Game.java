package states;

import elements.Grid;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) throws Exception {
        Grid gameGrid = new Grid(9);

        Scanner scans = new Scanner(System.in);
        
        int i = 0;
        int input;
        /* Diagonal Movement Code -> Slightly more elegant than manual code.
        int move[] = {-1, -1};
        for(int xy = 0; xy < 4; xy++) {
            griddy[xy % 2] *= -1;
            System.out.println(griddy[0] + ", " + griddy[1]);
            makeClicker(move);
        }
        */

        do {
            System.out.print(gameGrid);
            System.out.println("Player: " + (i + 1));
            input = scans.nextInt();
            gameGrid.playerMove(i, input, false);
            System.out.print(gameGrid);
            input = scans.nextInt();
            gameGrid.playerMove(i, input, true);
            i = (i + 1) % 2;
            
        } while(input != 5);

    }
}