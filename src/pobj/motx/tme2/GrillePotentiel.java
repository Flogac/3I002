package pobj.motx.tme2;

import java.util.ArrayList;
import java.util.List;

import pobj.motx.tme1.GrilleMots;
import pobj.motx.tme1.Mot;

public class GrillePotentiel {
	
	private GrilleMots grilleMot;
	private Dictionnaire dicoMot;
	private List<Dictionnaire> motsPot = new ArrayList<Dictionnaire>();
	
	public GrillePotentiel(GrilleMots grille, Dictionnaire dico){
		grilleMot = grille;
		System.out.println( grille.getMots().size());
		dicoMot = dico;
		initialisationDomaineDico( dicoMot );
	}

	public GrillePotentiel(GrilleMots grille, Dictionnaire dico , List<Dictionnaire> motsPot ){
		grilleMot = grille;
		System.out.println( grille.getMots().size());
		dicoMot = dico;
		this.motsPot = motsPot;
	}

	/**
	 * Initialise MotsPot avec les mots potenciels en fonction de
	 * leurs tailles.
	 * @param dico
	 */
	private void initialisationDomaineDico(Dictionnaire dico) {
		List<Dictionnaire> motsTaille = new ArrayList<Dictionnaire>();
		motsTaille = trieDicoTaille( dico );
		int tailleMotMax = motsTaille.size();
		int motEnCoursTaille = 0 ;
		// On récupère la liste des mots
		List<Mot> listeMots = grilleMot.getMots();
		// On passe un par un tous les mots
		for( int i = 0 ; i < listeMots.size() ; i++ ){
			motEnCoursTaille = listeMots.get(i).size();
			if(motEnCoursTaille <= tailleMotMax){
				/* On récupère le mot d'indice i listeMots.get(i)
				 * On récupère sa taille listeMots.get(i).size()
				 * On récupère le dictionnaire des mots qui 
				 * correspondent à sa taille
				 * motsTaille.get(listeMots.get(i).size() - 1 )
				 * On le met à l'indice i dans motsPot
				*/
				motsPot.add(motsTaille.get(motEnCoursTaille  - 1 ));
			} else { 
				motsPot.add( null );
			}
		}
	}

	
	/**
	 * On crée une liste de dictionnaires qui contiennent chacun la 
	 * liste des mots d'une certaine taille. La taille correspond à 
	 * l'indice plus un( premier indice : 0 , première taille : 1
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
			while( longueurMaxMotDico <= leMot.length() ){
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
	
}
