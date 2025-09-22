package elements;

public class Grid {
    private int size;
    private int actors;
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

    private void addPlayers() {
        int scaling = (size / (actors - 1)) - 1;
        System.out.println(scaling);
        player = new Player[actors];
        for(int i = 0; i < actors; i++) {
            player[i] = new Player(scaling * i, scaling * i);
            spaces[scaling * i][scaling * i] = player[i];
        }
    }

    public boolean playerMove(int turn, int inp, boolean makePit) {
        int[] move = {0, 0}; // [0] = x, [1] = y
        int[] ppos = player[turn].getPos(); // 
        
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
            
            spaces[ppos[0]][ppos[1]] =  spaces[xLast][yLast];
            if(makePit)
                spaces[xLast][yLast] = new Pit(xLast, yLast);
            else
                spaces[xLast][yLast] = new Space(xLast, yLast);
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
        return (jumpedTo.hitboxType() <= 2); // Returns false if space cannot be jumped to.
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