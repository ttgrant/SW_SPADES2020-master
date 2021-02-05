package client.clientpages;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.*;
import javax.swing.*;

import client.clientcontrollers.MainMenuController;

import java.awt.event.*;



public class MainMenuPage extends JPanel {
	 private JButton adminbutton;
	
	public MainMenuPage(MainMenuController mmc)
	  {
		JPanel titlepanel = new JPanel();
		titlepanel.setLayout(new GridLayout(1,2,1,0));
		JLabel mainmenu = new JLabel("Main Menu");
		mainmenu.setFont(new Font("Vladimir Script", Font.PLAIN, 50));
		JLabel icon = new JLabel("");
		icon.setIcon(new ImageIcon(InitialPage.class.getResource("/cards_png_zip/resized/honors_spade-14.png")));
		
		titlepanel.add(mainmenu);
		titlepanel.add(icon);
	    
	    
	    
	    // Create a panel for the buttons.
	    JPanel buttonPanel = new JPanel();
	    JButton playbutton = new JButton("Play");
	    playbutton.addActionListener(mmc);
	    adminbutton = new JButton("Admin Page");
	    adminbutton.addActionListener(mmc);  
	    JButton logoutbutton = new JButton("Logout");
	    logoutbutton.addActionListener(mmc);
	    buttonPanel.add(playbutton);
	    buttonPanel.add(adminbutton);
	    adminbutton.setEnabled(false);
	    buttonPanel.add(logoutbutton);

	    // Arrange the three panels in a grid.
	    JPanel grid = new JPanel(new GridLayout(2, 1, 0, 100));
	    grid.add(titlepanel);
	    grid.add(buttonPanel);
	    this.add(grid);
	    
	  }

	public void enableAdmin() {
		// TODO Auto-generated method stub
		adminbutton.setEnabled(true);
	}

}
