
package pong_game;

import java.awt.*;
import java.util.*;

public class Ball extends Rectangle{

	Random randomRNG;
	int speedX;
	int speedY;
	int startingSpeed = 2;
	
	Ball(int x, int y, int width, int height){
		super(x,y,width,height);
		randomRNG = new Random();
		int randXDir = randomRNG.nextInt(2);

		if(randXDir == 0)
			randXDir = randXDir -1;
		setXDirection(randXDir*startingSpeed);
		
		int randYDir = randomRNG.nextInt(2);
		if(randYDir == 0)
			randYDir = randYDir -1;
		setYDirection(randYDir*startingSpeed);
		
	}
	
	public void setXDirection(int randXDir) {
		speedX = randXDir;
	}
	public void setYDirection(int randYDir) {
		speedY = randYDir;
	}
	public void draw(Graphics g) {
		g.setColor(Color.red);
		g.fillOval(x, y, height, width);
	}
	public void move() {
		x += speedX;
		y += speedY;
	}
}
