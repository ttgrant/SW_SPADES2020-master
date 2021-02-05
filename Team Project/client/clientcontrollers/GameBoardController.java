package client.clientcontrollers;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import client.GameClient;
import client.clientpages.GameBoardPage;

import java.awt.CardLayout;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

public class GameBoardController implements ActionListener {

	private boolean makefirstmove;
	private JPanel container;
	private GameClient gameClient;
	private JSlider betSlider;
	private JLabel player1played;
	private JLabel player2played;
	private ArrayList<String> currentHand;
	private boolean isTurn;
	private JButton selectedCard;

	public JSlider getBetSlider() {
		return betSlider;
	}

	public GameBoardController(JPanel container, GameClient gameClient) {
		this.container = container;
		this.gameClient = gameClient;
		makefirstmove = false;
		isTurn = false;
	}

	public void actionPerformed(ActionEvent ae) {
		GameBoardPage gameBoardPage = (GameBoardPage) container.getComponent(6);
		try {
			String command = ae.getActionCommand();
			if(command.equals("Confirm Bet")) {
				gameBoardPage.disableBet();
				int holder = betSlider.getValue();
				if (makefirstmove) {
					gameClient.sendToServer("Player1Bet " + holder);
				}else {
					gameClient.sendToServer("Player2Bet " + holder);
				}
				
			}
			if (isTurn) {
				if (command.contains("Card")) {
					selectedCard = (JButton) ae.getSource();
					String card = command.substring(5);
					if (makefirstmove) {
						player1played.setIcon(new ImageIcon(GameBoardPage.class.getResource(
								"/cards_png_zip/resized/" + currentHand.get(Integer.parseInt(card) - 1) + ".png")));
						selectedCard.setEnabled(false);
						selectedCard.setIcon(
								new ImageIcon(GameBoardPage.class.getResource("/cards_png_zip/resized/gray_back.png")));
						gameClient.sendToServer("Player1Card" + currentHand.get(Integer.parseInt(card) - 1));

					} else {
						player2played.setIcon(new ImageIcon(GameBoardPage.class.getResource(
								"/cards_png_zip/resized/" + currentHand.get(Integer.parseInt(card) - 1) + ".png")));
						selectedCard.setEnabled(false);
						selectedCard.setIcon(
								new ImageIcon(GameBoardPage.class.getResource("/cards_png_zip/resized/gray_back.png")));
						gameClient.sendToServer("Player2Card" + currentHand.get(Integer.parseInt(card) - 1));
					}

				}
			} else {
				gameBoardPage.setSeverinstructions("Is not your turn yet");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void display() {
		GameBoardPage gameBoardPage = (GameBoardPage) container.getComponent(6);
		CardLayout cardLayout = (CardLayout) container.getLayout();
		cardLayout.show(container, "6");
		if (makefirstmove) {
			gameBoardPage.setSeverinstructions("You are Player 1");
			// whatever the first move us
		} else {
			gameBoardPage.setSeverinstructions("You are Player 2");
		}
		betSlider = gameBoardPage.getBetSlider();
		player1played = gameBoardPage.getPlayer1Played();
		player2played = gameBoardPage.getPlayer2Played();
		ArrayList<String> currentHand = new ArrayList<String>();
		setRoundScore("Player 1: 0 | Player 2: 0");
		

	}

	public boolean isMakefirstmove() {
		return makefirstmove;
	}

	public void setMakefirstmove(boolean makefirstmove) {
		this.makefirstmove = makefirstmove;
	}

	public void setHand(ArrayList<ArrayList<String>> temp) {
		GameBoardPage gameBoardPage = (GameBoardPage) container.getComponent(6);
		if (makefirstmove) {
			gameBoardPage.setCards(temp.get(0));
			currentHand = temp.get(0);
		} else {
			gameBoardPage.setCards(temp.get(1));
			currentHand = temp.get(1);
		}

	}

	public void recieveCommand(String message) {
		GameBoardPage gameBoardPage = (GameBoardPage) container.getComponent(6);
		// TODO Auto-generated method stub
		String[] command = message.split(":");
		String action;
		if (makefirstmove) {
			action = command[0].substring(7);
			if (action.contains("Wait")) {
				isTurn = false;
				gameBoardPage.setSeverinstructions("Player 2 turn to play card");
			} else if (action.contains("Turn")) {
				isTurn = true;
				gameBoardPage.setSeverinstructions("Player 1 turn to play card");
			} else if (action.contains("Display move")) {
				action = action.substring(14);
				player2played.setIcon(
						new ImageIcon(GameBoardPage.class.getResource("/cards_png_zip/resized/" + action + ".png")));
			} else if (action.contains("TWin")) {
				gameBoardPage.setSeverinstructions("You win the bag");
				player1played.setIcon(
						new ImageIcon(GameBoardPage.class.getResource("/cards_png_zip/resized/gray_back.png")));
				player2played.setIcon(
						new ImageIcon(GameBoardPage.class.getResource("/cards_png_zip/resized/gray_back.png")));
			} else if (action.contains("TLoss")) {
				gameBoardPage.setSeverinstructions("You lost the bag");
				player1played.setIcon(
						new ImageIcon(GameBoardPage.class.getResource("/cards_png_zip/resized/gray_back.png")));
				player2played.setIcon(
						new ImageIcon(GameBoardPage.class.getResource("/cards_png_zip/resized/gray_back.png")));
			} else if (action.contains("Bet")) {
				gameBoardPage.setSeverinstructions("Place your bet");
				gameBoardPage.enableBet();
			} else if (action.contains("Score")) {
				setRoundScore(message);
			}
		} else {
			action = command[1].substring(7);
			if (action.contains("Wait")) {
				isTurn = false;
				gameBoardPage.setSeverinstructions("Player 1 turn to play card");
			} else if (action.contains("Turn")) {
				isTurn = true;
				gameBoardPage.setSeverinstructions("Player 2 turn to play card");
			} else if (action.contains("Display move")) {
				action = action.substring(15);
				player1played.setIcon(
						new ImageIcon(GameBoardPage.class.getResource("/cards_png_zip/resized/" + action + ".png")));
			} else if (action.contains("TWin")) {
				gameBoardPage.setSeverinstructions("You win the bag");
				player1played.setIcon(
						new ImageIcon(GameBoardPage.class.getResource("/cards_png_zip/resized/gray_back.png")));
				player2played.setIcon(
						new ImageIcon(GameBoardPage.class.getResource("/cards_png_zip/resized/gray_back.png")));
			} else if (action.contains("TLoss")) {
				gameBoardPage.setSeverinstructions("You lost the bag");
				player1played.setIcon(
						new ImageIcon(GameBoardPage.class.getResource("/cards_png_zip/resized/gray_back.png")));
				player2played.setIcon(
						new ImageIcon(GameBoardPage.class.getResource("/cards_png_zip/resized/gray_back.png")));
			} else if (action.contains("Bet")) {
				gameBoardPage.setSeverinstructions("Place Bet your bet");
				gameBoardPage.enableBet();
			}else if (action.contains("Score")) {
				setRoundScore(message);
			}
		}

	}

	public void setRoundScore(String message) {
		// TODO Auto-generated method stub
		GameBoardPage gameBoardPage = (GameBoardPage) container.getComponent(6);
		gameBoardPage.setPlayersscore(message);
	}
}
