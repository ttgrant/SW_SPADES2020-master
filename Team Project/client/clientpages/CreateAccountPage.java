package client.clientpages;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import client.clientcontrollers.CreateAccountController;

public class CreateAccountPage extends JPanel {
	
	
		  private JTextField usernameField;
		  private JPasswordField passwordField;
		  private JPasswordField verifiedpasswordField;
		  private JLabel errorLabel;
		  
		  // Getter for the text in the username field.
		  public String getUsername()
		  {
		    return usernameField.getText();
		  }
		  
		  // Getter for the text in the password field.
		  public String getPassword()
		  {
		    return String.valueOf(passwordField.getPassword());
		  }
		  public String getverifiedpassword() {
			  return String.valueOf(verifiedpasswordField.getPassword());
		  }
		  // Setter for the error text.
		  public void setError(String error)
		  {
		    errorLabel.setText(error);
		  }
		  
		  // Constructor for the login panel.
		  public CreateAccountPage(CreateAccountController lc)
		  {
		    // Create the controller and set it in the chat client.
		    //LoginController controller = new LoginController(container, client);
		    //client.setLoginControl(controller);
		        
		    // Create a panel for the labels at the top of the GUI.
		    JPanel labelPanel = new JPanel(new GridLayout(2, 1, 5, 5));
		    errorLabel = new JLabel("", JLabel.CENTER);
		    errorLabel.setForeground(Color.RED);
		    JLabel instructionLabel = new JLabel("Enter your desired username and password to create account.", JLabel.CENTER);
		    labelPanel.add(errorLabel);
		    labelPanel.add(instructionLabel);

		    // Create a panel for the login information form.
		    JPanel createaccountpanel = new JPanel(new GridLayout(3, 2, 5, 5));
		    JLabel usernameLabel = new JLabel("Username:", JLabel.RIGHT);
		    usernameField = new JTextField(10);
		    JLabel passwordLabel = new JLabel("Password:", JLabel.RIGHT);
		    passwordField = new JPasswordField(10);
		    JLabel verifyPassword = new JLabel("Verify Password:",JLabel.RIGHT);
		    verifiedpasswordField = new JPasswordField(10);
		    createaccountpanel.add(usernameLabel);
		    createaccountpanel.add(usernameField);
		    createaccountpanel.add(passwordLabel);
		    createaccountpanel.add(passwordField);
		    createaccountpanel.add(verifyPassword);
		    createaccountpanel.add(verifiedpasswordField);
		    
		    
		    
		    // Create a panel for the buttons.
		    JPanel buttonPanel = new JPanel();
		    JButton submitButton = new JButton("Submit");
		    submitButton.addActionListener(lc);
		    JButton cancelButton = new JButton("Cancel");
		    cancelButton.addActionListener(lc);    
		    buttonPanel.add(submitButton);
		    buttonPanel.add(cancelButton);

		    // Arrange the three panels in a grid.
		    JPanel grid = new JPanel(new GridLayout(3, 1, 0, 10));
		    grid.add(labelPanel);
		    grid.add(createaccountpanel);
		    grid.add(buttonPanel);
		    this.add(grid);
		  }

		
		

		
	}


