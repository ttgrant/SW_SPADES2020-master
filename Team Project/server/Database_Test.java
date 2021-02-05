package server;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import game_Manager.GameManager;
import clientServerData.UserData;

public class Database_Test {

	private String[] test_users = {"user1","user2","user3"};
	private String[] test_passwords = {"password1","password2","password3"};
	
	private Database db;
	private Connection conn;
	private int rand;
	
	@Before
	public void setUp() throws Exception 
	{
		db =  new Database();
	}
	
	@Test
	public void testDatabase() {
		db.setConnection();
		assertNotNull(db.getConnection());
	}

	public void resetTestDatabase() {
		
		for(int i = 0; i < test_users.length; i++) {
			String dml = String.format("DELETE FROM users where username = '%s'", test_users[i]);
			try {
				db.executeDML(dml);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}
		
	}
	
	//Test get all users
	@Test
	public void testQuery() throws SQLException {
		
		//1. Set connection and reset temp data
		db.setConnection();
		db.getConnection();
		resetTestDatabase();
			
		//2. Insert test data to database
		rand = ((int)Math.random()*test_users.length);
		String command = String.format("INSERT INTO users VALUES ('%s', '%s', 0)", test_users[rand], test_passwords[rand]);
		String expected = test_passwords[rand];
		db.executeDML(command);
		
		//3. Query the database
		command = String.format("select password from users where username = '%s'", test_users[rand]);	
		ArrayList<String> result = db.query(command);
		System.out.println("Returned " + result);
		assertEquals(expected, result.get(0));	
	}

	// Test insert all user and passwords
	@Test
	public void testExecuteDML() throws Exception
	{
		boolean noException = true;
		
		//1. Set connection and reset temp data
		db.setConnection();
		db.getConnection();
		resetTestDatabase();
		
		//2. Set statement
		String command = String.format("INSERT INTO users VALUES ('%s', '%s', 0)", test_users[0], test_passwords[0]);
		
		try {
			  db.executeDML(command);
		  } catch (Exception e) {
			  noException = true;
		  }

		  assertTrue(noException);
	}

	@Test
	public void testVerifyAccount() {
		
		//1. Set connection and reset temp data
		db.setConnection();
		db.getConnection();
		resetTestDatabase();
		
		//2. Insert test data to database
		String command = String.format("INSERT INTO users(username, password, admin) VALUES ('%s','%s',0)", test_users[0], test_passwords[0]);
		try {
			db.executeDML(command);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//3. Verify account
		boolean accountVerified = false;
		accountVerified = db.verifyAccount(test_users[0], test_passwords[0]);
		
		assertTrue(accountVerified);
	}

	@Test
	public void testCreateNewAccount() {
		
		//1. Set connection and reset temp data
		db.setConnection();
		db.getConnection();
		resetTestDatabase();
		
		//2. Set account values
		rand = ((int)Math.random()*test_users.length);
		String username = test_users[rand]; 
		String password = test_passwords[rand];
		
		
		//3. Attempt to create account
		boolean accountCreated = false;
		accountCreated = db.createNewAccount(username, password);
		assertTrue(accountCreated);
		
	}

	@Test
	public void testCloseConnection() throws SQLException {
		db.setConnection();
		db.CloseConnection();
		assertTrue(db.getConnection().isClosed());
	}

}
