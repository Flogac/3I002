package pobj.motx.tme1;

import java.util.ArrayList;
import java.util.List;

public class GrilleMots {
	private List<Mot> mots;
	private Grille grille;
	private int nbMotHoriz;
	
	public GrilleMots (Grille grille){
		mots = new ArrayList<Mot>();
		this.grille = grille;
		for (int i = 0; i < grille.nbLig(); i++){
			chercheMots(getLig(i));
		}
		nbMotHoriz = mots.size();
		for (int j = 0; j < grille.nbCol(); j++){
			chercheMots(getCol(j));
		}
	}
	
	
	private List<Case> getLig (int lig){
		List<Case> cases = new ArrayList<Case>();
		for(int j = 0 ; j < grille.nbCol() ; j++){
			cases.add(grille.getCase(lig, j));
		}
		return cases;
	}
	
	private List<Case> getCol (int col){
		List<Case> cases = new ArrayList<Case>();
		for(int i = 0 ; i < grille.nbLig() ; i++){
			cases.add(grille.getCase(i, col));
		}
		return cases;
	}
	
	private void chercheMots(List<Case> cases){
		Mot motInit = new Mot();
		Case c ;
		for(int i = 0; i < cases.size(); i++){
			c = cases.get(i);
			if(c.isPleine() == false){
				motInit.addCases(c);
			}else{
				if(motInit.size()>1){
					mots.add(motInit);
				}
				motInit = new Mot();
			}
		}
		if(motInit.size()>1){
			mots.add(motInit);
		}
	}
	
	public List<Mot> getMots(){
		return mots;
	}
	
	public int getNbHorizontal(){
		return nbMotHoriz;
	}
	
	public String toString(){
		StringBuilder retour = new StringBuilder();
		for (int i = 0; i < mots.size(); i++){
			retour.append(mots.get(i));
		}
		return retour.toString();
	}

	
}
