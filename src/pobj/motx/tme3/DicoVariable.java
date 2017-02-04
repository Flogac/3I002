package pobj.motx.tme3;

import java.util.List;

import pobj.motx.tme2.GrillePotentiel;

public class DicoVariable implements IVariable{
	private int index;
	private GrillePotentiel gp;
	
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
