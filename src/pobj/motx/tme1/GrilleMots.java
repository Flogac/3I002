package pobj.motx.tme1;

import java.util.ArrayList;
import java.util.List;

public class GrilleMots {

	private List<Mot> mots;
	Grille leGrill;
	
	public GrilleMots (Grille grille){
		leGrill = grille;
		
	}
	
	public List<Mot> getMots(){
		
	}
	
	public int getNbHorizontal(){
		
	}
	
	public int getNbHorizontal(){
		
	}
	
	private List<Case> getLig (int lig){
		List<Case> retour = new ArrayList<Case>();
		for( int i = 0 ; i < leGrill.nbCol() ; i++ )
			retour.add( leGrill.getCase(lig, i));
	}
	
	private List<Case> getCol (int col){
		List<Case> retour = new ArrayList<Case>();
		for( int i = 0 ; i < leGrill.nbLig() ; i++ )
			retour.add( leGrill.getCase(i, col));
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
}
