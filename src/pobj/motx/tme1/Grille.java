package pobj.motx.tme1;

public class Grille {
	private int hauteur;
	private int largeur;
	private Case [][] leGrill;

	public Grille(int hauteur, int largeur){
		this.hauteur = hauteur;
		this.largeur = largeur;
		leGrill = new Case [hauteur][largeur];
		for( int i = 0 ; i < hauteur ; i++ )
			for( int j = 0 ; j < largeur ; j++ )
				leGrill[i][j] = new Case( i , j , ' ' );
		}
	
	public Case getCase(int lig, int col){
		return leGrill[lig][col];
	}
	
	public String toString(){
		String retour = GrilleLoader.serialize( this , false );
		/*
		StringBuilder retour = new StringBuilder();
		for( int i = 0 ; i < hauteur ; i++ ){
			for( int j = 0 ; j < largeur ; j++ ){
				retour.append( leGrill [i][j].getChar());
			}
			if(i != (hauteur - 1 )) retour.append('\n');
		}*/
		System.out.println(retour.toString());
		return retour.toString();
	}
	
	public int nbLig(){
		return hauteur;
	}
	
	public int nbCol(){
		return largeur;
	}
	
	public Case[][] getGrill(){
		return leGrill;
	}
	
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
