package game;

import java.io.Serializable;
import java.util.Vector;

public class Board implements Serializable{
	public SquareState[][]boardState;

	public Board(int width, int hight) {
		boardState=new SquareState[width+2][hight+2];
		for (int i = 0; i < boardState.length; i++) {
			for (int j = 0; j < boardState[0].length; j++) {
				if(i==0||i==boardState.length-1||j==0||j==boardState[0].length-1)
					boardState[i][j]=SquareState.securityWall;
				else
					boardState[i][j]=SquareState.empty;
			}
		}
		boardState[boardState.length/2-1][boardState[0].length/2-1]=SquareState.white;
		boardState[boardState.length/2][boardState[0].length/2-1]=SquareState.black;
		boardState[boardState.length/2-1][boardState[0].length/2]=SquareState.black;
		boardState[boardState.length/2][boardState[0].length/2]=SquareState.white;

	}

	public boolean makeMove(Move m){
		//check the move
		if(boardState[m.getOriginX()][m.getOriginY()]==SquareState.empty||
				boardState[m.getOriginX()][m.getOriginY()]==SquareState.securityWall||
				boardState[m.getDestinationX()][m.getDestinationY()]!=SquareState.empty)
			return false;
		int dir=m.dir(),length=m.length();
		
		
		for (int i = 1; i < length-1; i++) {
			if(boardState[m.getOriginX()+i*Move.DIRECTIONS_MAT[dir][0]][m.getOriginY()+i*Move.DIRECTIONS_MAT[dir][1]]!=boardState[m.getOriginX()][m.getOriginY()].opposite())
				return false;
		}
		for (int i = 1; i < length; i++) {
			boardState[m.getOriginX()+i*Move.DIRECTIONS_MAT[dir][0]][m.getOriginY()+i*Move.DIRECTIONS_MAT[dir][1]]=boardState[m.getOriginX()][m.getOriginY()];
		}
		return true;
	}

	public Vector<Move> allPosibleMoves(SquareState player){
		Vector<Move> result=new Vector<Move>();
		for (int i = 0; i < boardState.length; i++) {
			for (int j = 0; j < boardState[0].length; j++) {
				if(boardState[i][j]==player)
					result.addAll(allPosibleMovesForSquare(i, j));
			}
		}
		return result;
	}
	public Vector<Move> allPosibleMovesForSquare(int x,int y){
		Vector<Move> result=new Vector<Move>();

		if(boardState[x][y]==SquareState.empty||boardState[x][y]==SquareState.securityWall)
			return result;


		for (int i = 0; i < Move.DIRECTIONS_MAT.length; i++) {
			
			int desX=x+Move.DIRECTIONS_MAT[i][0],desY=y+Move.DIRECTIONS_MAT[i][1],lengh=1;
			
			while (boardState[desX][desY]==boardState[x][y].opposite()) {
				lengh++;
				desX+=Move.DIRECTIONS_MAT[i][0];
				desY+=Move.DIRECTIONS_MAT[i][1];
			}
			if(lengh>1 && boardState[desX][desY]==SquareState.empty)
				result.add(new Move(x, y, desX, desY));
		}
		return result;
	}
	public SquareState win(){

		if(allPosibleMoves(SquareState.white).size()>0&&allPosibleMoves(SquareState.black).size()>0)
			return SquareState.empty;
		int white=0,black=0;
		for (int i = 0; i < boardState.length; i++) {
			for (int j = 0; j < boardState[0].length; j++) {
				if(boardState[i][j]==SquareState.white)
					white++;
				if(boardState[i][j]==SquareState.black)
					black++;
			}
		}
		return white>black?SquareState.white:SquareState.black;
	}


	protected Board clone(){
		Board b=new Board(boardState.length-2, boardState[0].length-2);
		for (int i = 0; i < boardState.length; i++) {
			for (int j = 0; j < boardState[0].length; j++) {
				b.boardState[i][j]=boardState[i][j];
			}
		}
		return b;
	}
}
