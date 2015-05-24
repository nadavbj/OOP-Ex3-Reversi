package graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import game.Board;
import game.ComputerPlayr;
import game.SquareState;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


public class GameFrame extends JFrame {
	BoardPanel boardPanel;
	int playersLevel=1;
	public  GameFrame() {


		JMenuBar mb = new JMenuBar();
		JMenu menu = new JMenu ("Menu");
		mb.add(menu);
		JMenu help = new JMenu ("Help");
		mb.add(help);
		JMenu level = new JMenu ("Level");
		JMenu player = new JMenu ("Players");
		JMenuItem newGame = new JMenuItem("New game");
		menu.add(newGame);
		JMenuItem open = new JMenuItem("Open");
		menu.add(open);
		JMenuItem save = new JMenuItem("Save");
		menu.add(save);
		JMenuItem exit = new JMenuItem("Exit");
		menu.add(exit);
		JMenuItem sum = new JMenuItem("About");
		help.add(sum);
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});

		newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new  BoardPanel (8,8, new ComputerPlayr(SquareState.black,1), null);
			}});
		sum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JOptionPane.showMessageDialog(null, "The game Reversi: "+ '\n' +
						" is a game based on a grid with eight rows and eight columns,"+ '\n' +
						" played between you and the computer, by adding pieces with two sides: black and white." + '\n' +
						"  At the beginning of the game there are 4 pieces in the grid, "+ '\n' +
						"the player with the black pieces is the first one to place his piece on the board."+ '\n' +
						"  Each player must place a piece in a position that there exists at least one "+ '\n' +
						"straight (horizontal, vertical, or diagonal) line between the new piece and another piece"+ '\n' +
						" of the same color, with one or more contiguous opposite pieces between them.");
			}
		});

		menu.addSeparator();

		for (int i=1; i<=8 ; i++){
			JMenuItem levelNum = new JMenuItem (""+i);
			level.add(levelNum);
			levelNum.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					playersLevel=Integer.parseInt(((JMenuItem)e.getSource()).getText());
					try{
						boardPanel.getPlayer1().setLevel(playersLevel);
						boardPanel.getPlayer2().setLevel(playersLevel);
					}
					catch(Exception ex)
					{}
				}});
		}
		menu.add(level);

		player.setMnemonic(KeyEvent.VK_S);
		JMenuItem player1 = new JMenuItem("Guest VS Guest");
		player1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boardPanel.setPlayer1(null);
				boardPanel.setPlayer2(null);
			}});
		player.add(player1);
		JMenuItem player2 = new JMenuItem("Guest VS Computer");
		player2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boardPanel.setPlayer1(null);
				boardPanel.setPlayer2(new ComputerPlayr(SquareState.black,playersLevel ));
			}});
		player.add(player2);

		JMenuItem player3 = new JMenuItem("Computer VS Computer");
		player3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boardPanel.setPlayer1(new ComputerPlayr(SquareState.white,playersLevel ));
				boardPanel.setPlayer2(new ComputerPlayr(SquareState.black,playersLevel ));
			}});
		player.add(player3);

		menu.add(player);

		this.setJMenuBar(mb);

		try{
			//Read from file
			throw new RuntimeException();
		}
		catch(Exception e){
			boardPanel=new BoardPanel(null,new ComputerPlayr(SquareState.black, 1));
		}
		setSize(500, 500);
		setContentPane(boardPanel);}



	public static void main(String[] args) {
		new GameFrame().setVisible(true);

	}
}