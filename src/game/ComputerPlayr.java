package game;

import java.util.Vector;

import com.sun.jndi.url.dns.dnsURLContext;
import com.sun.org.apache.regexp.internal.recompile;

public class ComputerPlayr {
	private SquareState playerColor;
	private int level;
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public ComputerPlayr(SquareState playerColor,int level) {
		this.playerColor=playerColor;
		this.level=level*2;
	}

	public Move computeMove(Board state){
		return computeMove(state,playerColor,0);
	}
	
	private Move computeMove(Board state,SquareState player,int level) {
		Vector<Move>possibleMoves=state.allPosibleMoves(player);
		Vector<Board>possibleStates=new Vector<Board>();
		for (Move m : possibleMoves) {
			Board temp=state.clone();
			temp.makeMove(m);
			possibleStates.add(temp);
		}
		if(level<=0)
		{
			double maxValue=evalute(possibleStates.get(0));
			int maxIndex=0;
			for (int i = 1; i < possibleStates.size(); i++) {
				if(maxValue<evalute(possibleStates.get(i)))
				{
					maxValue=evalute(possibleStates.get(i));
					maxIndex=i;
				}
			}
			return possibleMoves.get(maxIndex);
		}
		double maxValue=player==this.playerColor?Double.MIN_VALUE:Double.MAX_VALUE;
		int maxIndex=0;
		
		for(int i=0;i<possibleStates.size();i++){
			possibleStates.get(i).makeMove(computeMove(possibleStates.get(i), player.opposite(), level-1));
			if(player==this.playerColor)
			{
				if(maxValue>evalute(possibleStates.get(i)))
				{
					maxValue=evalute(possibleStates.get(i));
					maxIndex=i;
				}
			}
			else
				if(maxValue<evalute(possibleStates.get(i)))
				{
					maxValue=evalute(possibleStates.get(i));
					maxIndex=i;
				}
		}
		return possibleMoves.get(maxIndex);
	}

	public double evalute(Board state) {
		double sum=0;
		for (int i = 0; i < state.boardState.length; i++) {
			for (int j = 0; j < state.boardState[0].length; j++) {
				if(state.boardState[i][j]==playerColor)
					sum+=1/(Math.min(i, state.boardState.length-i)+Math.min(j, state.boardState[0].length-j));
				if(state.boardState[i][j]==playerColor.opposite())
					sum-=1/(Math.min(i, state.boardState.length-i)+Math.min(j, state.boardState[0].length-j));
				
			}
		}
		return sum;
	}
}
