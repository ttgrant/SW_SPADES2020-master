package client.clientpages;

import javax.swing.*;
import java.awt.*;
import client.clientcontrollers.AdminController;
import java.awt.event.*;


public class AdminPage extends JPanel {
	private JLabel onlineUsers;
	private JTextPane users;

	public void setOnlineUsersPane(String[] users)
    {
		this.users.setText("");
		for (int i = 0; i < users.length; i++) {
			this.users.setText(this.users.getText() + users[i]);
		}
      
    }
  

    // Setter for the online users text.
    public void setOnlineUsers(Integer onlineUsers)
    {
      this.onlineUsers.setText(onlineUsers.toString());
    }

	public AdminPage(AdminController apc) {
		
		// Label Panel
	    JPanel labelPanel = new JPanel(new GridLayout(1, 2, 5, 5));
	    
	    JLabel onlineUserslbl = new JLabel("# Of Users Online: ", JLabel.CENTER);
	    onlineUsers = new JLabel("", JLabel.CENTER);
	    labelPanel.add(onlineUsers);
	    labelPanel.add(onlineUserslbl);
		
	    // Button Panel
	    JPanel buttonPanel = new JPanel(new GridLayout(1,2,5,0));
	    JPanel buttonPanel2 = new JPanel(new BorderLayout());
	    
	    JButton refreshButton = new JButton("Refresh");
	    refreshButton.addActionListener(apc);
	    JButton returntoMainMenu = new JButton("Main Menu");
	    returntoMainMenu.addActionListener(apc);    
	    buttonPanel.add(refreshButton);
	    buttonPanel.add(returntoMainMenu);
	    
	    buttonPanel2.add(buttonPanel, BorderLayout.SOUTH);
	    
	    // Page Title
	    JPanel titlePanel = new JPanel(new GridLayout(1, 1, 0, 0));
	    
	    JLabel title = new JLabel("Admin Page", JLabel.CENTER);
	    title.setFont(new Font("Label.font", Font.PLAIN, 20));
	    
	    titlePanel.add(title);
		
		// Arrange the panels to grid.
	    JPanel grid = new JPanel(new GridLayout(3, 1, 0, 15));
	    grid.add(labelPanel);
	    
	    this.add(grid);
	    
	    JPanel userPanel = new JPanel();
	    grid.add(userPanel);
	    userPanel.setLayout(new GridLayout(0, 1, 0, 0));
	    grid.add(buttonPanel2);
	    users = new JTextPane();
	    users.setEditable(false);
	    userPanel.add(users);
		
	}
}
