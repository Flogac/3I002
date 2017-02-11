package pobj.motx.tme1;

/**
 * Case d'une GrilleMot
 * @author Florent
 *
 */
public class Case {

	/**
	 * Contenance : ce que contient le 
	 */
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
	
	public boolean sameCase(Case b){
		if(abs == b.abs && ord == b.ord){
			return true;
		}
		return false;
	}
	
}
