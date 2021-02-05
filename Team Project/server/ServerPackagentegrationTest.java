package server;

import static org.junit.Assert.*;

import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import org.junit.Before;
import org.junit.Test;

import clientServerData.UserData;

public class ServerPackagentegrationTest {
	
	public UserManager user_manager;
	private GameServer test_server;
	private Database db;
	
	private UserData player1, player2;
	
	@Before
	public void setUp() throws Exception 
	{
		player1 = new UserData("player1", 1);  
		player2 = new UserData("player2", 2);
				
		db =  new Database();
		db.setConnection();
		
		resetTestDatabase();
		
		// INsert test data into DB
		String insertUser = String.format("INSERT INTO gamestats(username,totalgames,wins) VALUES ('%s','0',0)",player1.getUsername());
		db.executeDML(insertUser);
		insertUser = String.format("INSERT INTO gamestats(username,totalgames,wins) VALUES ('%s','0',0)",player2.getUsername());
		db.executeDML(insertUser);
		
		test_server =  new GameServer();
		user_manager = new UserManager(test_server);
		
	}
	
	public void resetTestDatabase() 
	{
		
		String dml = String.format("DELETE FROM gamestats where username = '%s'", player1.getUsername());
		String dml2 = String.format("DELETE FROM gamestats where username = '%s'", player2.getUsername());
		try {
			db.executeDML(dml);
			db.executeDML(dml2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
	}
	
	// User Manager methods
	
	@Test
	public void testSetDatabase() {
		user_manager.setDatabase(db);
		assertEquals(user_manager.getDatabase(), db);
	}
	
	@Test
	public void testUpdateTotalGames() 
	{
		user_manager.setDatabase(db);
		
		//Get current total games
	    String command = String.format("select totalgames from gamestats where username = '%s'", player1.getUsername());
		int expected = Integer.parseInt(db.query(command).get(0)) + 1;
		
		//Update game total
		user_manager.UpdateTotalGames(player1);
		int result = Integer.parseInt(db.query(command).get(0));
		
		assertEquals(result, expected);
	}

	@Test
	public void testUpdateWins() 
	{
		user_manager.setDatabase(db);
		
		//Get current total games
	    String command = String.format("select wins from gamestats where username = '%s'", player1.getUsername());
		int expected = Integer.parseInt(db.query(command).get(0)) + 1;
		
		//Update game total
		user_manager.UpdateWins(player1);
		int result = Integer.parseInt(db.query(command).get(0));
		
		assertEquals(result, expected);
	}
	
	// Server methods
	
	@Test
	public void testSetUserManager() {
		test_server.setUserManager(user_manager);
		assertEquals(test_server.getUserManager(), user_manager);
	}
	
	@Test
	public void testAddConnection() {
		
		// Create test TextArea
		JTextArea texArea = new JTextArea();
		test_server.setLog(texArea);
		
		// Add new connected client to test server
		test_server.addConnection(player1);
	
		//Expected return
		String expected = "Connected User added " + player1.getUsername();
		
		// Result
		String log = test_server.getLog();
		System.out.println("Log: " + log);
		String[] result = log.split("\\r?\\n");
		System.out.println(expected);
		
		assertEquals(result[result.length-1], expected);
	}
	
	@Test
	public void testAddDuplicateConnection() {
		
		// Create test TextArea
		JTextArea texArea = new JTextArea();
		test_server.setLog(texArea);
				
		// Add new connected client to test server twice
		test_server.addConnection(player1);
		test_server.addConnection(player1);
		
		//Expected return
		String expected = "Connected User removed then added " + player1.getUsername();
		
		// Result
		String log = test_server.getLog();
		String[] result = log.split("\\r?\\n");
		
		assertEquals(result[result.length-1], expected);
	}
	
	@Test
	public void testSetStatus() {
		JLabel label = new JLabel("status text");
		String expected  = "status text";
		
		test_server.setStatus(label);
		
		assertEquals(expected, test_server.getStatus());
	}
	
	@Test
	public void testDeleteActiveGame() {
		test_server.deleteActiveGame();
		assertNull(test_server.getActiveGame());
	}
	
	@Test
	public void testSetGameEnd() {
		
		user_manager.setDatabase(db);
		test_server.setUserManager(user_manager);
		
		//Get current total games and wins
	    String command1 = String.format("select wins from gamestats where username = '%s'", player1.getUsername());
		int expected1 = Integer.parseInt(db.query(command1).get(0)) + 1;
		
		String command2 = String.format("select totalgames from gamestats where username = '%s'", player2.getUsername());
		int expected2 = Integer.parseInt(db.query(command2).get(0)) + 1;
		
		String command3 = String.format("select totalgames from gamestats where username = '%s'", player1.getUsername());
		int expected3 = Integer.parseInt(db.query(command3).get(0)) + 1;
		
		//Set Game End
		test_server.setGameEnd(player1, player2, "w");
		
		//Get new data
		int result1 = Integer.parseInt(db.query(command1).get(0));
		int result2 = Integer.parseInt(db.query(command2).get(0));
		int result3 = Integer.parseInt(db.query(command3).get(0));
		
		assertEquals(result1, expected1);
		assertEquals(result2, expected2);
		assertEquals(result3, expected3);
	}
	

}
