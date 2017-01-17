package pobj.motx.tme1;

public class Case {

	private char contenance = ' ';
	private int abs;
	private int ord;
	
	public Case(int lig, int col, char c){
		abs = col;
		ord = lig;
		contenance = c;
	}
	
	public int getLig(){
		return ord;
	}
	
	public int getCol(){
		return abs;
	}
	public char getChar(){
		return contenance;
	}

	public void setChar(char c){
		contenance = c;
	}
	
	public boolean isVide(){
		if( contenance == ' ' ) return true;
		return false;
	}
	
	public boolean isPleine(){
		if( contenance == '*' ) return true;
		return false;
	}
	
}
