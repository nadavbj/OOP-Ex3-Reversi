package game;

public enum SquareState {
	blank,white,black,securityWall;
	public SquareState opposite(){
		if(this==black)
			return white;
		if(this==white)
			return black;
		return securityWall;
	}
}
