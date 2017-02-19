package pobj.tme4;

import java.util.Comparator;

/**
 * Representation d'un comparator generique pour le type T.
 * Ce comparator nous permet d'utiliser la methode sort de java avec n'importe quel
 * objet d'une classe implementant l'interface MultiSet 
 * Il n'est donc pas necessaire que cette classe soit interne a HashMultiSet ou NaiveMultiSet
 * puisqu'elle utilise juste la methode count de ces deux classes et n'a pas besoin de connaitre
 * leur structure interne.
 * @param <T>
 */

public class KeyComparator<T> implements Comparator<T>{
	private MultiSet<T> multiSet;
	
	public KeyComparator(MultiSet<T> multiSet){
		this.multiSet = multiSet;
	}
	
	@Override
	public int compare(Object o1, Object o2) {
		if(multiSet.count((T)o1) > multiSet.count((T)o2))
			return -1;
		if(multiSet.count((T)o1) < multiSet.count((T)o2))
			return 1;
		return 0;
	}
}
