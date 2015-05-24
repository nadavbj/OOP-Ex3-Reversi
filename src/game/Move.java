package game;

import com.sun.org.apache.regexp.internal.recompile;

import sun.net.www.content.text.plain;

public class Move {
	public static final int [][]DIRECTIONS_MAT={{-1,-1},{-1,0},{-1,1},
																						 	{0 , -1}				 ,{ 0 ,1},
																						 	{ 1, -1},   { 1, 0 }, { 1 , 1},};
	private int originX,originY,destinationX,destinationY;

	public int getOriginX() {
		return originX;
	}

	public int getDestinationX() {
		return destinationX;
	}

	
	public int getDestinationY() {
		return destinationY;
	}

	
	public int getOriginY() {
		return originY;
	}

	public Move(int originX,int originY,int destinationX,int destinationY) {
		if(!((destinationX==originX ^ destinationY==originY) || Math.abs(destinationX-originX )==Math.abs(destinationY-originY)))
			throw new RuntimeException("Ilegal move");
		this.destinationX=destinationX;
		this.destinationY=destinationY;
		this.originX=originX;
		this.originY=originY;	
	}
	public Move(int originX,int originY, int length,byte dir) {
		this(originX, originY, originX+length*DIRECTIONS_MAT[dir][0], originY+length*DIRECTIONS_MAT[dir][1]);
	}
	
	public int length() {
		return Math.max(Math.abs(destinationX-originX ), Math.abs(destinationY-originY))+1;
	}
	public int dir(){
		int x= (destinationX-originX)/ (Math.abs(destinationX-originX)==0?1:Math.abs(destinationX-originX));
		int y= (destinationY-originY)/ (Math.abs(destinationY-originY)==0?1:Math.abs(destinationY-originY));
		for (int i = 0; i < DIRECTIONS_MAT.length; i++) {
			if(x==DIRECTIONS_MAT[i][0]&&y==DIRECTIONS_MAT[i][1])
				return i;
		}
		return -1;
	}
	
	public boolean contain(int x,int y) {
		int tempX=originX,tempY=originY;
		for (int i = 0; i < length(); i++) {
			if(x==tempX&&y==tempY)
				return true;
			tempX+=DIRECTIONS_MAT[dir()][0];
			tempY+=DIRECTIONS_MAT[dir()][1];
		}
		return false;
	}
}
