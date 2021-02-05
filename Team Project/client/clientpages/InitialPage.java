package client.clientpages;

import java.awt.*;

import javax.swing.*;

import client.clientcontrollers.InitialPageController;

import java.awt.event.*;

public class InitialPage extends JPanel {

	
	public InitialPage(InitialPageController ipc) {
		
		JPanel page = new JPanel();
		page.setLayout(new GridLayout(2,1,0,150));
		
		JPanel titlepanel = new JPanel();
		titlepanel.setLayout(new GridLayout(1,2,1,1));
		JLabel spades = new JLabel("Spades");
		spades.setFont(new Font("Vladimir Script", Font.BOLD, 50));
		titlepanel.add(spades);
		
		
		JLabel icon = new JLabel("");
		titlepanel.add(icon);
		icon.setIcon(new ImageIcon(InitialPage.class.getResource("/cards_png_zip/resized/honors_spade-14.png")));
		page.add(titlepanel);
		
		JPanel buttonpanel = new JPanel();
		JButton start = new JButton("Start");
		start.addActionListener(ipc);
		buttonpanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		buttonpanel.add(start);
		page.add(buttonpanel);
		
		this.add(page);
		
		
	}
	
}
