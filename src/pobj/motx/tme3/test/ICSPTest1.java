package pobj.motx.tme3.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import pobj.motx.tme1.Grille;
import pobj.motx.tme1.GrilleLoader;
import pobj.motx.tme1.GrilleMots;
import pobj.motx.tme2.Dictionnaire;
import pobj.motx.tme2.GrillePotentiel;
import pobj.motx.tme2.test.GrillePotentielTest;
import pobj.motx.tme3.MotX;
import pobj.motx.tme3.csp.CSPSolver;

public class ICSPTest1{
	@Test
	public void testMedium() {

		Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");
		Grille gr = GrilleLoader.loadGrille("data/medium.grl");

		GrilleMots grille = new GrilleMots(gr);

		GrillePotentiel gp = new GrillePotentiel(grille, gut);
		
		MotX problem = new MotX(gp);
		
		CSPSolver solution= new CSPSolver();
		
		System.out.println("Test  du solver sur grille medium");
		
		while (solution.getProblem_solved() == 0){
			problem = (MotX) solution.solve(problem);
		}
		System.out.println(problem.getGrillePot().getGrilleMots().getGrille());
	}
	
	@Test
	public void testEnonce() {

		Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");
		Grille gr = GrilleLoader.loadGrille("data/enonce.grl");

		GrilleMots grille = new GrilleMots(gr);

		GrillePotentiel gp = new GrillePotentiel(grille, gut);
		
		MotX problem = new MotX(gp);
		
		CSPSolver solution= new CSPSolver();
		
		System.out.println("Test  du solver sur grille enonce");
		
		while (solution.getProblem_solved() == 0){
			problem = (MotX) solution.solve(problem);
		}
		System.out.println(problem.getGrillePot().getGrilleMots().getGrille());
	}
	
	@Test
	public void testSplit() {

		Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");
		Grille gr = GrilleLoader.loadGrille("data/split.grl");

		GrilleMots grille = new GrilleMots(gr);

		GrillePotentiel gp = new GrillePotentiel(grille, gut);
		
		MotX problem = new MotX(gp);
		
		CSPSolver solution= new CSPSolver();
		
		System.out.println("Test  du solver sur grille split");
		
		while (solution.getProblem_solved() == 0){
			problem = (MotX) solution.solve(problem);
		}
		System.out.println(problem.getGrillePot().getGrilleMots().getGrille());
	}
	
	@Test
	public void testReverse() {

		Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");
		Grille gr = GrilleLoader.loadGrille("data/reverse.grl");

		GrilleMots grille = new GrilleMots(gr);

		GrillePotentiel gp = new GrillePotentiel(grille, gut);
		
		MotX problem = new MotX(gp);
		
		CSPSolver solution= new CSPSolver();
		
		System.out.println("Test  du solver sur grille reverse");
		
		while (solution.getProblem_solved() == 0){
			problem = (MotX) solution.solve(problem);
		}
		System.out.println(problem.getGrillePot().getGrilleMots().getGrille());
	}




	@Test
	public void testHard() {

		Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");
		Grille gr = GrilleLoader.loadGrille("data/hard.grl");
		

		GrilleMots grille = new GrilleMots(gr);

		GrillePotentiel gp = new GrillePotentiel(grille, gut);
		
		MotX problem = new MotX(gp);
		
		CSPSolver solution= new CSPSolver();

		
		System.out.println("Test  du solver sur grille hard");
		
		while (solution.getProblem_solved() == 0){
			problem = (MotX) solution.solve(problem);
		}
	
		System.out.println(problem.getGrillePot().getGrilleMots().getGrille());

	}

	/**
	 * Ce test part d'un grille completement vierge contenant 124 mots, cela prend donc beaucoup
	 * trop de temps à executer quand on test celui là, car beaucoup trop de possibilite.
	 */
	/*@Test
	public void testLarge() {
		Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");
		Grille gr = GrilleLoader.loadGrille("data/large.grl");

		GrilleMots grille = new GrilleMots(gr);

		GrillePotentiel gp = new GrillePotentiel(grille, gut);
		
		MotX problem = new MotX(gp);
		
		CSPSolver solution= new CSPSolver();
		
		System.out.println("Test  du solver sur grille Large");
		
		while (solution.getProblem_solved() == 0){
			problem = (MotX) solution.solve(problem);
		}

	}*/

	@Test
	public void testLarge3() {
		Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");
		Grille gr = GrilleLoader.loadGrille("data/large3.grl");

		GrilleMots grille = new GrilleMots(gr);

		GrillePotentiel gp = new GrillePotentiel(grille, gut);
		
		MotX problem = new MotX(gp);
		
		CSPSolver solution= new CSPSolver();
		
		System.out.println("Test  du solver sur grille Large3");
		
		while (solution.getProblem_solved() == 0){
			problem = (MotX) solution.solve(problem);
		}
		
		System.out.println(problem.getGrillePot().getGrilleMots().getGrille());
	}

	@Test
	public void testLarge2() {

		Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");
		Grille gr = GrilleLoader.loadGrille("data/large2.grl");

		GrilleMots grille = new GrilleMots(gr);

		GrillePotentiel gp = new GrillePotentiel(grille, gut);
		
		MotX problem = new MotX(gp);
		
		CSPSolver solution= new CSPSolver();
		
		System.out.println("Test  du solver sur grille Large2");
		
		while (solution.getProblem_solved() == 0){
			problem = (MotX) solution.solve(problem);
		}
		System.out.println(problem.getGrillePot().getGrilleMots().getGrille());
	}

	@Test
	public void testLarge4() {

		Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");
		Grille gr = GrilleLoader.loadGrille("data/large4.grl");

		assertEquals(20, gr.nbCol());
		assertEquals(20, gr.nbLig());

		GrilleMots grille = new GrilleMots(gr);

		GrillePotentiel gp = new GrillePotentiel(grille, gut);
		
		MotX problem = new MotX(gp);
		
		CSPSolver solution= new CSPSolver();
		
		System.out.println("Test  du solver sur grille Large4");
		
		while (solution.getProblem_solved() == 0){
			problem = (MotX) solution.solve(problem);
		}
		System.out.println(problem.getGrillePot().getGrilleMots().getGrille());
	}
	
	@Test
	public void testEasy2() {

		Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");
		// grille 6x5, mots sans croisement
		Grille gr = GrilleLoader.loadGrille("data/easy2.grl");


		GrilleMots grille = new GrilleMots(gr);

		GrillePotentiel gp = new GrillePotentiel(grille, gut);

		System.out.println("Test du solveur sur la grilleEasy2:");
		
		MotX problem = new MotX(gp);
		
		CSPSolver solution= new CSPSolver();
		
		while (solution.getProblem_solved() == 0){
			problem = (MotX) solution.solve(problem);
		}
		
		System.out.println(problem.getGrillePot().getGrilleMots().getGrille());
	}
	
	@Test
	public void testEasy() {
		Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");
		// grille 6x5, mots sans croisement
		Grille gr = GrilleLoader.loadGrille("data/easy.grl");

		GrilleMots grille = new GrilleMots(gr);

		GrillePotentiel gp = new GrillePotentiel(grille, gut);


		System.out.println("Test du solveur sur la GrilleEasy.");
		
		MotX problem = new MotX(gp);
		
		CSPSolver solution= new CSPSolver();
		
		while (solution.getProblem_solved() == 0){
			problem = (MotX) solution.solve(problem);
		}
		
		System.out.println(problem.getGrillePot().getGrilleMots().getGrille());
	}
}
