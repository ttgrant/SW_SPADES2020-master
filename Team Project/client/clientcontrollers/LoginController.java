package client.clientcontrollers;

import java.awt.*;
import javax.swing.*;

import client.GameClient;
import client.clientpages.LoginPage;
import client.clientpages.MainMenuPage;
import clientServerData.LoginData;

import java.awt.event.*;
import java.io.IOException;

public class LoginController implements ActionListener
{
  // Private data fields for the container and game client.
  private JPanel container;
  private GameClient gameClient;
 
 
  // Constructor for the login controller.
  public LoginController(JPanel container,GameClient gameClient)
  {
    this.container = container;
    this.gameClient = gameClient;
  }
  
  // Handle button clicks.
  public void actionPerformed(ActionEvent ae)
  {
    // Get the name of the button clicked.
    String command = ae.getActionCommand();

    // The Cancel button takes the user back to the login panel.
    if (command == "Cancel")
    {
      CardLayout cardLayout = (CardLayout)container.getLayout();
      cardLayout.show(container, "0");
    }

    // The Submit button submits the login information to the server.
    else if (command == "Submit")
    {
    	
      // Get the username and password the user entered.
      LoginPage loginPage = (LoginPage)container.getComponent(1);
      System.out.println("in login controller:" + loginPage.getPassword());
      LoginData data = new LoginData(loginPage.getUsername(), loginPage.getPassword());
      
      // Check the validity of the information locally first.
      if (data.getUsername().equals("") || data.getPassword().equals(""))
      {
        displayError("You must enter a username and password.");
        return;
      }

      // Submit the login information to the server.
      try {
		gameClient.sendToServer(data);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
 
      
    }
    else if (command == "Create Account") {
    	CardLayout cardLayout = (CardLayout)container.getLayout();
        cardLayout.show(container, "2");
    	
    	
    }
   
    
  }

  // After the login is successful take user to main menu
  public void loginSuccess(String message)
  {
	  	LoginPage loginPage = (LoginPage)container.getComponent(1);
	    
	    CardLayout cardLayout = (CardLayout)container.getLayout();
	    cardLayout.show(container, "3");
	    if(message.contains("1")) {
	    	MainMenuPage mainMenuPage = (MainMenuPage)container.getComponent(3);
	    	mainMenuPage.enableAdmin();
		  }
	    
    
  }

  public void loginfail() {
	 
	  displayError("Incorrect Username/Password");
  }
  
  // Method that displays a message in the error 
  public void displayError(String error)
  {
	  	LoginPage loginPage = (LoginPage)container.getComponent(1);
	    loginPage.setError(error);
    
  }
}
