package pobj.tme5.test;

import static org.junit.Assert.*;

import org.junit.Test;

import pobj.tme5.HashMultiSet;
import pobj.tme5.MultiSet;
import pobj.tme5.MultiSetDecorator;
/**
 * Classe de tests banals sur de diverses methodes qui modifient la structure interne d'un MultiSet, pour tester la coherence fonctionnel
 */
public class HashMultiSetTest {
	@Test
	public void testRemove(){
		MultiSet<String> m = new HashMultiSet<>();
		MultiSetDecorator<String> decorated = new MultiSetDecorator<String>(m);
		decorated.add("a");
		decorated.add("a",5);
		assertEquals(m.count("a"), 6); 
		decorated.remove("a");
		decorated.remove("a",5);
		assertEquals(m.count("a"),0);
		System.out.println("Test de la fonction Remove: succés\n");
	}
	
	@Test
	public void testSize(){
		MultiSet<String> m = new HashMultiSet<>();
		MultiSetDecorator<String> decorated = new MultiSetDecorator<String>(m);
		decorated.add("a");
		decorated.add("a",5);
		decorated.remove("a");
		decorated.remove("a",3);
		assertEquals(decorated.size(),2);
		System.out.println("Test de la fonction Size: succés\n");
	}
	
	@Test
	public void testToString(){
		MultiSet<String> m = new HashMultiSet<>();
		MultiSetDecorator<String> decorated = new MultiSetDecorator<String>(m);
		decorated.add("a");
		decorated.add("b");
		decorated.add("a",5);
		decorated.remove("a");
		decorated.remove("a",3);
		decorated.remove("a",2);
		assertEquals(decorated.toString(),"[b:1]");
		System.out.println("Test de la fonction ToString: succés\n");
	}
	
	@Test
	public void testClear(){
		MultiSet<String> m = new HashMultiSet<>();
		MultiSetDecorator<String> decorated = new MultiSetDecorator<String>(m);
		decorated.add("a");
		decorated.add("b");
		decorated.add("a",5);
		decorated.remove("a");
		decorated.remove("a",3);
		decorated.count("c");
		decorated.clear();
		assertEquals(decorated.size(),0);
		System.out.println("Test de la fonction Clear: succés\n");
	}
	
	
}
