package pobj.tme5;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


/**
 * Cette classe est une representation d'un NaiveMultiSet generique T.
 * Chaque objet T que l'on appelera key seront stocke dans une ArrayList.
 * Leur occurences n'etant pas stocke , il faudra les calculer a l'aide des methodes.
 * @param <T>
 */
public class NaiveMultiSet<T> extends AbstractCollection<T> implements MultiSet<T>{
	private List<T> l = new ArrayList<T>();
	private KeyComparator<T> comp = new KeyComparator<T>(this);

	public NaiveMultiSet(){}
	
	public NaiveMultiSet(ArrayList<T> copy){
		l = new ArrayList<T>(copy);
	}
	
	@Override
	public boolean add(T e, int count) {
		if(count <= 0) throw new IllegalArgumentException("Il faut entrer un nombre strictement positif");
		for(int i = 1; i <= count; i++)
			l.add(e);
		return true;
	}

	@Override
	public boolean add(T e){
		l.add(e);
		return true;
	}
	
	@Override
	public boolean remove(Object e, int count) {
		if(count <= 0) throw new IllegalArgumentException("Il faut entrer un nombre strictement positif");
		for(int i = 1; i <= count; i++)
			l.remove(e);
		return true;
	}
	
	@Override
	public boolean remove(Object e){
		l.remove(e);
		return true;
	}
	
	@Override
	public int count(T o) {
		int occurence = 0;
		for(int i = 0; i < l.size(); i++){
			if(l.get(i).equals(o))
				occurence++;
		}
		return occurence;
	}

	@Override
	public List<T> elements() {
		List<T> bis = new ArrayList<>(l);
		Set<T> hs = new HashSet<T>();
		hs.addAll(bis);
		bis.clear();
		bis.addAll(hs);
		bis.sort(comp);
		for(int i = 0; i < 10; i++)
			System.out.print(bis.get(i)+" ");
		System.out.print("\n");
		return bis;
	}


	@Override
	public int size() {
		return l.size();
	}

	@Override
	public Iterator<T> iterator() {
		return l.iterator();
	}
	
	public List<T> getList(){
		return l;
	}

	@Override
	/**
	 * Methode qui test la validite de notre structure. Or ici notre structure de NaiveMultiSet n'est qu'une liste.
	 * En consequent, il n'y a pas grand chose a verifier mis a part le fait que la liste ne soit pas vide au moins.
	 */
	public boolean isConsistent() {
		return !l.isEmpty();
	}
}
