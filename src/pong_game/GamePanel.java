
package pong_game;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable{

	static final int GAME_WIDTH = 1000;
	static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555));
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);
	static final int BALL_DIAMETER = 20;
	static final int PADDLE_WIDTH = 25;
	static final int PADDLE_HEIGHT = 100;
	Thread gThread;
	Image gImage;
	Graphics graphics;
	Random rand1;
	Rackets p1;
	Rackets p2;
	Ball gBall;
	ScoreKeeper gScore;
	
	GamePanel(){
		createGPaddles();
		createGBall();
		gScore = new ScoreKeeper(GAME_WIDTH,GAME_HEIGHT);
		this.setFocusable(true);
		this.addKeyListener(new KeyChecker());
		this.setPreferredSize(SCREEN_SIZE);
		
		gThread = new Thread(this);
		gThread.start();
	}
	public void createGPaddles() {
		p1 = new Rackets(0,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
		p2 = new Rackets(GAME_WIDTH-PADDLE_WIDTH,
		(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2)
		,PADDLE_WIDTH,PADDLE_HEIGHT,2);
	}
	
	public void createGBall() {
		rand1 = new Random();

		gBall = new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2),
		rand1.nextInt(GAME_HEIGHT-BALL_DIAMETER),
		BALL_DIAMETER,BALL_DIAMETER);
	}
	
	public void paint(Graphics g) {
		gImage = createImage(getWidth(),getHeight());
		graphics = gImage.getGraphics();
		draw(graphics);
		g.drawImage(gImage,0,0,this);
	}

	public void move() {
		p1.move();
		p2.move();
		gBall.move();
	}

	public void draw(Graphics g) {
		p1.draw(g);
		p2.draw(g);
		gBall.draw(g);
		gScore.draw(g);
		Toolkit.getDefaultToolkit().sync(); 

	}
	
	public void checkCollision() {
		if(gBall.y < 1) {
			gBall.setYDirection(-gBall.speedY);
		}
		if(gBall.y >= GAME_HEIGHT-BALL_DIAMETER) {
			gBall.setYDirection(-gBall.speedY);
		}
		if(gBall.intersects(p1)) {
			gBall.speedX = Math.abs(gBall.speedX);
			gBall.speedX = gBall.speedX + 1; 
			if(gBall.speedY > 0){
				gBall.speedY = gBall.speedY + 1;
			} 
			else{
				gBall.speedY = gBall.speedY - 1;
			}
			gBall.setXDirection(gBall.speedX);
			gBall.setYDirection(gBall.speedY);
		}
		if(gBall.intersects(p2)) {
			gBall.speedX = Math.abs(gBall.speedX);
			gBall.speedX = gBall.speedX + 1; 
			if(gBall.speedY > 0){
				gBall.speedY = gBall.speedY + 1; 
			}
			else{
				gBall.speedY--;
			}
			gBall.setXDirection(-gBall.speedX);
			gBall.setYDirection(gBall.speedY);
		}
		if(p1.y < 1 )
			p1.y = 0;
		if(p1.y >= (GAME_HEIGHT-PADDLE_HEIGHT))
			p1.y = GAME_HEIGHT-PADDLE_HEIGHT;
		if(p2.y < 1)
			p2.y = 0;
		if(p2.y >= (GAME_HEIGHT-PADDLE_HEIGHT))
			p2.y = GAME_HEIGHT-PADDLE_HEIGHT;
		if(gBall.x < 1) {
			gScore.player2 = gScore.player2 + 1;
			createGPaddles();
			createGBall();
		}
		if(gBall.x >= GAME_WIDTH-BALL_DIAMETER) {
			gScore.player1 = gScore.player1 + 1;
			createGPaddles();
			createGBall();
		}
	}
	public void run() {
		long lastTime = System.nanoTime();
		double ticks =60.0;
		double ns = 1000000000 / ticks;
		double delta = 0;

		while(true) {

			long current = System.nanoTime();
			delta = delta + (current -lastTime)/ns;
			lastTime = current;

			if(0 < delta) {
				move();
				checkCollision();
				repaint();
				delta = delta - 1;
			}
		}
	}
	public class KeyChecker extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			p1.keyPressed(e);
			p2.keyPressed(e);
		}
		public void keyReleased(KeyEvent e) {
			p1.keyReleased(e);
			p2.keyReleased(e);
		}
	}
}
