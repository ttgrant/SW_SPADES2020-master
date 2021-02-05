package client;

import javax.swing.*;

import client.clientcontrollers.AdminController;
import client.clientcontrollers.CreateAccountController;
import client.clientcontrollers.GameBoardController;
import client.clientcontrollers.InitialPageController;
import client.clientcontrollers.LoginController;
import client.clientcontrollers.MainMenuController;
import client.clientcontrollers.PostGameController;
import client.clientcontrollers.WaitingForGamePageController;
import client.clientpages.AdminPage;
import client.clientpages.CreateAccountPage;
import client.clientpages.GameBoardPage;
import client.clientpages.InitialPage;
import client.clientpages.LoginPage;
import client.clientpages.MainMenuPage;
import client.clientpages.PostGamePage;
import client.clientpages.WaitingForGamePage;

import java.awt.*;
import java.io.IOException;

public class GameGui extends JFrame
{
 
  private GameClient gameClient;
  // Constructor that creates the client GUI.
  public GameGui()
  {
	gameClient = new GameClient(this);
    gameClient.setHost("71.129.97.242");
    gameClient.setPort(8300);
    try {
		gameClient.openConnection();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	};
    
    // Set the title and default close operation.
    this.setTitle("Client");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    // Create the card layout container.
    CardLayout cardLayout = new CardLayout();
    JPanel container = new JPanel(cardLayout);
    
    //Create the Controllers next
    //Next, create the Controllers
   
    LoginController lc = new LoginController(container, gameClient); //Probably will want to pass in gameClient here
    CreateAccountController cac = new CreateAccountController(container,gameClient);
    MainMenuController mmc = new MainMenuController(container,gameClient);
    AdminController apc = new AdminController(container,gameClient);
    WaitingForGamePageController wfgpc = new WaitingForGamePageController(container,gameClient);
    GameBoardController gbc = new GameBoardController(container,gameClient);
    PostGameController pgc = new PostGameController(container,gameClient);
    InitialPageController ipc = new InitialPageController(container,gameClient);
    // Create the four views. (need the controller to register with the Panels
    JPanel view0 = new InitialPage(ipc);
    JPanel view1 = new LoginPage(lc);
    JPanel view2 = new CreateAccountPage(cac);
    JPanel view3 = new MainMenuPage(mmc);
    JPanel view4 = new AdminPage(apc);
    JPanel view5 = new WaitingForGamePage(wfgpc);
    JPanel view6 = new GameBoardPage(gbc);
    JPanel view7 = new PostGamePage(pgc);
    // Add the views to the card layout container.
    container.add(view0,"0");
    container.add(view1, "1");
    container.add(view2, "2");
    container.add(view3,"3");
    container.add(view4,"4");
    container.add(view5,"5");
    container.add(view6,"6");
    container.add(view7,"7");
    gameClient.setPanels(ipc,lc, cac, mmc, apc, wfgpc, gbc, pgc);
   
    
    // Show the initial view in the card layout.
    cardLayout.show(container, "0");
    
    // Add the card layout container to the JFrame.
    this.add(container, BorderLayout.CENTER);

    // Show the JFrame.
    this.setSize(500, 300);
    this.setVisible(true);
   
  }

  // Main function that creates the game GUI when the program is started.
  public static void main(String[] args)
  {
    new GameGui();
  }
}
