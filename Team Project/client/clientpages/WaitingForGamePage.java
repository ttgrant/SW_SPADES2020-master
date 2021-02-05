package client.clientpages;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import client.clientcontrollers.WaitingForGamePageController;



public class WaitingForGamePage extends JPanel {
	
	
	
	
	public WaitingForGamePage(WaitingForGamePageController wfgpc) {
		
		JPanel status = new JPanel(new BorderLayout());
		JLabel waiting = new JLabel("Waiting For Game to Start");
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(wfgpc);
		status.add(waiting,BorderLayout.CENTER);
		status.add(cancel,BorderLayout.SOUTH);
		
		this.add(status);
		
		
	}

}
