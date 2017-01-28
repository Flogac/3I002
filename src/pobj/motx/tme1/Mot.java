package pobj.motx.tme1;

import java.util.ArrayList;
import java.util.List;

public class Mot {
	private List<Case> lettres;
	
	
	public Mot(){
		lettres = new ArrayList<Case>();
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
		return lettres.size();
	}
	
	public void addCases(Case c){
		lettres.add(c);
	}
	
	public List<Case> getLettres(){
		return lettres;
	}
	
	public Case getCase( int numero){
		return lettres.get(numero);
	}

}
