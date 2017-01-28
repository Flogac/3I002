package pobj.motx.tme2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Un ensemble de mots.
 *
 */
public class Dictionnaire {

	// stockage des mots
	private List<String> mots = new ArrayList<>();


	/**
	 * Ajoute un mot au Dictionnaire, en dernière position.
	 * @param mot à ajouter, il sera stocké en minuscules (lowerCase)
	 */
	public void add(String mot) {
		mots.add(mot.toLowerCase());
	}

	/**
	 * Taille du dictionnaire, c'est à dire nombre de mots qu'il contient.
	 * @return la taille
	 */
	public int size() {
		return mots.size();
	}
	
	/**
	 * Accès au i-eme mot du dictionnaire.
	 * @param i l'index du mot recherché, compris entre 0 et size-1.
	 * @return le mot à cet index
	 */
	public String get(int i) {
		return mots.get(i);
	}

	/**
	 * Rend une copie de ce Dictionnaire.
	 * @return une copie identique de ce Dictionnaire
	 */
	public Dictionnaire copy () {
		Dictionnaire copy = new Dictionnaire();
		copy.mots.addAll(mots);
		return copy;
	}

	/**
	 * Retire les mots qui ne font pas exactement "len" caractères de long.
	 * Attention cette opération modifie le Dictionnaire, utiliser copy() avant de filtrer pour ne pas perdre d'information.
	 * @param len la longueur voulue 
	 * @return le nombre de mots supprimés
	 */
	public int filtreLongueur(int len) {
		List<String> cible = new ArrayList<>();
		int cpt=0;
		for (String mot : mots) {
			if (mot.length() == len)
				cible.add(mot);
			else
				cpt++;
		}
		mots = cible;
		return cpt;
	}
	
	
	/**
	 * Retire les mots qui n'ont pas le caract�re c � la iieme place.
	 * Attention cette opération modifie le Dictionnaire, utiliser copy() avant de filtrer pour ne pas perdre d'information.
	 * @param le char c en question, et i la position 
	 * @return le nombre de mots supprimees.
	 */
	public int filtreParLettre(char c, int i){
		List<String> cible = new ArrayList<>();
		int cpt = 0;
		for (String mot : mots){
			if(mot.charAt(i) == c) {
				cible.add(mot);
			} else {
				cpt++;
			}
		}
		mots = cible;
		return cpt;
	}

	
	@Override
	public String toString() {
		if (size() == 1) {
			return mots.get(0);
		} else {
			return "Dico size =" + size();
		}
	}
	
	/**
	 * Cette method construit un objet de type
	 * Dictionnaire en lisant un fichier passé en argument
	 * elle retourne un dictionnaire.
	 * @param path 
	 * @return
	 */
	public static Dictionnaire loadDictionnaire(String path){
		Dictionnaire dico = new Dictionnaire();
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))){
			for (String line = br.readLine() ; line != null ; line = br.readLine() ) {
				dico.add(line);
			}
		} catch (IOException e) {
			// Problème d'accès au fichier.
			e.printStackTrace();
		}
		return dico;
		
	}
	
	
	/**
	 * Cette methode cree un ensemble de lettre possible en fonction
	 * du dictionnaire de mot potentiel pour un mot de la grille et de 
	 * la position de la lettre dans ce mot.
	 * @param position de la lettre dans le mot
	 * @return l'ensemble de lettre potentiel.
	 */
	public EnsembleLettre lettrePossible(int position){
		EnsembleLettre ens = new EnsembleLettre();
		for(int i = 0 ; i < mots.size() ; i++){
			ens.add(mots.get(i).charAt(position));
		}
		return ens;
	}
	
	/**
	 * a partir de l'ensemble trouve dans la methode precedente 
	 * qui aura ete purge par la methode reduce dans CroixContrainte
	 * on va pouvoir filtrer le dictionnaire du mot en question 
	 * @param index permet de trouver le dictionnaire correspondant au mot.
	 * @param ens l'ensemble des lettres potentiel du mot.
	 * @return le nombre de mots supprime dans le dictionnaire.
	 */
	public int filtrerParEns(int index, EnsembleLettre ens){
		List<String> cible = new ArrayList<>();
		int cpt = 0;
		for (String mot : mots){
			if(ens.contains(mot.charAt(index)) == true){
				cible.add(mot);
			} else {
				cpt++;
			}
		}
		mots = cible;
		return cpt;
	}
	
}