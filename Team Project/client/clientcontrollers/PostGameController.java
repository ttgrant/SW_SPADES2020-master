package client.clientcontrollers;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;

import client.GameClient;
import client.clientpages.GameBoardPage;
import client.clientpages.PostGamePage;

public class PostGameController  implements ActionListener {
	
	  private JPanel container;
	  private GameClient gameClient;
	  
	  public PostGameController(JPanel container, GameClient gameClient) {
		  this.container = container;
		  this.gameClient = gameClient;
	  }
	  
	  public void actionPerformed(ActionEvent ae)
	  {
	    // Get the name of the button clicked.
	    String command = ae.getActionCommand();
	    
	    if(command == "Play Again")
	    {
	    	try {
				gameClient.sendToServer("Waiting for game");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    else if(command == "Quit")
	    {
	    	  CardLayout cardLayout = (CardLayout)container.getLayout();
	          cardLayout.show(container, "3");
	          gameClient.setPushedPlay(false);
	    }
	    
	    
	  }
	  
	  public void display(String winnerstring) {
		  	PostGamePage postGamePage = (PostGamePage) container.getComponent(7);
			CardLayout cardLayout = (CardLayout) container.getLayout();
			cardLayout.show(container, "7");
			postGamePage.setWinner(winnerstring);
		}


}
