package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import game.Board;
import game.ComputerPlayr;
import game.Move;
import game.SquareState;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class BoardPanel extends JPanel {
	private Board board;
	private SquareState currHumenPlayer=SquareState.white;
	
	private ComputerPlayr player1=null,player2=null;
	
	public ComputerPlayr getPlayer1() {
		return player1;
	}
	public void setPlayer1(ComputerPlayr player1) {
		this.player1 = player1;
		updateListener();
	}
	public ComputerPlayr getPlayer2() {
		return player2;
	}
	public void setPlayer2(ComputerPlayr player2) {
		this.player2 = player2;
		updateListener();
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public SquareState getCurrHumenPlayer() {
		return currHumenPlayer;
	}
	public void setCurrHumenPlayer(SquareState currHumenPlayer) {
		this.currHumenPlayer = currHumenPlayer;
	}
	private Move viewedMove=null;
	@Override
	public void paint(Graphics g) {
		
		super.paint(g);
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
					case empty:
					g.setColor(Color.lightGray);
					break;
					case securityWall:
					g.setColor(Color.yellow);
					break;
					default:
					break;
				}
				g.fillRect(i*getWidth()/board.boardState.length, j*getHeight()/board.boardState[0].length, getWidth()/board.boardState.length, getHeight()/board.boardState[0].length);
				g.setColor(Color.BLACK);
				if(viewedMove!=null){
				if(viewedMove.contain(i, j))
					g.setColor(Color.BLUE);
				}
				g.drawRect(i*getWidth()/board.boardState.length, j*getHeight()/board.boardState[0].length, getWidth()/board.boardState.length, getHeight()/board.boardState[0].length);

			}
		}
	}
	
	public BoardPanel(Board b,ComputerPlayr player1,ComputerPlayr player2) {
		board=b;
		if(player1!=null)
			currHumenPlayer=SquareState.empty;
		this.player1=player1;
		this.player2=player2;
		updateListener();
	}
	
	private void updateListener(){
		for (MouseMotionListener motionListener : getMouseMotionListeners()) {
			removeMouseMotionListener(motionListener);
		}
		for (MouseListener mouseListener : getMouseListeners()) {
			removeMouseListener(mouseListener);
		}
		if(player1==null||player2==null)
addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				boolean flag=false;
				for (Move move : board.allPosibleMoves(currHumenPlayer)) {
				
					if(move.getDestinationX()==BoardPanel.this.getLocation(e.getPoint()).x && move.getDestinationY()==BoardPanel.this.getLocation(e.getPoint()).y)
					{
						flag=true;
						BoardPanel.this.viewedMove=move;
					}
				}
				if(!flag)
					viewedMove=null;
				repaint();
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {}
		});
		
			
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {	}
			
			@Override
			public void mousePressed(MouseEvent e) {}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				boolean flag=false;
				for (Move move : board.allPosibleMoves(currHumenPlayer)) {
				
					if(move.getDestinationX()==BoardPanel.this.getLocation(e.getPoint()).x && move.getDestinationY()==BoardPanel.this.getLocation(e.getPoint()).y)
					{
						flag=true;
						BoardPanel.this.board.makeMove(move);
					}
				}
				if(player1!=null)
					BoardPanel.this.board.makeMove(player1.computeMove(BoardPanel.this.board));
				if(player2!=null &&(flag||player1!=null)){
					
					BoardPanel.this.board.makeMove(player2.computeMove(BoardPanel.this.board));
					}
				else
					if(player1==null)
						currHumenPlayer=currHumenPlayer.opposite();
				
				repaint();
			
			}
		});
	}
	
	public BoardPanel(int hight,int width,ComputerPlayr player1,ComputerPlayr player2) {
		this(new Board(hight,width),player1,player2);
	}
	public BoardPanel(ComputerPlayr player1,ComputerPlayr player2) {
		this(8,8,player1,player2);
	}
	public BoardPanel() {
		this(null,null);
	}
	/**
	 * 
	 * @param position on the board
	 * @return square indexes
	 */
	public Point getLocation(Point position){
	
	 return new Point(position.x/(getWidth()/board.boardState.length),position.y/(getHeight()/board.boardState[0].length));
	}

}
