package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import game.Board;
import game.SquareState;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class BoardPanel extends JPanel {
	Board board;
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
	g.fillRect(9, 9, 9, 9);
		for (int i = 0; i < board.boardState.length; i++) {
			for (int j = 0; j < board.boardState[0].length; j++) {
				switch (board.boardState[i][j]) {
				case black:
					g.setColor(Color.BLACK);
					break;
				case white:
					g.setColor(Color.WHITE);
				case blank:
					g.setColor(Color.lightGray);
				case securityWall:
					g.setColor(Color.yellow);
				default:
					break;
				}
				g.fillRect(i*getWidth()/board.boardState.length, j*getHeight()/board.boardState[0].length, getWidth()/board.boardState.length, getHeight()/board.boardState[0].length);
			}
		}
		super.paint(g);
	}
	public static void main(String[] args) {
		
		JFrame tmp=new JFrame();
		tmp.setVisible(true);
		JPanel contentPane = new BoardPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		tmp.setContentPane(contentPane);
	}
	public BoardPanel() {
		board=new Board();
		board.boardState=new SquareState[8][8];
		for (int i = 0; i < board.boardState.length; i++) {
			for (int j = 0; j < board.boardState[0].length; j++) {
				switch ((int)(Math.random()*4)+1){
				case 1:
					board.boardState[i][j]=SquareState.white;
				case 2:
					board.boardState[i][j]=SquareState.black;
				case 3:
					board.boardState[i][j]=SquareState.blank;
				case 4:
					board.boardState[i][j]=SquareState.securityWall;
					
				}
			}
		}
		repaint();
	}
}
