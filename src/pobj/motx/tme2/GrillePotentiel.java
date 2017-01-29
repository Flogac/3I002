package pobj.motx.tme2;

import java.util.ArrayList;
import java.util.List;

import pobj.motx.tme1.Case;
import pobj.motx.tme1.GrilleMots;
import pobj.motx.tme1.Mot;

public class GrillePotentiel {
	
	private GrilleMots grilleMot;
	private Dictionnaire dicoMot;
	private List<Dictionnaire> motsPot = new ArrayList<Dictionnaire>();
	private List<IContrainte> contraintes = new ArrayList<IContrainte>() ;
	
	public GrillePotentiel(GrilleMots grille, Dictionnaire dico){
		grilleMot = grille;
		System.out.println( grille.getMots().size());
		dicoMot = dico;
		initialisationDomaineDico( dicoMot );
		filtreParLettreDico();
		detection_contraintes();
		this.propage();
	}

	public GrillePotentiel(GrilleMots grille, Dictionnaire dico , List<Dictionnaire> motsPot ){
		grilleMot = grille;
		System.out.println( grille.getMots().size());
		dicoMot = dico;
		this.motsPot = motsPot;
		filtreParLettreDico();
		detection_contraintes();
		this.propage();
	}

	/**
	 * Initialise MotsPot avec les mots potentiels en fonction de
	 * leurs tailles.
	 * @param dico
	 */
	private void initialisationDomaineDico(Dictionnaire dico) {
		List<Dictionnaire> motsTaille = new ArrayList<Dictionnaire>();
		motsTaille = trieDicoTaille( dico );
		int tailleMotMax = motsTaille.size();
		int motEnCoursTaille = 0 ;
		// On recupere la liste des mots
		List<Mot> listeMots = grilleMot.getMots();
		// On passe un par un tous les mots
		for( int i = 0 ; i < listeMots.size() ; i++ ){
			motEnCoursTaille = listeMots.get(i).size();
			motsPot.add( null );
			if(motEnCoursTaille <= tailleMotMax){
				/* On recupere le mot d'indice i listeMots.get(i)
				 * On recupere sa taille listeMots.get(i).size()
				 * On recupere le dictionnaire des mots qui 
				 * correspondent a  sa taille
				 * motsTaille.get(listeMots.get(i).size() - 1 )
				 * On le met a  l'indice i dans motsPot
				*/
				motsPot.set(i, motsTaille.get(motEnCoursTaille  - 1 ).copy());
			} 
		}
	}
	
	
	/**
	 * Cette methode filtre la liste de dictionnaires motsPot
	 * a partir de la liste de mots contenu dans grilleMot.
	 * Pour chaque mots, on filtre son dictionnaire correspondant à travers
	 * une double boucle : une pour parcourir la liste des mots, et une
	 * pour parcourir la liste des lettres (ou case) de chaque mots.
	 * Dans cette deuxieme boucle, si une lettre est presente, alors
	 * on appelle la methode filtreParLettre. 
	 */
	private void filtreParLettreDico(){
		List<Mot> mots = grilleMot.getMots();
		for(int i = 0; i < mots.size(); i++){
			/*System.out.println("le nombre de mots "+mots.size());
			System.out.println("le nombre de mots dans le dico "+motsPot.get(i).size());*/
			List<Case> lettres = mots.get(i).getLettres();
			/*System.out.println("la taille d'un mot "+lettres.size());
			if(lettres.size() == motsPot.get(i).get(i).length()){
				System.out.println("bonne taille");
			} else {
				System.out.println("pas bonne taille");
			}*/
			for(int j = 0; j < lettres.size(); j++){
				if(lettres.get(j).getChar()!=' '){
					motsPot.get(i).filtreParLettre(lettres.get(j).getChar(), j);
				}
			}
		}
	}

	
	/**
	 * On cree une liste de dictionnaires qui contiennent chacun la 
	 * liste des mots d'une certaine taille. La taille correspond a  
	 * l'indice plus un( premier indice : 0 , premiere taille : 1
	 * ).
	 * @param dico
	 * @return
	 */
	private List<Dictionnaire> trieDicoTaille(Dictionnaire dico) {
		int longueurMaxMotDico = 0;
		int tailleDico = dico.size();
		List<Dictionnaire> retour = new ArrayList<Dictionnaire>();
		String leMot;
		for( int i = 0 ; i < tailleDico ; i++ ){
			leMot = dico.get(i);
			while( longueurMaxMotDico < leMot.length() ){
				retour.add( new Dictionnaire() );
				longueurMaxMotDico++;
				//System.out.println("yolo"+longueurMaxMotDico);
				
			}
			retour.get( leMot.length() - 1 ).add( leMot );
		}
		return retour;
	}

	public boolean isDead(){
		for(int i = 0; i < motsPot.size(); i++){
			if(motsPot.get(i).size() == 0){
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @return
	 */
	public List<Dictionnaire> getMotsPot() {
		return motsPot;
	}
	
	/**
	 * 
	 * @param m
	 * @param soluce
	 * @return
	 */
	public GrillePotentiel fixer(int m, String soluce){
		Dictionnaire nouveauDico = dicoMot.copy() ;
		GrilleMots nouvelleGrille = grilleMot.fixer( m , soluce );
		List<Dictionnaire> nouveauxMots = new ArrayList<Dictionnaire>();
		
		for( int i = 0 ; i < motsPot.size() ; i++ ){
			nouveauxMots.add( motsPot.get(i).copy() );
		}
		return new GrillePotentiel( nouvelleGrille, nouveauDico , nouveauxMots  );
		
	}
	
	private boolean propage(){
		int mots_elimines;
		while( true ){
			mots_elimines = 0;
			for( int i = 0 ; i < contraintes.size() ; i++ ){
				mots_elimines += contraintes.get(i).reduce( this );
			}
			if( this.isDead() == true ) return false;
			if( mots_elimines == 0 ) return true;
		}
		
	}

	
	public List<IContrainte> getContraintes(){
		return contraintes;
	}
	

	public void detection_contraintes(){

		IContrainte retour = null;
		for( int i = 0 ; i < grilleMot.getNbHorizontal() ; i++ ){
			for( int j = grilleMot.getNbHorizontal() ; j < grilleMot.getMots().size() ; j++ ){
				retour = grilleMot.getMots().get(i).intersection( grilleMot.getMots().get(j) , i , j );
				if( retour != null ) contraintes.add(retour);
			}
		}
	}


	public GrilleMots getGrilleMots(){
		return grilleMot;
	}
	
}
