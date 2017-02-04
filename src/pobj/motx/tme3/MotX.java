package pobj.motx.tme3;

import java.util.ArrayList;
import java.util.List;

import pobj.motx.tme1.Mot;
import pobj.motx.tme2.GrillePotentiel;

public class MotX implements ICSP{
	List<IVariable> dicoVar;
	GrillePotentiel gp;
	

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
