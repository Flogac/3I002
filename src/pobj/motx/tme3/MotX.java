package pobj.motx.tme3;

import java.util.ArrayList;
import java.util.List;

import pobj.motx.tme1.Mot;
import pobj.motx.tme2.GrillePotentiel;

/**
 * Cette classe represente un objet MotX qui implemente ICSP
 * Elle regroupe l'ensemble des variables du probleme pour permettre
 * de les traiter.
 * Elle a aussi une reference sur la grillepotentiel 
 */
public class MotX implements ICSP{
	List<IVariable> dicoVar;
	GrillePotentiel gp;
	

	/**
	 * Construction : a partir d'une grillePotentiel, cree le nombre de IVariable necéssaire à la 
	 * resolution du probleme, en effet, si un mot a une case vide, il faut en cree une .
	 * @param gp une grille potentiel.
	 */
	public MotX(GrillePotentiel gp){
		Mot m;
		this.gp = gp;
		dicoVar = new ArrayList<IVariable>();
		for(int i = 0 ; i < gp.getGrilleMots().getMots().size() ; i++){
			m = gp.getGrilleMots().getMots().get(i);
			if(m.caseVide() == true){
				dicoVar.add(new DicoVariable(i,gp));
			}
		}
	}

	@Override
	public List<IVariable> getVars() {
		return dicoVar;
	}

	@Override
	public boolean isConsistent() {
	
		return !gp.isDead();
	}

	/**
	 * Cette methode permet , pour une IVariable passe en paramtre
	 * de l'enlever du probleme en fixant un mot du dictionnaire potentiel 
	 * tout en respectant l'ensemble des contraintes . on obtient donc un probleme avec
	 * une IVariable en moins, d'ou le retour d'un objet MotX.
	 */
	@Override
	public ICSP assign(IVariable vi, String val) {
		DicoVariable bis;
		if(vi instanceof DicoVariable){
			bis = (DicoVariable) vi;
		}
		bis = (DicoVariable) vi;
		return new MotX( gp.fixer(bis.getIndex(), val) ) ;
	}
	
	public GrillePotentiel getGrillePot(){
		return gp;
	}
}
