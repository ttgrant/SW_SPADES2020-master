package client.clientcontrollers;

import java.awt.*;
import java.lang.*;
import javax.swing.*;

import client.GameClient;
import client.clientpages.LoginPage;
import clientServerData.UserData;

import java.awt.event.*;
import java.io.IOException;

public class MainMenuController implements ActionListener
{
  // Private data fields for the container and game client.
  private JPanel container;
  private GameClient gameClient;
 

  
  // Constructor for the main menu controller.
  public MainMenuController(JPanel container,GameClient gameClient)
  {
    this.container = container;
    this.gameClient = gameClient;
    

  }
  
  // Handle button clicks.
  public void actionPerformed(ActionEvent ae)
  {
    // Get the name of the button clicked.
    String command = ae.getActionCommand();
  
    // The logout button takes the user back to the login panel.
    if (command == "Logout")
    {
    	
      try {
		gameClient.sendToServer("Logging out");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      System.exit(0);
      
    }

    // The play button takes user to waiting for game page
    else if (command == "Play")
    {
    	
         
         try {
			gameClient.sendToServer("Waiting for game");
			gameClient.setPushedPlay(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    //The admin button takes the user to the admin page
    else if (command == "Admin Page")
    {
    	
    	CardLayout cardLayout = (CardLayout)container.getLayout();
        cardLayout.show(container, "4");
        try {
			gameClient.sendToServer("Admin");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
  }

  
  

}
