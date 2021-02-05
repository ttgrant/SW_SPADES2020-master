package client.clientcontrollers;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JPanel;

import client.GameClient;
import client.clientpages.AdminPage;
import client.clientpages.LoginPage;

public class AdminController  implements ActionListener {

	  private JPanel container;
	  private GameClient gameClient;
	
	
	public AdminController(JPanel container, GameClient gameClient) {
		this.container = container;
		this.gameClient = gameClient;	
	}
	
	public void actionPerformed(ActionEvent ae)
	  {
	    // Get the name of the button clicked.
	    String command = ae.getActionCommand();
	    System.out.println(command);
	    if(command == "Main Menu")
	    {
	    	CardLayout cardLayout = (CardLayout)container.getLayout();
	        cardLayout.show(container, "3");
	    }
	    else if(command == "Refresh") 
	    {
	    	// Submit the data request to server
	        try {
	        	gameClient.sendToServer("Admin Data");
	        } catch (IOException e) {
		  		// TODO Auto-generated catch block
		  		e.printStackTrace();
	        }

	    }
	  }
	
	public void setAdminData() 
	{
    	//Set admin data from server
		// AdminPage adminPage = 
		//adminPage.setGamesPlayed("1");
		//adminPage.setCurrentGames("2");
		//adminPage.setTotalUsers("3");
		//adminPage.setOnlineUsers("4");
	}

	public void populatePage(String[] temp) {
		// TODO Auto-generated method stub
		AdminPage adminpage = (AdminPage)container.getComponent(4);
		adminpage.setOnlineUsers(Integer.valueOf(temp.length));
		adminpage.setOnlineUsersPane(temp);
		
	}

}
