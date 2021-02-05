package client.clientcontrollers;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import client.GameClient;

public class InitialPageController implements ActionListener {
	private JPanel container;
	private GameClient gameClient;

	
	public InitialPageController(JPanel container,GameClient gameClient) {
		this.container = container;
		this.gameClient = gameClient;
		
	}

	
	public void actionPerformed(ActionEvent ae) {
		String command = ae.getActionCommand();
		if(command == "Start") {
			CardLayout cardLayout = (CardLayout)container.getLayout();
		      cardLayout.show(container, "1");
		}
		
		
	}
}
