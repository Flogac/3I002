package pobj.tme5;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.List;

/**
 * Representation du pattern decorateur pour les type MultiSet. Il a en attribut un MultiSet<T> et implemente l'interface 
 * MultiSet<T>.
 * Ainsi, on peut utiliser ce pattern pour verifier la coherence interne d'un multiSet donne (sans savoir lequel exactmement , naive ou hash)
 * et ne pas activer les assert pour toute les classes.
 */
public class MultiSetDecorator<T> extends AbstractCollection<T> implements MultiSet<T> {
	private final MultiSet<T> decorated ;

	public MultiSetDecorator(MultiSet<T> original){
		this.decorated = original;
	}

	@Override
	public boolean add(T e, int count) {
		boolean res = decorated.add(e, count);
		assert decorated.isConsistent();
		return res;
	}
	
	@Override
	public boolean add(T e){
		boolean res = decorated.add(e);
		assert decorated.isConsistent();
		return res;
	}

	@Override
	public boolean remove(Object e, int count) {
		boolean res = decorated.remove(e, count);
		assert decorated.isConsistent();
		return res;
	}
	
	public boolean remove(Object e){
		boolean res = decorated.remove(e);
		assert decorated.isConsistent();
		return res;
	}

	@Override
	public int count(T o) {
		return decorated.count(o);
	}

	@Override
	public List<T> elements() {
		return decorated.elements();
	}

	@Override
	public Iterator<T> iterator() {
		return decorated.iterator();
	}

	@Override
	public int size() {
		return decorated.size();
	}
	
	/**
	 * Nous avons ici fait le choix de ne pas "migrer" (qu'entendez-vous pas migrer?) la fonction isConsistent:
	 * En effet, cette methode doit être specifique a la classe sur laquelle on va l'utiliser car on a besoin de verifier
	 * la coherence de la structure interne de la classe, or cette structure varie en fonction des classes Naive ou Hash.
	 * On a donc implementer la methode isConsistent dans les deux classes, et ici, on appelle juste la methode isConsistent sur 
	 * notre attribut decorated.
	 */
	public boolean isConsistent(){
		return decorated.isConsistent();
	}
	
	public String toString(){
		return decorated.toString();
	}
	
	public void clear(){
		decorated.clear();
	}
}
