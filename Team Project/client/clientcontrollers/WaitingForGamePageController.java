package client.clientcontrollers;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JPanel;

import client.GameClient;

public class WaitingForGamePageController implements ActionListener {
	
	private JPanel container;
	private GameClient gameClient;
	
	public WaitingForGamePageController(JPanel container,GameClient gameClient) {
		this.container = container;
	    this.gameClient = gameClient;
	}
		
	

//
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		 String command = ae.getActionCommand();
		 
		 if(command == "Cancel") {
			 try {
				gameClient.sendToServer("Stop looking for game");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 CardLayout cardLayout = (CardLayout)container.getLayout();
	         cardLayout.show(container, "3");
		 }
		 
		 
		
	}
	public void display() {
		 CardLayout cardLayout = (CardLayout)container.getLayout();
         cardLayout.show(container, "5");
	
	}
	
	

}
