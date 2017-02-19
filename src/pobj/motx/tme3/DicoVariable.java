package pobj.motx.tme3;

import java.util.List;
import pobj.motx.tme2.GrillePotentiel;

/**
 * Cette classe represente un objet DicoVariable et implemente IVariable
 * chaque objet de ce type est un nouvelle contrainte au solveur
 */

public class DicoVariable implements IVariable{
	private int index;
	private GrillePotentiel gp;
	
	/**
	 * Constructeur d'un dicoVariable
	 * @param index pour savoir de quel mot il est question dans la grille
	 * @param gp reference sur la grillepotentiel
	 */
	public DicoVariable(int index, GrillePotentiel gp){
		this.index = index;
		this.gp =  gp;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder() ;
		sb.append( index );
		sb.append( gp.getGrilleMots().getMots().get(index));
		return sb.toString();
	}
	
	@Override
	public List<String> getDomain() {
		return gp.getMotsPot().get(index).getMots();
	}
	
	public int getIndex(){
		return index;
	}
	
	
}
