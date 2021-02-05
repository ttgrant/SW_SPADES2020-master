package clientServerData;

import java.io.Serializable;

import ocsf.server.ConnectionToClient;

public class UserData implements Serializable
{
	private String username;
	private Long connectionID;
	
	public UserData(String Username, long id) {
		// TODO Auto-generated constructor stub
		this.username = Username;
		this.connectionID = id;
	}

	public UserData() {
		// TODO Auto-generated constructor stub
		username = "";
		connectionID = null;
	}

	public void newUser(String Username, long ID) 
	{
		this.username = Username;
		this.connectionID = ID;
		
	}
	
	public void setUsername(String username) 
	{
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}

	public void setPlayAgain(String username) 
	{
		this.username = username;
	}
	
	public String getPlayAgain() 
	{
		return this.username;
	}

	public Long getConnectionID() {
		return connectionID;
	}

	public void setConnectionID(Long connectionID) {
		this.connectionID = connectionID;
	}
}
