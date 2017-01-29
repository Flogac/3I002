package pobj.motx.tme1;

/**
 * Representatin d'une case de la grille du mot croisé
 * une case contient un charactere, les coordonnées de la case
 */
public class Case {

	private char contenance = ' ';
	private int abs;
	private int ord;
	
	/**
	 * Constructeur d'une case : initialise la case
	 * en fonction des parametres 
	 * @param lig Coordonnee x de la case
	 * @param col Coordonnee y de la case
	 * @param c   Contenu de la case 
	 */
	public Case(int lig, int col, char c){
		abs = col;
		ord = lig;
		contenance = c;
	}
	
	/**
	 * Accesseur de la ligne
	 * @return
	 */
	public int getLig(){
		return ord;
	}
	
	/**
	 * Accesseur de la colonne
	 * @return
	 */
	public int getCol(){
		return abs;
	}
	
	/**
	 * Accesseur du contenu de la case
	 * @return
	 */
	public char getChar(){
		return contenance;
	}

	/**
	 * Modifie le contenu de la case avec la parametre
	 * @param c
	 */
	public void setChar(char c){
		contenance = c;
	}
	
	/**
	 * Test si la case contient un mot
	 * @return
	 */
	public boolean isVide(){
		if( contenance == ' ' ) return true;
		return false;
	}
	
	/**
	 * Test si la case ne contient pas de mot
	 * @return
	 */
	public boolean isPleine(){
		if( contenance == '*' ) return true;
		return false;
	}
	
	/**
	 * Regarde si deux case on les meme coordonnées.
	 * @param b
	 * @return
	 */
	public boolean sameCase(Case b){
		if(abs == b.abs && ord == b.ord){
			return true;
		}
		return false;
	}
	
}
