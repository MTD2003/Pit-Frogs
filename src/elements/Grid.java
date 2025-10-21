package elements;
import java.util.ArrayList;

import utilities.Constants;

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
        for(int x = 0; x < size; x++) {
            for(int y = 0; y < size; y++) {
                spaces[x][y] = new Space(x, y);
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
            player[j] = new Player(xy, xy , j);
            spaces[xy][xy].setBlocked(true);
        }
        for(; j < actors; j++) {
            int x = (size - 1) * (j % 2);
            int y = (size - 1) * ((j + 1) % 2);
            player[j] = new Player(x, y, j);
            spaces[x][y].setBlocked(true);
        }
    }

    // Redo this when you do the GUI.
    public boolean playerMove(int turn, int inp, boolean makePit) {
        int[] move = {0, 0}; // [0] = x, [1] = y
        int[] ppos = player[turn].getPos();
        
        switch(inp) {
            case 9:
                move[Constants.X] = 1;
                move[Constants.Y] = -1;
                break;
            case 8:
                move[Constants.X] = 0;
                move[Constants.Y] = -1;
                break;
            case 7:
                move[Constants.X] = -1;
                move[Constants.Y] = -1;
                break;
            case 6:
                move[Constants.X] = 1;
                move[Constants.Y] = 0;
                break;
            case 4:
                move[Constants.X] = -1;
                move[Constants.Y] = 0;
                break;
            case 3:
                move[Constants.X] = 1;
                move[Constants.Y] = 1;
                break;
            case 2:
                move[Constants.X] = 0;
                move[Constants.Y] = 1;
                break;
            case 1:
                move[Constants.X] = -1;
                move[Constants.Y] = 1;
        }
        ppos[Constants.X] += move[Constants.X];
        ppos[Constants.Y] += move[Constants.Y];
        
        // Moves player in direction.
        if(tryPos(ppos)) {
            int xLast = ppos[Constants.X] - move[Constants.X];
            int yLast = ppos[Constants.Y] - move[Constants.Y];
            
            spaces[ppos[Constants.X]][ppos[Constants.Y]].setBlocked(true);
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
        
        Space jumpedTo = spaces[newPos[Constants.X]][newPos[Constants.Y]];
        return (!jumpedTo.isBlocked()); // Returns false if space cannot be jumped to.
    }

    public int actorsLeft() {
        return actors;
    }
    
    // Compiles an ordered "draw list" for all assets on the grid.
    // Ordered according to object type (primary) and position (secondary).
    // Players are always placed above tiles.
    public ArrayList<Entity> getDrawList() {
    	ArrayList<Entity> drawList = new ArrayList<Entity>();
    	
    	for(int y = 0; y < size; y++) {
    		for(int x = 0; x < size; x++) {
    			drawList.add(spaces[x][y]);
    		}
    	}
    	
    	for(Player p : player) {
    		drawList.addLast(p);
    	}
    	
    	return drawList;
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