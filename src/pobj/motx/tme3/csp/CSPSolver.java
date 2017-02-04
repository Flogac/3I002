package pobj.motx.tme3.csp;

import pobj.motx.tme3.DicoVariable;
import pobj.motx.tme3.ICSP;
import pobj.motx.tme3.IVariable;

public class CSPSolver {
	private int problem_solved = 0;

	public ICSP solve(ICSP problem) {
		System.out.println("\n Solve : \n" + problem);
		// Condition terminale : succ�s
		if (problem.getVars().isEmpty()) {
			System.out.println("Probl�me r�solu.\n");
			problem_solved = 1;
			return problem;
		}
		// condition terminale : �chec sur cette branche
		if (!problem.isConsistent()) {
			problem_solved = -1;
			System.out.println("Probl�me invalide.");
			return problem;
		} else {
			System.out.println("Probl�me valide.");
		}
		// On choisit une variable arbitraire, ici la premi�re
		// On est garantis que ! getVars().isEmpty(), test� au dessus
		IVariable vi = problem.getVars().get(0);

		ICSP next = null;
		// On est garantis que toute variable a un domaine non nul
		for (String val : vi.getDomain()) {
			System.out.println("Fixe var :" + ((DicoVariable) vi).getIndex() + " � " + val);
			next = problem.assign(vi, val);
			next = solve(next);
			if (next.isConsistent()) {
				return next;
			} else {
				System.out.println("Essai valeur suivante.");
			}
		}
		System.out.println("Backtrack sur variable "+ vi);
		return next;
	}
	
	public int getProblem_solved(){
		return problem_solved;
	}


}