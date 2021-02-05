package client.clientpages;

import javax.swing.*;

import client.clientcontrollers.PostGameController;


import java.awt.*;
import java.awt.event.*;

public class PostGamePage extends JPanel {
	
	
	
	JLabel winner;
	public PostGamePage(PostGameController pgc) {
		
		JPanel page = new JPanel();
		page.setLayout(new GridLayout(3, 1, 0, 0));
		
		
		JPanel titlepanel = new JPanel();
		titlepanel.setLayout(new GridLayout(1,2,0,0));
		JLabel spades = new JLabel("Spades");
		spades.setFont(new Font("Vladimir Script", Font.PLAIN, 89));
		JLabel icon = new JLabel("");
		icon.setIcon(new ImageIcon(InitialPage.class.getResource("/cards_png_zip/resized/honors_spade-14.png")));
		
		titlepanel.add(spades);
		titlepanel.add(icon);
		page.add(titlepanel);
		
		JPanel winnerpanel = new JPanel();
		winnerpanel.setLayout(new GridLayout(1,1,0,0));
		winner = new JLabel("");
		winner.setHorizontalAlignment(SwingConstants.CENTER);
		winnerpanel.add(winner);
		page.add(winnerpanel);


		
		
		JPanel buttonpanel = new JPanel();
		buttonpanel.setLayout(new GridLayout(1,2,0,10));
		JButton quit = new JButton("Quit");
		quit.addActionListener(pgc);
		buttonpanel.add(quit);
		JButton playagain = new JButton("Play Again");
		playagain.addActionListener(pgc);
		buttonpanel.add(playagain);
		page.add(buttonpanel);
		
	
		
		this.add(page);
		
		
		
		
		
		
	}
	
	public void setWinner(String winnerstring) {
		
		winner.setText(winnerstring);
		
	}

}
