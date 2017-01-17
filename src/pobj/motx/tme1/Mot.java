package pobj.motx.tme1;

import java.util.List;

public class Mot {
	private List<Case> lettres;
	
	public Mot(List<Case> lesLettres) {
		lettres = lesLettres;
	}

	public String toString() {
		StringBuilder retour = new StringBuilder();
		int taille = this.size();
		for( int i = 0 ; i < taille ; i++){
			retour.append(lettres.get(i).getChar());
		}
		return retour.toString();
	}
	
	public int size(){
		return ((CharSequence) lettres).length();
	}
	
}
