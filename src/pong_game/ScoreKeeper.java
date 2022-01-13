
package pong_game;

import java.awt.*;

public class ScoreKeeper extends Rectangle{
	
	int player1;
	int player2;
	static int wGame;
	static int hGame;

	
	ScoreKeeper(int x, int y){
		ScoreKeeper.wGame = x;
		ScoreKeeper.hGame = y;
	}
	public void draw(Graphics g) {
		g.setColor(Color.green);
		g.setFont(new Font("Arial",Font.PLAIN,50));
		
		g.drawLine(wGame/2, 0, wGame/2, hGame);
			
		g.drawOval(325, 103, 350, 350);
		
		g.drawString(String.valueOf(player1/10)+String.valueOf(player1%10), (wGame/2)-85, 50);
		g.drawString(String.valueOf(player2/10)+String.valueOf(player2%10), (wGame/2)+20, 50);
	}
}
