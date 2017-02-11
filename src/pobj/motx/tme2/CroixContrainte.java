package pobj.motx.tme2;

import pobj.motx.tme1.Mot;

/**
 * Une contrainte de GrilleMot qui permet de 
 * @author Florent
 *
 */
public class CroixContrainte implements IContrainte {

	int m1;
	int c1;
	int m2;
	int c2;
	
	public CroixContrainte( int m1 , int c1 , int m2 , int c2){
		this.m1 = m1;
		this.c2 = c2;
		this.m2 = m2;
		this.c1 = c1;
		
	}
	
	@Override
	public int reduce(GrillePotentiel grille) {
		int cpt = 0;
		Dictionnaire DicoM1 = grille.getMotsPot().get(m1);
		Dictionnaire DicoM2 = grille.getMotsPot().get(m2);
		EnsembleLettre l1 = DicoM1.lettrePossible(c1);
		EnsembleLettre l2 = DicoM2.lettrePossible(c2);
		l1.intersection(l2.getLettres());
		cpt = cpt + DicoM1.filtrerParEns(c1, l1);
		cpt = cpt + DicoM2.filtrerParEns(c2, l1);
		return cpt;
	}

}
