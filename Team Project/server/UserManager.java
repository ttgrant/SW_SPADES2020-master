package server;


import java.io.IOException;

import ocsf.server.ConnectionToClient;
import clientServerData.CreateAccountData;
import clientServerData.LoginData;
import clientServerData.UserData;

public class UserManager {

	private GameServer gameServer;
	private Database db;

	public UserManager(GameServer gameServer) {
		this.gameServer = gameServer;
	}
	
	public void setDatabase(Database db) {
		this.db = db;
	}
	
	public Database getDatabase() {
		return db;
	}

	public void VerifyLogin(LoginData loginData, ConnectionToClient arg1) throws IOException 
	{
		System.out.println("Database recieved: " + loginData.getPassword());
		//Search Database with Login Data query on username
		if(!db.verifyAccount(loginData.getUsername(), loginData.getPassword())) 
		{
			System.out.println("Usermanager: Incorrect Username/Password");
		 	arg1.sendToClient("Incorrect Username/Password");
	 	}
		else 
	    {
			
			System.out.println("UserData: Login Successful");
			String isAdmin = db.isAdmin(loginData.getUsername());
			arg1.sendToClient("Login Successful "+isAdmin);
			gameServer.addConnection(new UserData(loginData.getUsername(),arg1.getId()));
			// create a user object with the data from the database along with the recieved connection id
			//UserData tempuser = new UserData();
			//tempuser.newUser(loginData.getUsername(), arg1.getId());
			//System.out.println(tempuser.getUsername());
			//arg1.sendToClient(tempuser);
	    }
	 	
	}

	public void VerifyCreateAccount(CreateAccountData createAccountData, ConnectionToClient arg1) throws IOException 
	{
		System.out.println("Create Account info recieved");
		//Search Database with Login Data query on username
		if(!db.createNewAccount(createAccountData.getUsername(), createAccountData.getPassword())) 
		{
			arg1.sendToClient("Username Already Taken");
		}
	    else 
	    {
	   	 	arg1.sendToClient("Account Created");
	    }
	
		}

	public void UpdateTotalGames(UserData user) {
		//increase total number of games for recieved user in database
		db.increaseTotalGames(user.getUsername());
	}
	public void UpdateWins(UserData user) {
		//increase win number of games for recieved user in database
		db.increaseWins(user.getUsername());
	}
}
