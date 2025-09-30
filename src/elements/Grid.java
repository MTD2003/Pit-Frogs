package elements;

public class Grid {
    private final int size;
    private final int actors;
    private Space[][] spaces;
    private Player[] player;

    public Grid() {
        size = 4;
        actors = 2;
       
        buildGrid();
    }

    public Grid(int size) {
        this.size = size;
        actors = 2;
        
        buildGrid();
    }

    public Grid(int actors, int size) {
        this.size = size;
        this.actors = actors;
        
        buildGrid();
    }

    private void buildGrid() {
        spaces = new Space[size][size];
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                spaces[i][j] = new Space(i, j);
            }
        }
        addPlayers();
    }
    
    // Creating and placing player objects.
    private void addPlayers() {
        int j = 0;
        player = new Player[actors];
        for(; j < 2; j++) { // Can guarantee min 2 players by game rules.
            int xy = (size - 1) * j;
            player[j] = new Player(xy, xy);
            spaces[xy][xy].setBlocked(true);
        }
        for(; j < actors; j++) {
            int x = (size - 1) * (j % 2);
            int y = (size - 1) * (j % 3);
            player[j] = new Player(x, y);
            spaces[x][y].setBlocked(true);
        }
    }

    // Redo this when you do the GUI.
    public boolean playerMove(int turn, int inp, boolean makePit) {
        int[] move = {0, 0}; // [0] = x, [1] = y
        int[] ppos = player[turn].getPos();
        
        switch(inp) {
            case 9:
                move[0] = 1;
                move[1] = -1;
                break;
            case 8:
                move[0] = 0;
                move[1] = -1;
                break;
            case 7:
                move[0] = -1;
                move[1] = -1;
                break;
            case 6:
                move[0] = 1;
                move[1] = 0;
                break;
            case 4:
                move[0] = -1;
                move[1] = 0;
                break;
            case 3:
                move[0] = 1;
                move[1] = 1;
                break;
            case 2:
                move[0] = 0;
                move[1] = 1;
                break;
            case 1:
                move[0] = -1;
                move[1] = 1;
        }
        ppos[0] += move[0];
        ppos[1] += move[1];
        
        // Moves player in direction.
        if(tryPos(ppos)) {
            int xLast = ppos[0] - move[0];
            int yLast = ppos[1] - move[1];
            
            spaces[ppos[0]][ppos[1]].setBlocked(true);
            if(makePit)
                spaces[xLast][yLast] = new Pit(xLast, yLast);
            else
                spaces[xLast][yLast].setBlocked(false);
            player[turn].setPos(ppos);

            return true;
        }

        return false;
    }

    private boolean tryPos(int[] newPos) {
        for(int xy = 0; xy <= 1; xy++) {
            if(newPos[xy] >= size || newPos[xy] < 0) {
                return false;
            }
        }
        
        Space jumpedTo = spaces[newPos[0]][newPos[1]];
        return (!jumpedTo.isBlocked()); // Returns false if space cannot be jumped to.
    }

    public int actorsLeft() {
        return actors;
    }

    public String toString() {
        String output = "";
        for(int y = 0; y < size; y++) { // Y-Axis should be vertical.
            for(int x = 0; x < size; x++) { // X-Axis should print out on same line.
                output += "[" + spaces[x][y] + "] ";
            }
            output += "\n";
        }

        return output;
    }
}