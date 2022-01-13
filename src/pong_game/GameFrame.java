
package pong_game;

import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame{

	GamePanel myGamePanel;
	
	GameFrame(){
		myGamePanel = new GamePanel();
		this.add(myGamePanel);
		this.setTitle("Pong Game");
		this.setResizable(false);
		this.setBackground(Color.white);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
}
