package pobj.motx.tme2;

import java.util.ArrayList;
import java.util.List;

/**
 * Representation d'un Ensemble de lettres potentiellement
 * acceptable pour un mot donne dans une grille
 */
public class EnsembleLettre {
	private List<Character> lettres;
	
	public EnsembleLettre(){
		lettres = new ArrayList<Character>();
	}
	
	/**
	 * Ajoute un character c dans la liste s'il n'y est pas deja
	 * @param c le character a ajouter
	 */
	public void add(Character c){
		if(lettres.contains(c) == false)
			lettres.add(c);
	}
	
	/**
	 * Regarde si l'ensemble de lettre contient le
	 * character c
	 * @param c
	 * @return true si contient, false sinon
	 */
	public boolean contains(Character c){
		return lettres.contains(c);
	}
	
	
	/**
	 * cette methode regarde l'intersection de deux ensemble , l'appelant
	 * et l'argument, et range cette intersection dans l'appelant.
	 * @param b le deuxieme ensemble.
	 */
	public void intersection(List<Character> b){
		lettres.retainAll(b);
	}
	
	public int  getSizeLettres(){
		return lettres.size();
	}
	
	public List<Character> getLettres(){
		return lettres;
	}
	
	public EnsembleLettre copy(){
		EnsembleLettre copy = new EnsembleLettre();
		for(int i = 0; i < lettres.size(); i++){
			copy.lettres.set(i,lettres.get(i));
		}
		return copy;
	}
}
