package client.clientcontrollers;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import client.GameClient;
import client.GameGui;
import client.clientpages.CreateAccountPage;
import clientServerData.CreateAccountData;

public class CreateAccountController  implements ActionListener  {
	private JPanel container;
	  private GameClient gameClient;
	  
	  // Constructor for the create account controller.
	  public CreateAccountController(JPanel container,GameClient gameClient)
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
	        cardLayout.show(container, "1");
	    }

	    // The Submit button submits the login information to the server.
	    else if (command == "Submit")
	    {
	      // Get the username and password the user entered.
	      CreateAccountPage capanel = (CreateAccountPage)container.getComponent(2);
	      CreateAccountData data = new CreateAccountData(capanel.getUsername(), capanel.getPassword());
	      
	      // Check the validity of the information locally first.
	      if (data.getUsername().equals("") || data.getPassword().equals(""))
	      {
	        displayError("You must enter a username and password.");
	        return;
	      } else if(!capanel.getverifiedpassword().equals(data.getPassword())) {
	    	  displayError("Your Password field and verified password fields must match");
	      }
	      else

	      // Submit the login information to the server.
	      try {
			gameClient.sendToServer(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
	    }
	  }

	  // After the creation of account is successful take user to main menu
	
	  public void createaccountsucess()
	  {
		  CreateAccountPage createAccountPage = (CreateAccountPage)container.getComponent(2);
		    GameGui gameGUI = (GameGui)SwingUtilities.getWindowAncestor(createAccountPage);
		    CardLayout cardLayout = (CardLayout)container.getLayout();
		    cardLayout.show(container, "1");
	  }
	  
	  public void createaccountfailure() {
		  displayError("Username already taken");
	  }

	  // Method that displays a message in the error 
	  public void displayError(String error)
	  {
	    CreateAccountPage createaccountpage = (CreateAccountPage)container.getComponent(2);
	    createaccountpage.setError(error);
	    
	  }
	}

