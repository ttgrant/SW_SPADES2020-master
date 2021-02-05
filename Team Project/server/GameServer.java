package server;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import game_Manager.GameManager;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

import clientServerData.CreateAccountData;
import clientServerData.LoginData;

import clientServerData.UserData;

public class GameServer extends AbstractServer {
	private static UserManager userManager;
	private static ArrayList<UserData> connectedClients;
	private static GameManager activegame;
	private static boolean player2present;

	private JLabel status;
	private JTextArea log;

	public GameServer() {
		super(8300);
		connectedClients = new ArrayList<UserData>();
		activegame = null;
		player2present = false;
	}

	public GameServer(int port) {
		super(port);
		connectedClients = new ArrayList<UserData>();
		activegame = null;
		player2present = false;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
	
	public UserManager getUserManager() {
		return userManager;
	}

	@Override
	protected void handleMessageFromClient(Object arg0, ConnectionToClient arg1) {
		log.append("Messgage from Client: " + arg1.getId() + "\n");
		try {

			// stuff for usermanager
			if (arg0 instanceof LoginData) {
				// Send Data To UserManager for login data
				userManager.VerifyLogin((LoginData) arg0, arg1);
			} else if (arg0 instanceof CreateAccountData) {
				// Send Data To UserManager for Creating and account
				userManager.VerifyCreateAccount((CreateAccountData) arg0, arg1);
			}

			if (arg0 instanceof String) {
				String temp = (String) arg0;
				System.out.println(temp);
				if (temp.equals("Logout")) {
					// do something on logout
					System.out.println("Logout recieved for " + arg1.getId());
				}else if(temp.contains("Admin")) {
					sendUsers(arg1);
				} else if (temp.equals("Waiting for game")) {
					UserData temp1 = null;
					System.out.println(arg1.getId() + " Is waiting for a game");
					// Determine the connected user data based on connection id
					for (UserData connecteduser : connectedClients) {
						if (connecteduser.getConnectionID() == arg1.getId()) {
							temp1 = connecteduser;
						}
					}
					// checking if there is a active game if not create one
					if (activegame == null) {
						GameManager game = new GameManager(this, temp1);
						activegame = game;
						arg1.sendToClient("Game Created");
						System.out.println("Game Created");
					} else {
						// if there is a active game inform both players game is ready to play
						this.sendToAllClients("Game is ready to join");
						activegame.setPlayer2(temp1);
						// Start round in game manager or something
						activegame.StartRound();
					}

				} else if (temp.contains("Player1Card") || temp.contains("Player2Card")) {
					activegame.ReceiveMove(temp);
				} else if (temp.contains("Player1Bet") || temp.contains("Player2Bet")) {
					activegame.receiveBet(temp);
				}
			}
		} catch (

		IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void sendUsers(ConnectionToClient arg1) {
		// TODO Auto-generated method stub
		int i = 0;
		String[] onlineUsers = new String[connectedClients.size()];
		for (UserData userData : connectedClients) {
			onlineUsers[i] = connectedClients.get(i).getUsername() + "\n";
			i++;
		}
		try {
			arg1.sendToClient(onlineUsers);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void listeningException(Throwable exception) {
		// Display info about the exception
		System.out.println("Listening Exception:" + exception);
		exception.printStackTrace();
		System.out.println(exception.getMessage());

	}

	public void serverStarted() {
		System.out.println("Server Started");
		log.append("Server Started\n");
		status.setText("Server Started");
		status.setForeground(Color.GREEN);
	}

	public void serverStopped() {
		System.out.println("Server Stopped");
	}

	public void serverClosed() {
		System.out.println("Server closed");
	}

	public void clientConnected(ConnectionToClient client) {
		System.out.println("Client connected and given connection ID: " + client.getId());
		log.append("Client connected and given connection ID: " + client.getId() + "\n");

	}

	public void addConnection(UserData conn) {
		if (connectedClients.isEmpty()) {
			connectedClients.add(conn);
			System.out.println("Connected User added " + conn.getUsername());
			log.append("Connected User added " + conn.getUsername() + "\n");
		} else if (!connectedClients.contains(conn)) {
			connectedClients.add(conn);
			System.out.println("Connected User added " + conn.getUsername());
			log.append("Connected User added " + conn.getUsername() + "\n");
		} else {
			connectedClients.remove(conn);
			connectedClients.add(conn);
			System.out.println("Connected User removed then added " + conn.getUsername());
			log.append("Connected User removed then added " + conn.getUsername() + "\n");
		}
	}

	public void setStatus(JLabel status) {
		// TODO Auto-generated method stub
		this.status = status;

	}
	
	public String getStatus() {
		return status.getText();
	}

	public void setLog(JTextArea log) {
		// TODO Auto-generated method stub
		this.log = log;

	}
	
	public String getLog() {
		return log.getText();
	}
	
	public void deleteActiveGame() {
		// TODO Auto-generated method stub
		activegame = null;

	}
	
	public GameManager getActiveGame() {
		return activegame;
	}

	public void setGameEnd(UserData U1, UserData U2,String endtype) {
		if(endtype.equals("w")) {
			userManager.UpdateWins(U1);
			userManager.UpdateTotalGames(U1);
			userManager.UpdateTotalGames(U2);
		}
		else {
			userManager.UpdateTotalGames(U1);
			userManager.UpdateTotalGames(U2);
		}
		
	}
}
