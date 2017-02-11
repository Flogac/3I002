package pobj.motx.tme1;

/**
 * Representation d'une grille qui contient plusieur cases
 *
 */
public class Grille {
	private int hauteur;
	private int largeur;
	private Case [][] leGrill;

	/**
	 * Constructeur d'une grille en fonction des parametres:
	 * @param hauteur
	 * @param largeur
	 * Initialise la grille avec des cases vides.
	 */
	public Grille(int hauteur, int largeur){
		this.hauteur = hauteur;
		this.largeur = largeur;
		leGrill = new Case [hauteur][largeur];
		for( int i = 0 ; i < hauteur ; i++ )
			for( int j = 0 ; j < largeur ; j++ )
				leGrill[i][j] = new Case( i , j , ' ' );
		}
	
	/**
	 * Accesseur d'une case en fonction des coordonnee passe
	 * en parametre
	 * @param lig
	 * @param col
	 * @return
	 */
	public Case getCase(int lig, int col){
		return leGrill[lig][col];
	}
	
	public String toString(){
		String retour = GrilleLoader.serialize( this , false );
		return retour.toString();
	}
	
	/**
	 * Accesseur du nombre de ligne
	 * @return hauteur
	 */
	public int nbLig(){
		return hauteur;
	}
	/**
	 * Accesseur du nombre de colonne
	 * @return largeur
	 */
	public int nbCol(){
		return largeur;
	}
	
	/**
	 * Accesseur de la grille 
	 * @return une grille
	 */
	public Case[][] getGrill(){
		return leGrill;
	}
	
	/**
	 * Copy d'une grille vers une nouvelle grille
	 * @return une nouvelle grille
	 */
	public Grille copy(){
		Grille bbq = new Grille( hauteur , largeur);
		for( int i = 0 ; i < hauteur ; i++ ){
			for( int j = 0 ; j < largeur ; j++ ){
				bbq.getCase(i,j).setChar(leGrill[i][j].getChar());
			}
		}
		return bbq;
	}
	
}
