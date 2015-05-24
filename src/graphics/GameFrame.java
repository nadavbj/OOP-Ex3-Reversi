package graphics;

import game.Board;
import game.ComputerPlayr;
import game.SquareState;

import javax.swing.JFrame;
import javax.swing.JPanel;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class GameFrame extends JFrame {
	
	
	
	
	public GameFrame() {
		BoardPanel boardPanel;
		try{
			//Read from file
			throw new NotImplementedException();
		}
		catch(Exception e){
			boardPanel=new BoardPanel(null,new ComputerPlayr(SquareState.black, 1));
		}
		setSize(500, 500);
		setContentPane(boardPanel);
	}

	public static void main(String[] args) {
		new GameFrame().setVisible(true);
	}
}
