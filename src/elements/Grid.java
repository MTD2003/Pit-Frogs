package elements;
import utilities.GridConsts;

import java.util.ArrayList;

public class Grid {
    private final int size;
    private final int actors;
    // private final int teams;
    
    private ArrayList<Selection> moves;
    private Space[][] spaces;
    private Player[] player;

    public Grid() {
    	this(2, 6);
    }

    public Grid(int size) {
    	this(2, size);
    }

    public Grid(int actors, int size) {
        this.size = size;
        this.actors = actors;
        
        moves = new ArrayList<Selection>();
        buildGrid();
    }

    private void buildGrid() {
        spaces = new Space[size][size];
        for(int x = 0; x < size; x++) {
            for(int y = 0; y < size; y++)
                spaces[x][y] = new Space(x, y);
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
    
    public void playerMove(int index, int newX, int newY) {
    	int lastX = player[index].getX();
    	int lastY = player[index].getY();
    	
    	player[index].setPos(newX, newY);
    	if(spaces[newX][newY] instanceof Pit) // Results in player death.
    		player[index].kill();
    	else
    		spaces[newX][newY].setBlocked(true);
    	
    	if(player[index].getPit())
    		spaces[lastX][lastY] = new Pit(lastX, lastY);
    	else
    		spaces[lastX][lastY].setBlocked(false);
    	
    	player[index].togglePit();
    	clearMoves();
    }

    // Checks if a given position on the grid is valid.
    private boolean tryPos(int x, int y) {
    	if(x >= size || x < 0)
    		return false;
    	if(y >= size || y < 0)
    		return false;
    	
        return (!spaces[x][y].isBlocked()); // Returns false if space is blocked, true if it isn't.
    }
    
    // Generates player-adjacent selectables.
    // May be diagonal or horizontal.
    public void generateMoves(int index, int direction) {
    	int[] pos = { player[index].getX(), player[index].getY() };
    	int x, y;
    	
    	// Might seem weird to write a "if not" for this logic but it's to account for later game rules.
    	// If the direction input is neither diagonal OR orthogonal, performs both generations.
    	if(direction != GridConsts.DIAGONAL) {
    		int temp;
    		int addPos[] = {-1, 0};
    		
    		for(int xy = 0; xy < 4; xy++) { // negate -> swap -> negate -> swap pattern.
    			if(xy % 2 == 0) {
    				addPos[GridConsts.X] *= -1;
    				addPos[GridConsts.Y] *= -1;
    			} else {
    				temp = addPos[GridConsts.X];
    				addPos[GridConsts.X] = addPos[GridConsts.Y];
    				addPos[GridConsts.Y] = temp;
    			}
    			
    			x = pos[GridConsts.X] + addPos[GridConsts.X];
    			y = pos[GridConsts.Y] + addPos[GridConsts.Y];
    			
    			if(tryPos(x, y))
    				moves.add(new Selection(this, index, x, y));
    		}
    	}
    	
    	if(direction != GridConsts.ORTHOGONAL) { // Gens diagonal selections.
    		int newPos[] = {-1, -1};
            for(int xy = 0; xy < 4; xy++) {
                newPos[xy % 2] *= -1;
                x = pos[GridConsts.X] + newPos[GridConsts.X];
                y = pos[GridConsts.Y] + newPos[GridConsts.Y];
                
                if(tryPos(x, y))
                	moves.add(new Selection(this, index, x, y));
            }
    	}
    	
    	if(moves.isEmpty()) {
    		player[index].kill();
    		spaces[pos[GridConsts.X]][pos[GridConsts.Y]] = new Pit(pos[GridConsts.X], pos[GridConsts.Y]);
    	}
    }
    
    public void clearMoves() {
    	moves.clear();
    }
    
    // Compiles an ordered "draw list" for all assets on the grid.
    // Ordered according to object type (primary) and position (secondary).
    // Players are always placed above tiles.
    public ArrayList<Entity> getDrawList() {
    	ArrayList<Entity> drawList = new ArrayList<Entity>();
    	
    	for(int y = 0; y < size; y++) {
    		for(int x = 0; x < size; x++)
    			drawList.add(spaces[x][y]);
    	}
    	for(Player p : player)
    		drawList.addLast(p);
    	for(Selection s : moves)
    		drawList.addLast(s);
    	
    	return drawList;
    }
    
    public int getMovesNum() {
    	return moves.size();
    }
    
    public Selection getMoveAt(int index) {
    	return moves.get(index);
    }
    
    public Player getPlayerAt(int index) {
    	return player[index];
    }
    
    public boolean getPlayerStatusAt(int index) {
    	return player[index].isActive();
    }
    
    // Gets the next alive player for the turn count.
    public int getNextAlive(int current) {
    	for(int count = 0; count < actors; count++) {
    		current = (current + 1) % actors;
    		if(player[current].isActive())
    			return current;
    	}
    	
    	return 0; // Failsafe return, shouldn't occur.
    }
    
    public int getActors() {
        return actors;
    }
    
    public int getSize() {
    	return size;
    }
}