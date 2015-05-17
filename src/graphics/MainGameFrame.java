package graphics;

import game.Board;
import game.SquareState;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainGameFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGameFrame frame = new MainGameFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
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
					break;
				case blank:
					g.setColor(Color.lightGray);
					break;
				case securityWall:
					g.setColor(Color.yellow);
					break;
				default:
					break;
				}
				g.fillRect(i*getWidth()/board.boardState.length, j*getHeight()/board.boardState[0].length, getWidth()/board.boardState.length, getHeight()/board.boardState[0].length);
			}
		}
		super.paint(g);
	}
	/**
	 * Create the frame.
	 */
	public MainGameFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(new BoardPanel());
		
		
		board=new Board();
		board.boardState=new SquareState[8][8];
		for (int i = 0; i < board.boardState.length; i++) {
			for (int j = 0; j < board.boardState[0].length; j++) {
				switch ((int)(Math.random()*4)+1){
				case 1:
					board.boardState[i][j]=SquareState.white;
					break;
				case 2:
					board.boardState[i][j]=SquareState.black;
					break;
				case 3:
					board.boardState[i][j]=SquareState.blank;
					break;
				case 4:
					board.boardState[i][j]=SquareState.securityWall;
					break;
					
				}
			}
		}
	}

}
