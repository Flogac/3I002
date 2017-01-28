package pobj.motx.tme2;

import pobj.motx.tme1.Mot;

public class CroixContrainte implements IContrainte {

	int m1;
	int c1;
	int m2;
	int c2;
	
	public CroixContrainte( int m1 , int c1 , int m2 , int c2){
		
	}
	
	@Override
	public int reduce(GrillePotentiel grille) {
		Dictionnaire DicoM1 = grille.getMotsPot().get(m1);
		Dictionnaire DicoM2 = grille.getMotsPot().get(m2);
		EnsembleLettre l1 = DicoM1.lettrePossible(c1);
		EnsembleLettre l2 = DicoM2.lettrePossible(c2);
		l1.intersection(l2.getLettres());
		DicoM1.filtrerParEns(c1, l1);
		DicoM2.filtrerParEns(c2, l1);
		return 0;
	}

}
