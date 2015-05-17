package game;

import java.util.Vector;

public class Board {
	public SquareState[][]boardState;

	public Board(int width, int hight) {
		// TODO Auto-generated constructor stub
	}

	public boolean makeMove(move m){


		return true;
	}
	public Vector<move> allPosibleMoves(SquareState player){
		return null;

	}
	public Vector<move> allPosibleMovesForSquare(int x,int y){
		return null;

	}
	public SquareState win(){
		
		return SquareState.blank;
		
	}
	
	
	protected Board clone(){
		
		return null;
	}
}
