package client.clientpages;

import javax.swing.*;


import client.clientcontrollers.GameBoardController;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

public class GameBoardPage extends JPanel {
	
	private JButton Card1;
	private JButton Card2;
	private JButton Card3;
	private JButton Card4;
	private JButton Card5;
	private JButton Card6;
	private JButton Card7;
	private JButton Card8;
	private JButton Card9;
	private JButton Card10;
	private JButton Card11;
	private JButton Card12;
	private JButton Card13;
	private ArrayList<JButton> hand;
	private JLabel player1Played;
	private JButton confirmBetButton;
	private JLabel player2Played;
	private JLabel Severinstructions;
	private JSlider betSlider;
	private JLabel Playersscore;
	
	
	
	
	
	public JLabel getPlayer1Played() {
		return player1Played;
	}

	public void setPlayer1Played(JLabel player1Played) {
		this.player1Played = player1Played;
	}

	public JLabel getPlayer2Played() {
		return player2Played;
	}

	public void setPlayer2Played(JLabel player2Played) {
		this.player2Played = player2Played;
	}

	
	
	
	public JLabel getSeverinstructions() {
		return Severinstructions;
	}

	public void setSeverinstructions(String severinstructions) {
		Severinstructions.setText(severinstructions);
	}

	public GameBoardPage(GameBoardController gbc) {
		hand = new ArrayList<JButton>();
		
		JPanel board = new JPanel();
		board.setLayout(new BorderLayout());
		JPanel Scoreboard = new JPanel();
		Scoreboard.setLayout(new GridLayout(1,2));
		Playersscore = new JLabel("Player 1: 0 | Player 2: 0 ");
		Playersscore.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Severinstructions = new JLabel("Game Starting");
		Severinstructions.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Scoreboard.add(Playersscore);
		Scoreboard.add(Severinstructions);
		JPanel betPanel = new JPanel();
		Scoreboard.setLayout(new GridLayout(1,2));
		betPanel.setLayout(new GridLayout(1, 3, 0, 0));
	
		
		JLabel placebetLabel = new JLabel("Place Bet");
		placebetLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		placebetLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		betPanel.add(placebetLabel);
		confirmBetButton = new JButton("Confirm Bet");
		confirmBetButton.addActionListener(gbc);
		betSlider = new JSlider();
		betSlider.setMajorTickSpacing(1);
		betSlider.setMinimum(1);
		betSlider.setMinorTickSpacing(1);
		betPanel.add(betSlider);
		betSlider.setPaintLabels(true);
		betSlider.setPaintTicks(true);
		betSlider.setSnapToTicks(true);
		
		betSlider.setFont(new Font("Tahoma", Font.PLAIN, 15));
		betSlider.setBackground(Color.WHITE);
		betSlider.setToolTipText("Set Bet");
		betSlider.setForeground(Color.DARK_GRAY);
		betSlider.setMaximum(13);
		betSlider.setValue(1);
		confirmBetButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		betPanel.add(confirmBetButton);
		confirmBetButton.setEnabled(false);
		JPanel handArea = new JPanel();
		handArea.setLayout(new GridLayout(0,2));
		JPanel displayArea = new JPanel();
		displayArea.setLayout(new GridLayout(2,7));
		JPanel playArea = new JPanel();
		playArea.setLayout(new GridLayout(2,1));
		 
	
		
		Card1 = new JButton("Card 1");
		displayArea.add(Card1);
		
		Card2 = new JButton("Card 2");
		displayArea.add(Card2);
		
		Card3 = new JButton("Card 3");
		displayArea.add(Card3);
		
		Card4 = new JButton("Card 4");
		displayArea.add(Card4);
		
		Card5 = new JButton("Card 5");
		displayArea.add(Card5);
		
		Card6 = new JButton("Card 6");
		displayArea.add(Card6);
		
		Card7 = new JButton("Card 7");
		displayArea.add(Card7);
		
		Card8 = new JButton("Card 8");
		displayArea.add(Card8);
		
		Card9 = new JButton("Card 9");
		displayArea.add(Card9);
		
		Card10 = new JButton("Card 10");
		displayArea.add(Card10);
		
		Card11 = new JButton("Card 11");
		displayArea.add(Card11);
		
		Card12 = new JButton("Card 12");
		displayArea.add(Card12);
		
		Card13 = new JButton("Card 13");
		displayArea.add(Card13);
		
		
		player1Played = new JLabel();
		player1Played.setHorizontalAlignment(JLabel.CENTER);
		player2Played = new JLabel();
		player2Played.setHorizontalAlignment(JLabel.CENTER);
		player1Played.setIcon(new ImageIcon(GameBoardPage.class.getResource("/cards_png_zip/resized/gray_back.png")));
		player2Played.setIcon(new ImageIcon(GameBoardPage.class.getResource("/cards_png_zip/resized/gray_back.png")));
		
		Card1.setIcon(new ImageIcon(GameBoardPage.class.getResource("/cards_png_zip/resized/gray_back.png")));
		Card2.setIcon(new ImageIcon(GameBoardPage.class.getResource("/cards_png_zip/resized/gray_back.png")));
		Card3.setIcon(new ImageIcon(GameBoardPage.class.getResource("/cards_png_zip/resized/gray_back.png")));
		Card4.setIcon(new ImageIcon(GameBoardPage.class.getResource("/cards_png_zip/resized/gray_back.png")));
		Card5.setIcon(new ImageIcon(GameBoardPage.class.getResource("/cards_png_zip/resized/gray_back.png")));
		Card6.setIcon(new ImageIcon(GameBoardPage.class.getResource("/cards_png_zip/resized/gray_back.png")));
		Card7.setIcon(new ImageIcon(GameBoardPage.class.getResource("/cards_png_zip/resized/gray_back.png")));
		Card8.setIcon(new ImageIcon(GameBoardPage.class.getResource("/cards_png_zip/resized/gray_back.png")));
		Card9.setIcon(new ImageIcon(GameBoardPage.class.getResource("/cards_png_zip/resized/gray_back.png")));
		Card10.setIcon(new ImageIcon(GameBoardPage.class.getResource("/cards_png_zip/resized/gray_back.png")));
		Card11.setIcon(new ImageIcon(GameBoardPage.class.getResource("/cards_png_zip/resized/gray_back.png")));
		Card12.setIcon(new ImageIcon(GameBoardPage.class.getResource("/cards_png_zip/resized/gray_back.png")));
		Card13.setIcon(new ImageIcon(GameBoardPage.class.getResource("/cards_png_zip/resized/gray_back.png")));
		
			hand.add(Card1);
			hand.add(Card2);
			hand.add(Card3);
			hand.add(Card4);
			hand.add(Card5);
			hand.add(Card6);
			hand.add(Card7);
			hand.add(Card8);
			hand.add(Card9);
			hand.add(Card10);
			hand.add(Card11);
			hand.add(Card12);
			hand.add(Card13);
			Card1.addActionListener(gbc);
			Card2.addActionListener(gbc);
			Card3.addActionListener(gbc);
			Card4.addActionListener(gbc);
			Card5.addActionListener(gbc);
			Card6.addActionListener(gbc);
			Card7.addActionListener(gbc);
			Card8.addActionListener(gbc);
			Card9.addActionListener(gbc);
			Card10.addActionListener(gbc);
			Card11.addActionListener(gbc);
			Card12.addActionListener(gbc);
			Card13.addActionListener(gbc);
			

		
		
		
		
		
		
		
		
		
		
		board.add(playArea);
		board.add(Scoreboard,BorderLayout.NORTH);
		board.add(betPanel,BorderLayout.SOUTH);
		playArea.add(handArea);
		
		handArea.add(player1Played);
		handArea.add(player2Played);
		playArea.add(displayArea);
		this.add(board);
		
	}
	
	public void setPlayersscore(String score) {
		Playersscore.setText(score);
	}

	public JSlider getBetSlider() {
		return betSlider;
	}

	public void setBetSlider(JSlider betSlider) {
		this.betSlider = betSlider;
	}

	public void showUserMove() {
		
	}
	
	public void showOppMove() {
		
	}

	
	public void setCards(ArrayList<String> cards) {
		for (int i = 0; i < 13;i++) {
			hand.get(i).setIcon(new ImageIcon(GameBoardPage.class.getResource("/cards_png_zip/resized/" + cards.get(i) + ".png")));
			hand.get(i).setEnabled(true);
		}
	}

	public void enableBet() {
		// TODO Auto-generated method stub
		confirmBetButton.setEnabled(true);
	}
	public void disableBet() {
		confirmBetButton.setEnabled(false);
	}



}
