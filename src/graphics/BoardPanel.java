package graphics;

import java.awt.Graphics;

import game.Board;

import javax.swing.JPanel;

public class BoardPanel extends JPanel {
	Board board;
	@Override
	public void paint(Graphics g) {
		for (int i = 0; i < board.boardState.length; i++) {
			for (int j = 0; j < board.boardState[0].length; j++) {
				// g.draw somthing
			}
		}
		super.paint(g);
	}
}
