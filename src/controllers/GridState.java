package controllers;

import bots.MoveTuple;
import bots.NaiveAgent;
import elements.Entity;
import elements.Grid;
import elements.Selection;
import menu.MenuText;
import utilities.GridConsts;
import utilities.InputMirror;
import utilities.SpriteList;
import view.InteractBox;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GridState implements State {
	private ArrayList<InteractBox> hitboxes;
	private final Game gameObj;
	private final Grid gridObj;
	private MenuText countTurn, countPlayer, countTime;
	private MenuText tutorial;
	
	private static int playerPlanter = GridConsts.MIN_PLAYERS;
    private static int sizePlanter = GridConsts.MIN_SIZE + 1;
	private static int timePlanter = GridConsts.MAX_TIMER / 2;
	// Indicates the number, difficulty, and position of artificial agents.
	private static int botFlag[] = { GridConsts.NAIVE_BOT, GridConsts.NAIVE_BOT, GridConsts.NAIVE_BOT, GridConsts.NAIVE_BOT };
	
	private final int maxTime;
	private int timer, index, turn, movesLeft;
	private int gridScale, lastScale;
	private NaiveAgent bot; // Will change this up a little later.
	private boolean bSearched;
	
	// Outside objects may only construct an object with planter parameters.
	public GridState(Game gameObj) {
		this(gameObj, playerPlanter, sizePlanter, timePlanter);
	}
	
	private GridState(Game gameObj, int actors, int size, int maxTime) {
		this.gameObj = gameObj;
		this.maxTime = maxTime;
		
		gridObj = new Grid(actors, size);
		hitboxes = new ArrayList<InteractBox>();
		gridScale = 1; // Safety to prevent frame 1 crash.
		
		index = 0;
		movesLeft = 2;
		timer = 0;
		turn = 1;
		
		bot = new NaiveAgent(8);
		
		loadText();
	}

	public void step() {
		int timeLimit = maxTime * Game.UPS + 60; // 60 update time limit delay improves gamefeel.
    	timer++;
    	
    	if((!gridObj.getPlayerStatusAt(index)))
    		movesLeft = 0;
    	
    	if(botFlag[index] != GridConsts.HUMAN) {
    		if(movesLeft > 0 && gridObj.getPlayerStatusAt(index)) {
    			if(!bSearched) { // If search has not been completed.
    				int pX = gridObj.getPlayerAt(index).getX();
    				int pY = gridObj.getPlayerAt(index).getY();
    				bSearched = true;
    				
    				bot.setPosition(gridObj.getBitGrid());
    				bot.startSearch(pX, pY);
    			} else if(timer > 60) { // Adds a delay before applying moves.
    				MoveTuple move = bot.popMove();
    				
    				if(move.getEval() <= -1) {
    					gridObj.forceKill(index);
    					turnReset();
    					return;
    				}
    				
    				gridObj.playerMoveRaw(index, move.getX(), move.getY());
    				gridObj.forceID(index);
    				movesLeft--;
    				timer = 0;
    			}
    		} else {
    			turnReset();
    			bot.clearQueue(); // This will be conditional in the future.
    		}
    	
    	} else if(gridObj.getMovesNum() == 0) {
    		if(movesLeft > 0 && gridObj.getPlayerStatusAt(index)) {
	    		gridObj.generateMoves(index, movesLeft % 2);
	    		generateHitboxes();
	    		timer = 0;
	    		movesLeft--;
    		} else {
    			turnReset();
    		}
    	}
    	
    	inputProcessing();
    	
    	if(timer == timeLimit) {
    		randomMove();
    		timer = 0;
    	}
	}

	public void draw(Graphics g) {
		ArrayList<Entity> drawList = gridObj.getDrawList();
		
		findGridScale();
    	for(Entity e : drawList) {
    		BufferedImage sprite = gameObj.getSprite(e.getSprite(), e.getFrame());
    		int x = e.getX() * gridScale;
    		int y = e.getY() * gridScale;
    		
    		g.drawImage(sprite, x, y, gridScale, gridScale, null);
    	}
    	
    	updateHitboxes();
    	updateText();
    	
    	countTurn.selfDraw(g);
    	countPlayer.selfDraw(g);
    	countTime.selfDraw(g);
    	tutorial.selfDraw(g);
	}
	
	public void loadText() {
		int fontSize = 24;
		int x = 8;
		int y = Game.SCREEN_HEIGHT - fontSize;
		Font fontBasic = new Font("Consolas", Font.PLAIN, fontSize);
		countTurn = new MenuText("", x, y, fontSize, fontBasic);
		countPlayer = new MenuText("", x, y - fontSize, fontSize, fontBasic);
		countTime = new MenuText("", Game.SCREEN_WIDTH - fontSize * 4, y, fontSize * 3, fontBasic);
		
		tutorial = new MenuText("<Control with Mouse>", Game.SCREEN_WIDTH / 4 + 32, y - fontSize / 2, fontSize - 4, fontBasic);
	}
	
	// Will handle scaling later.
	public void updateText() {
		int truTime = maxTime - timer / Game.UPS;
		String strTime = String.format("%02d", truTime);
		
		countTurn.setText("Turn " + turn);
		countPlayer.setText("Player " + (index + 1));
		countTime.setText(strTime);
	}
	
	private void inputProcessing() {
		InputMirror inputs = gameObj.getInputMirror();
		int heldKey = inputs.getHeld(), lastKey = inputs.getKey();
    	int mouseX = inputs.getMouseX(), mouseY = inputs.getMouseY();
    	int mouseState = inputs.getMouseState();
    	int usedKey = (heldKey == InputMirror.KEY_EMPTY ? lastKey : InputMirror.KEY_EMPTY); // Only register a used key if held is empty.
    	
    	hoverCheck(mouseX, mouseY, heldKey, mouseState);
    	inputCheck(mouseX, mouseY, usedKey, mouseState);
	}
	
	// Checks for holding/hovering over interactboxes.
	private void hoverCheck(int mouseX, int mouseY, int heldKey, int mouseState) {
		boolean mouseHeld = (mouseState == InputMirror.MOUSE_HELD);
    	for(InteractBox ib : hitboxes)
    		ib.checkHold(mouseX, mouseY, heldKey, mouseHeld);
    }
	
	// Checks for a valid input and clears hitboxes if it is one.
    private void inputCheck(int mouseX, int mouseY, int usedKey, int mouseState) {
    	if(mouseState == InputMirror.MOUSE_CLICK) {
    		for(InteractBox ib : hitboxes) {
    			if(ib.checkHit(mouseX, mouseY)) {
    				hitboxes.clear();
    				return;
    			}
    		}
    	} else if(usedKey != InputMirror.KEY_EMPTY) {
    		for(InteractBox ib : hitboxes) {
    			if(ib.checkBind(usedKey)) {
    				hitboxes.clear();
    				return;
    			}
    		}
    	}
    }
    
    // Calculates gridScale based on Window and Grid size.
    public void findGridScale() {
    	int gridSize = gridObj.getSize();
    	int height = gameObj.getWindowHeight();
    	int width = gameObj.getWindowWidth();
    	int size = Math.min(width, height);
    	
    	lastScale = gridScale;
    	gridScale = (size - SpriteList.SPRITE_DIMENSIONS) / gridSize;
    }
    
    // Generates InteractBoxes for each Interactable.
    private void generateHitboxes() {
    	int x, y;
    	int totalMoves = gridObj.getMovesNum();
    	
    	for(int i = 0; i < totalMoves; i++) {
    		Selection move = gridObj.getMoveAt(i);
    		x = move.getX() * gridScale;
    		y = move.getY() * gridScale;
    		hitboxes.add(new InteractBox(move, x, y, gridScale, gridScale, move.calcKeyBind()));
    	}
    }
    
    private void updateHitboxes() {
    	for(InteractBox ib : hitboxes)
    		ib.updateScale(lastScale, gridScale);
    }
    
    private void randomMove() {
    	if(!hitboxes.isEmpty()) {
    		int move = (int)(Math.random() * hitboxes.size());
    		hitboxes.get(move).forceActivate();
    		
    		hitboxes.clear();
    	}
    }
    
    // Resets necessary conditions at the end of a player turn.
    private void turnReset() {
    	tutorial.setText("<Avoid the Pitfalls>"); // Maybe turn into a random tip at some point.
		index = gridObj.getNextAlive(index);
		movesLeft = 2;
		turn++;
		
		tryWinCondition();
		
		if(botFlag[index] != GridConsts.HUMAN) {
			bSearched = false;
			gridObj.forceID(index);
		}
    }
    
	private void tryWinCondition() {
    	if(index == gridObj.getNextAlive(index)) {
    		WinState winscreen = new WinState(gameObj, gridObj, gridScale, index);
    		gridObj.getPlayerAt(index).win();
    		gameObj.swapState(winscreen);
    	}
    }
	
	// Helper function for setting Grid Parameters.
	private static int checkMinMax(int value, int minVal, int maxVal) {
		return Math.min(maxVal, Math.max(value, minVal));
	}
	
	public static int getPlayerP() {
		return playerPlanter;
	}
	
	public static int getSizeP() {
		return sizePlanter;
	}
	
	public static int getTimeP() {
		return timePlanter;
	}
	
	public static void setPlayerP(int pp) {
		playerPlanter = checkMinMax(pp, GridConsts.MIN_PLAYERS, GridConsts.MAX_PLAYERS);
	}
	
	public static void setSizeP(int sp) {
		sizePlanter = checkMinMax(sp, GridConsts.MIN_SIZE, GridConsts.MAX_SIZE);
	}
	
	public static void setTimeP(int tp) {
		timePlanter = checkMinMax(tp, GridConsts.MIN_TIMER, GridConsts.MAX_TIMER);
	}
	
	public static void setBotFlag(int type, int target) {
		target = Math.min(3, Math.max(0, target));
		type = Math.min(GridConsts.SMART_BOT, Math.max(0, type));
		botFlag[target] = type;
	}
}