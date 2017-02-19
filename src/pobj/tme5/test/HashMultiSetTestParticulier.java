package pobj.tme5.test;

import org.junit.Test;

import pobj.tme5.HashMultiSet;
import pobj.tme5.MultiSet;
import pobj.tme5.MultiSetDecorator;
/**
 * Classe de test particulier qui teste les limites de notre structure.
 */
public class HashMultiSetTestParticulier {
	@Test
	/**
	 * Ce test va forcement rater car on ne peut entrer l'argument 0 dans la methode add.
	 */
	public void testAddZero(){
		MultiSet<String> m = new HashMultiSet<>();
		MultiSetDecorator<String> decorated = new MultiSetDecorator<String>(m);
		decorated.add("a",0); // devrait retourner une exception IllegalArgumentException car il faut entrer un entier > 0
		System.out.println("Test de la fonction AddZero: succés\n");
	}
	
	@Test
	/**
	 * Pareil que le test du dessus.
	 */
	public void testRemoveZero(){
		MultiSet<String> m = new HashMultiSet<>();
		MultiSetDecorator<String> decorated = new MultiSetDecorator<String>(m);
		decorated.add("a",1); 
		decorated.remove("a",0); // devrait retourner une exception IllegalArgumentException car il faut entrer un entier > 0
		System.out.println("Test de la fonction RemoveZero: succés\n");
	}
	
	@Test
	/**
	 * Pareil que le test du dessus
	 */
	public void testAddAndRemoveN(){
		MultiSet<String> m = new HashMultiSet<>();
		MultiSetDecorator<String> decorated = new MultiSetDecorator<String>(m);
		decorated.add("a",1); 
		decorated.remove("a",-1); // devrait retourner une exception IllegalArgumentException car il faut entrer un entier > 0
		System.out.println("Test de la fonction AddAndRemoveN: succés\n");
	}
	
	@Test
	/**
	 * Ici l'implementation de notre methode count, fait que si l'element en question n'est pas dans le multiSet, alors on 
	 * retourne 0
	 * Ce test sera donc un succes.
	 */
	public void testCountElementNotInSet(){
		MultiSet<String> m = new HashMultiSet<>();
		MultiSetDecorator<String> decorated = new MultiSetDecorator<String>(m);
		decorated.add("a",1); 
		decorated.count("what");
		System.out.println("Test de la fonction testCountElementNotInSet: succés\n");
	}
	
}
