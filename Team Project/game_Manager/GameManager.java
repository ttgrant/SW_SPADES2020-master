package game_Manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import clientServerData.UserData;
import ocsf.server.ConnectionToClient;
import server.GameServer;

public class GameManager {

	GameServer server = null;

	private final ArrayList<String> OGDeck = new ArrayList<String>(Arrays.asList("C2", "C3", "C4", "C5", "C6", "C7",
			"C8", "C9", "C10", "C11", "C12", "C13", "C14", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "D10", "D11",
			"D12", "D13", "D14", "H2", "H3", "H4", "H5", "H6", "H7", "H8", "H9", "H10", "H11", "H12", "H13", "H14",
			"S2", "S3", "S4", "S5", "S6", "S7", "S8", "S9", "S10", "S11", "S12", "S13", "S14"));

	private ArrayList<String> playingDeck = new ArrayList<String>();

	private int CurrentRound = 0;
	private final int MaxRounds = 2;
	private String FirstPlayerMove = new String();
	private String SecondPlayerMove = new String();
	private int FirstPlayerBet = 0;
	private int SecondPlayerBet = 0;
	private UserData player1 = null;
	private UserData player2 = null;
	private int player1Score = 0; // these are the overall scores
	private int player2Score = 0;


	// below are the blank players hands
	private ArrayList<String> player1Hand = new ArrayList<String>();
	private ArrayList<String> player2Hand = new ArrayList<String>();

	// below are the turn scores, AKA: how many bids are won
	private int player1turnscore = 0;
	private int player2turnscore = 0;

	private final int maxTurns = 13;
	private int currentTurn = 0;

	// Determines who is to go first in the turn
	private boolean player1First = true;

	public GameManager(GameServer server, UserData player1) {
		// Basic constructor that allows for the game manager to know the players and
		// the server
		// while setting up the basic information needed for containing a round
		this.server = server;
		this.player1 = player1;

		// this.player2 = player2;

		CurrentRound = 0;
		currentTurn = 0;
	}

	public void setPlayer2(UserData player2) {
		this.player2 = player2;
	}

	public long getPlayer1() {
		return player1.getConnectionID();
	}

	public long getPlayer2() {
		return player2.getConnectionID();
	}

	public void ReceiveMove(String temp) {

		// Essentially a two way setter for the moves of the players,
		// at the beginning of a turn both moves will be set to null,
		// if firstplayermove is null and this is called then obviously
		// it is the first players move and if not null, then it is obviously the second
		// players
		if (currentTurn != 14) {
			if (temp.contains("Player1Card") && FirstPlayerMove == null) {
				temp = temp.substring(11);
				FirstPlayerMove = temp;
				System.out.println("First Player's Move Received");
				server.sendToAllClients("Player1 Wait : Player2 Display move " + FirstPlayerMove);
				System.out.println("Player1 Wait : Player2 Display move " + FirstPlayerMove);

			} else if (temp.contains("Player2Card") && SecondPlayerMove == null) {
				temp = temp.substring(11);
				SecondPlayerMove = temp;
				System.out.println("Second Player's Move Received");
				server.sendToAllClients("Player1 Display move " + SecondPlayerMove + ": Player2 Wait");
				System.out.println("Player1 Display move " + SecondPlayerMove + ": Player2 Wait");
				// determineTurnWinner();
			}
			if (FirstPlayerMove != null && SecondPlayerMove != null && currentTurn != 14) {
				server.sendToAllClients("Player1 Wait : Player2 Wait");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Determining turn winner");
				determineTurnWinner();
			} else if (!player1First) {
				server.sendToAllClients("Player1 Turn : Player2 Wait");
				System.out.println("Player1 Turn : Player2 Wait");

			} else {
				server.sendToAllClients("Player1 Wait : Player2 Turn");
				System.out.println("Player1 Wait : Player2 Turn");
			}
		} else {
			server.sendToAllClients("Player1 Wait : Player2 Wait");
			System.out.println("Player1 Wait : Player2 Wait");
		}

	}

	/*
	 * public String SendMove() {
	 * 
	 * //Sends a move to the Server communication, So what it will determine when
	 * called is it // will check to ensure what move is to be sent, if there is no
	 * received move it will // send an error instead, it should return a string
	 * either way.
	 * 
	 * if(FirstPlayerMove != null && SecondPlayerMove == null) { return
	 * FirstPlayerMove; } else if(SecondPlayerMove != null) { return
	 * SecondPlayerMove; }else return "ERROR: NO PLAYER MOVES HAVE BEEN STORED!!!";
	 * }
	 */
	public void receiveBet(String bet) {
		// Similar to the receiving of a move, the boardGameData will hold the players
		// current
		// bet regardless of whose turn it is. However, due to the fact that a new round
		// has a new
		// bet each time, it will be null before this is called. And we can set the
		// FirstPlayer to play
		// at the beginning of each round and associate the first player bet with that
		// player

		if (bet.contains("Player1Bet")) {
			FirstPlayerBet = Integer.parseInt(bet.substring(11));
			System.out.println("First Player's Bet Received");
		} else if (bet.contains("Player2Bet")) {
			SecondPlayerBet = Integer.parseInt(bet.substring(11));
			System.out.println("Second Player's Bet Received");
		}

		if (FirstPlayerBet != 0 && SecondPlayerBet != 0) {
			startFirstRound();
		}
	}

	public Integer sendBet() {
		// Sends a Bet to the Server communication, So what it will determine when
		// called is it
		// will check to ensure what bet is to be sent. Similar to the move being sent

		if (FirstPlayerBet != 0 && SecondPlayerBet == 0) {
			return FirstPlayerBet;
		} else if (SecondPlayerBet != 0) {
			return SecondPlayerBet;
		} else
			return null;
	}

	// WE HAVE RECEIVE PLAYERS HERE, BUT I FIGURE THAT WE CAN JUST HAVE A
	// CONSTRUCTOR THAT INTAKES
	// PLAYERS AS IT WOULD MAKE MORE SENSE, SINCE IT WOULD NOT BE A GAMEMANAGER BUT
	// SERVER WHO WOULD DO MATCHMAKING?

	public String SendScoreRoundEnd() {
		return player1.getUsername() + " 's Score: " + player1Score + ", " + player2.getUsername() + "'s Score: "
				+ player2Score;
	}

	public void StartRound() {
		CurrentRound++;
		FirstPlayerBet = 0;
		FirstPlayerMove = null;
		player1turnscore = 0;
		SecondPlayerBet = 0;
		SecondPlayerMove = null;
		player2turnscore = 0;
		player1Hand.clear();
		player2Hand.clear();
		FirstPlayerBet = 0;
		SecondPlayerBet = 0;
		currentTurn = 0;

		if (playingDeck.size() > 0) {
			playingDeck.clear();
		}
		for (String s : OGDeck) {
			playingDeck.add(s);
		}
		Collections.shuffle(playingDeck);

		for (int i = 0; i <= 12; i++) {
			player1Hand.add(playingDeck.get(i));

		}
		for (int i = 13; i <= 26; i++) {
			player2Hand.add(playingDeck.get(i));
		}

		ArrayList<ArrayList<String>> hands = new ArrayList<ArrayList<String>>();
		hands.add(player1Hand);
		hands.add(player2Hand);

		server.sendToAllClients(hands);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		askBet();

		// return hands;
		// Here is where we would request the
		// bet from first player1, and then player2

	}

	private void startFirstRound() {
		// TODO Auto-generated method stub
		if (currentTurn == 0) {

			if (player1First) {
				server.sendToAllClients("Player1 Turn : Player2 Wait");
				System.out.println("Player1 Turn : Player2 Wait");
			} else {
				server.sendToAllClients("Player1 Wait : Player2 Turn");
				System.out.println("Player1 Wait : Player2 Turn");
			}
		}

	}

	private void askBet() {

		server.sendToAllClients("Player1 Bet : Player2 Bet");
		System.out.println("Player1 Bet : Player2 Bet");
	}

	private void determineTurnWinner() {
		// calculate that the current turn has come to an end and increment
		currentTurn++;

		// This is where it gets tricky because I need to know how we are storing moves,
		// if we are storing it in a comma delimited string then i know that i can go
		// ahead
		// and parse it to where play[0] is the suit and play[1] is the numeric value

		if (FirstPlayerMove != null && SecondPlayerMove != null) {
			char firstSuit = FirstPlayerMove.charAt(0);
			String firstValue = FirstPlayerMove.substring(1);
			char secondSuit = SecondPlayerMove.charAt(0);
			String secondValue = SecondPlayerMove.substring(1);
			System.out.println("Comparing cards: " + firstSuit + firstValue + "  " + secondSuit + secondValue);
			if (player1First) {
				if (firstSuit == secondSuit) {
					// If they are both the same suit, high card
					if (Integer.parseInt(firstValue) > Integer.parseInt(secondValue)) {
						player1turnscore++;
						System.out.println("Player 1 turn score:" + player1turnscore);
						server.sendToAllClients("Player1 TWin : Player2 TLoss");
						System.out.println("Player1 TWin : Player2 TLoss");
					} else {
						player2turnscore++;
						System.out.println("Player 2 turn score:" + player2turnscore);
						server.sendToAllClients("Player1 TLoss : Player2 TWin");
						System.out.println("Player1 TLoss : Player2 TWin");
					}
				} else if (firstSuit != secondSuit) { // This is the not so tricky part. if there is a spade in the
														// values
														// of the suit then i must determine who played the spade

					if (secondSuit == 'S') {
						player2turnscore++;
						System.out.println("Player 2 turn score:" + player2turnscore);
						server.sendToAllClients("Player1 TLoss : Player2 TWin");
						System.out.println("Player1 TLoss : Player2 TWin");
					} else {
						player1turnscore++;
						System.out.println("Player 1 turn score:" + player1turnscore);
						server.sendToAllClients("Player1 TWin : Player2 TLoss");
						System.out.println("Player1 TWin : Player2 TLoss");
					}
				}
			} else {
				if (firstSuit == secondSuit) {
					// If they are both the same suit, high card
					if (Integer.parseInt(firstValue) > Integer.parseInt(secondValue)) {
						player1turnscore++;
						System.out.println("Player 1 turn score:" + player1turnscore);
						server.sendToAllClients("Player1 TWin : Player2 TLoss");
						System.out.println("Player1 TWin : Player2 TLoss");
					} else {
						player2turnscore++;
						System.out.println("Player 2 turn score:" + player2turnscore);
						server.sendToAllClients("Player1 TLoss : Player2 TWin");
						System.out.println("Player1 TLoss : Player2 TWin");
					}
				} else if (firstSuit != secondSuit) { // This is the not so tricky part. if there is a spade in the
														// values
														// of the suit then i must determine who played the spade

					if (firstSuit == 'S') {
						player1turnscore++;
						System.out.println("Player 2 turn score:" + player2turnscore);
						server.sendToAllClients("Player1 TWin : Player2 TLoss");
						System.out.println("Player1 TWin : Player2 TLoss");
					} else {
						player2turnscore++;
						System.out.println("Player 1 turn score:" + player1turnscore);
						server.sendToAllClients("Player1 TLoss : Player2 TWin");
						System.out.println("Player1 TLoss : Player2 TWin");
					}
				}
			}

			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// server.sendToAllClients("Player1 TurnScore " + player1turnscore + ": Player2
			// TurnScore " + player2turnscore);

			// Decided not applicable to send score but turn wins instead

			// server.sendToAllClients("Player1 TurnWin : Player2 TurnLoss");

		}

		// at the end of each turn we reset the moves to null;
		FirstPlayerMove = null;
		SecondPlayerMove = null;

		// Send to the clients who will be going first on the next turn:
		player1First = !player1First;

		if (player1First) {
			server.sendToAllClients("Player1 Turn : Player2 Wait");
			System.out.println("Player1 Turn : Player2 Wait");

		} else if (currentTurn == maxTurns) {
			server.sendToAllClients("Player1 Wait : Player2 Wait");
			System.out.println("Player1 Wait : Player2 Wait");
		} else {
			server.sendToAllClients("Player1 Wait : Player2 Turn");
			System.out.println("Player1 Wait : Player2 Turn");
		}

		if (currentTurn == maxTurns) {
			calculateRoundScore();
		}

	}

	public void calculateRoundScore() {

		// if the first player ends up overbidding, they lose the difference in their
		// actual score and bet
		if (player1turnscore < FirstPlayerBet) {
			player1Score += (player1turnscore - FirstPlayerBet) * 10;
		} else // however if the first player bets equal to or over their total turnscore, then
				// they gain their bet
		{
			player1Score += FirstPlayerBet * 10;
		}

		if (player2turnscore < SecondPlayerBet) {
			player2Score += (player1turnscore - SecondPlayerBet) * 10;
		} else {
			player2Score += SecondPlayerBet * 10;
		}
		server.sendToAllClients("Player1 Score " + player1Score + " : Player2 Score " + player2Score);

		if (CurrentRound == MaxRounds) {
			determineWinner();
		} else {
			StartRound();
		}
	}

	public void determineWinner() {
		// at the end of the seven rounds whomever has the higher score will be declared
		// the winner of the game
		if (player1Score > player2Score) {
			server.setGameEnd(player1,player2,"w");
			server.sendToAllClients("Player1 Wins!  : Player2 Loses!");
			System.out.println("Player1 Wins!  : Player2 Loses!");

		} else if (player1Score < player2Score) {
			server.setGameEnd(player2,player2,"w");
			server.sendToAllClients("Player1 Loses! : Player2 Wins!");
			System.out.println("Player1 Loses! : Player2 Wins!");
		} else {
			server.setGameEnd(player1,player2,"d");
			server.sendToAllClients("Player1 Draw! : Player2 Draw!");
			System.out.println("Player1 Draw! : Player2 Draw!");

		}
		
		
		
		server.deleteActiveGame();

	}


}
