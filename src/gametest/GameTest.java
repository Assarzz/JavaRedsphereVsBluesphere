package gametest;
import se.egy.graphics.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Console;
import java.util.*;
import java.awt.*;
import java.awt.Toolkit;

public class GameTest implements KeyListener {
	
	static boolean gameRunning = true;
	static GameScreen gameScreen = new GameScreen("Test", 1280, 960, false);
	static HashMap<String, Boolean> keyMap = new HashMap<String, Boolean>();
	

	
	public GameTest() {
    	this.gameScreen.setKeyListener(this);
		keyMap.put("w", false);
		keyMap.put("a", false);
		keyMap.put("s", false);
		keyMap.put("d", false);
    	
    	startGame();
	}
	
	public void startGame() {
		
    	ArrayList<Drawable> icons = new ArrayList<Drawable>();
    	
    	Random random = new Random();
    	//Image arrow = Toolkit.getDefaultToolkit().getImage("ArrowForSchoolGame.png");
    	Entity blueBall = new Entity(500, 100, "/bluesmuggBall.png", 50, 50);
    	Entity redBall = new Entity(100, 160, "/redAngryBall.png", 50, 50);
		icons.add(blueBall);
		icons.add(redBall);
    	while(gameRunning) {
    		gameScreen.render(icons); // renderar bilden
    		
    		blueBallMove(blueBall, keyMap, 10);
    		redBallMove(redBall, blueBall, 15);
    		if (CollisionBetweenSpheres(blueBall.getX(), blueBall.getY(), redBall.getX(), redBall.getY(), blueBall.getHeight()/2, redBall.getHeight()/2)) {
    			gameRunning = false;
    		}
    		handleBlueBallOutOfBounds(blueBall, gameScreen);
    		
        	try{ Thread.sleep(20);}catch(Exception e){}; // pausar i 20 ms
 
        	
    	}
    	System.exit(0);
		
	}
	
	public static void main(String[] args) {
		new GameTest();


		
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyPressed(KeyEvent e) {
		char pressedChar =  Character.toLowerCase(e.getKeyChar());
		keyMap.put(Character.toString(pressedChar), true);
		
		 
		
	}

	public void keyReleased(KeyEvent e) {
		char releasedChar =  Character.toLowerCase(e.getKeyChar());
		keyMap.put(Character.toString(releasedChar), false);
		
	}
	
	public static void blueBallMove(Entity ball, HashMap<String, Boolean> keyMap, int moveDistance ) {
		
		if (keyMap.get("w")) {
			ball.moveY(-moveDistance);
		}
		if (keyMap.get("s")) {
			ball.moveY(moveDistance);
		}
		if (keyMap.get("a")) {
			ball.moveX(-moveDistance);
		}
		if (keyMap.get("d")) {
			ball.moveX(moveDistance);
		}

	}
	public boolean CollisionBetweenSpheres(double s1_x, double s1_y, double s2_x, double s2_y, double s1_r, double s2_r) {
		
		if (Math.sqrt(Math.pow(Math.abs(s1_x-s2_x), 2)+ Math.pow(Math.abs(s1_y-s2_y), 2))> s1_r+s2_r ) {
			//igen collision
			return false;
		}
		else {
			return true;
		}
	}
	
	public void redBallMove(Entity redBall, Entity blueBall, double moveDistance) {
		
			double rbX =  redBall.getX();
			double rbY = redBall.getY();
			
			double bbX =  blueBall.getX();
			double bbY = blueBall.getY();
			
			
			double absX = Math.abs(rbX-bbX);
				
			if (absX != 0) {
				double c =  Math.sqrt(Math.pow(Math.abs(rbX-bbX), 2)+Math.pow(Math.abs(rbY-bbY), 2));
				redBall.moveX((-(rbX-bbX)/c)*moveDistance);
				redBall.moveY((-(rbY-bbY)/c)*moveDistance);
			}
			else {
				
			}

		}
	
	public void handleBlueBallOutOfBounds(Entity bBall, GameScreen localGameScreen ) {

		if (bBall.getX() > localGameScreen.getWidth()) {
			bBall.moveX(-localGameScreen.getWidth());
		}
		else if (bBall.getX() < 0) {
			bBall.moveX(localGameScreen.getWidth());

		}
		
		if (bBall.getY() > localGameScreen.getHeight()) {
			bBall.moveY(-localGameScreen.getHeight());
		}
		else if (bBall.getY() < 0) {
			bBall.moveY(localGameScreen.getHeight());

		}
	}
}

