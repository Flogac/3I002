package pobj.motx.tme1;

import java.util.ArrayList;
import java.util.List;

public class GrilleMots {

	private List<Mot> mots;
	Grille leGrill;
	int nbHorizontal = 0;
	int nbVertical = 0;
	
	public GrilleMots (Grille grille){
		leGrill = grille;
		for( int i = 0 ; i < leGrill.nbCol() ; i++ ){
			chercheMots(getCol(i));
		}
		nbVertical = mots.size();
		for( int i = 0 ; i < leGrill.nbLig() ; i++ ){
			chercheMots(getLig(i));
		}
		nbHorizontal = mots.size() - nbVertical;
	}
	
	public List<Mot> getMots(){
		return mots;
	}
	
	public int getNbHorizontal(){
		return nbHorizontal;
	}
	
	public int getNbVertical(){
		return nbVertical;
	}
	
	private List<Case> getLig (int lig){
		List<Case> retour = new ArrayList<Case>();
		for( int i = 0 ; i < leGrill.nbCol() ; i++ )
			retour.add( leGrill.getCase(lig, i));
		return retour;
	}
	
	private List<Case> getCol (int col){
		List<Case> retour = new ArrayList<Case>();
		for( int i = 0 ; i < leGrill.nbLig() ; i++ )
			retour.add( leGrill.getCase(i, col));
		return retour;
	}
	
	private void chercheMots(List<Case> cases) {
		int tailleMot = 0;
		Case temp;
		List<Case> retour = new ArrayList<Case>();
		for( int i = 1 ; i < cases.size() ; i++ ){
			temp = cases.get(i);
			if( temp.getChar() != '*' ){ 
				tailleMot++;
				retour.add(temp);
			}else{
				if( tailleMot >= 2 ) mots.add( new Mot( retour ) );
				tailleMot=0;
				retour.clear();
			}
		}
	}
	
	public String toString(){
		StringBuilder retour = new StringBuilder();
		for( int i = 0 ; i < mots.size() ; i++ ) retour.append( mots.get(i).toString() + '\n');
		return retour.toString();
	}
}
