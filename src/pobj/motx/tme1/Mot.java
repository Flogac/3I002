package pobj.motx.tme1;

import java.util.ArrayList;
import java.util.List;

import pobj.motx.tme2.CroixContrainte;
import pobj.motx.tme2.IContrainte;

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

	/**
	 * Le premier mot est horizontal, le second vertical.
	 * On vérifie l'intersection des deux et on rajoute la contrainte correspondant.
	 * @param mot
	 * @return
	 */
	public IContrainte intersection(Mot mot , int indice_Mot_h , int indice_Mot_v) {
		List<Case> mot_horiz = this.lettres;
		List<Case> mot_vert = mot.getLettres();
		for(int i = 0; i < mot_horiz.size() ; i++){
			for(int j = 0; j < mot_vert.size() ; j++){
				if(mot_horiz.get(i).sameCase(mot_vert.get(j)) == true){
					if(mot_horiz.get(i).getChar() == ' ')
						return new CroixContrainte( indice_Mot_h , i , indice_Mot_v , j );
				}
			}
		}
		return null;
		
	}
	
	public boolean caseVide(){
		for(int i = 0 ; i<lettres.size() ; i++){
			if(lettres.get(i).isVide() == true){
				return true;
			}
		}
		return false;
	}


}
