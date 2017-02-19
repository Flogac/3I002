package pobj.tme5.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import pobj.tme5.InvalidMultiSetFormat;
import pobj.tme5.MultiSet;
import pobj.tme5.MultiSetDecorator;
import pobj.tme5.MultiSetParser;
/**
 * Classe de test JUnit qui va tester une succession de remove et add sur un MultiSet construit a l'aide d'un objet
 * de la classe MultiSetParser. On partira donc d'un HashMultiSet qui contient deja des donnees + occurences, et on va 
 * appliquer a ce HashMultiSet, diverses methodes pour tester la coherence fonctionnelle. De plus on utilisera 
 * un objet decorated pour verifier aussi la coherence interne.
 */
public class HashMultisSetTestComplex {
	
	@Test
	public void testRemove() throws InvalidMultiSetFormat{
		System.out.println("debut du test complexe:\n");
		MultiSetParser parser = new MultiSetParser();
		MultiSet<String> ms;
		ms = parser.parse("test.txt"); //[oui:23; java:23; c'est:23; top:23; hello:22; ok:39; hola:29; bonjour:2]
		MultiSetDecorator<String> decorated = new MultiSetDecorator<String>(ms);
		System.out.println("dictionnaire initial:");
		System.out.println(ms);
		
		decorated.add("a");
		decorated.add("a",5);
		decorated.add("bonjour",10);
		
		assertEquals(decorated.count("a"), 6);
		assertEquals(decorated.count("oui"),23);
		assertEquals(decorated.count("bonjour"),12);
		assertEquals(decorated.size(),200);
		
		decorated.remove("a");
		decorated.remove("a",3);
		decorated.remove("oui",23);
		decorated.remove("top",23);
		decorated.add("nul",23);
		
		assertEquals(decorated.count("a"),2);
		assertEquals(decorated.count("oui"),0);
		assertEquals(decorated.size(),173);
		assertEquals(decorated.toString(),"[a:2; java:23; c'est:23; nul:23; hello:22; ok:39; hola:29; bonjour:12]");
		System.out.println("dictionnaire de fin:");
		System.out.println(ms);
	}
}
