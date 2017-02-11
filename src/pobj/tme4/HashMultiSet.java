package pobj.tme4;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public class HashMultiSet<T> extends AbstractCollection<T> implements MultiSet<T> {

	private HashMap<T,Integer> leSet = new HashMap<T,Integer>();
	private int size = 0;
	
	public HashMultiSet(){
	}

	public HashMultiSet( Collection<T> col ){
		
	}
	
	/**
	 * Voir sujet
	 */
	@Override
	public boolean add(Object e, int count) {
		this.size += count;
		leSet.put( (T) e , leSet.get(e) + count );
		return true;
	}
	
	@Override
	public boolean add(Object e) {
		//System.out.println( leSet.get(e) );
		this.size += 1;
		if( leSet.containsKey(e) ){
			leSet.replace((T) e, leSet.get(e) + 1);
			//System.out.println( leSet.get(e) + ".");
			//leSet.put( (T) e , leSet.get(e) + 1 );
			return true;
		}
		leSet.put( (T) e ,  1 );
		//System.out.println( leSet.get(e) );
		return true;
	}
	@Override
	public boolean remove(Object e) {
		this.size -= 1;
		if( leSet.get( e ) <= 1 ){
			leSet.remove( e );
		}else{
			leSet.put( (T) e , leSet.get(e) - 1 );
		}
		return true;
	}
	@Override
	public boolean remove(Object e, int count) {
		size -= count;
		if( leSet.get( e ) <= count ){
			leSet.remove( e );
		}else{
			leSet.put( (T) e , leSet.get(e) - count );
		}
		if( this.size < 0 ) this.size = 0;
		return true;
	}
	@Override
	public int count(Object o) {
		if( leSet.get(o) == null ) return 0;
		return leSet.get(o);
	}
	@Override
	public void clear() {
		leSet.clear();
		
	}
	
	@Override
	public int size() {
		return this.size;
	}
	
	public class HashMultiSetIterator<T> implements Iterator<T>{
		
		private int iterateur = 0; //Pour compter le nombre de trace
		
		private T trace; // Pour mémoriser l'objet
		
		private Set<T> array;  //Pour passer à l'objet suivant.
		
		private HashMap<T, Integer> leSet; // Le Set qu'on itère.
		
		private Iterator<T> arrayIterateur; // L'itérateur pour itérer le set qui permet de passer à l'objet suivant.
		
		public HashMultiSetIterator(HashMultiSet<T> hashMultiSet) {
			array = hashMultiSet.getSet().keySet();
			leSet = hashMultiSet.getSet();
			arrayIterateur = array.iterator();
		}

		/**
		 * Vérifie la nextitude
		 * @return un bool si la nextitude existe
		 */
		@Override
		public boolean hasNext() {
			if( iterateur != 0 ) return true;
			return arrayIterateur.hasNext();
		}

		/**
		 * Vérifie le suivant
		 * @return
		 */
		@Override
		public T next() {
			if( iterateur != 0 ){
				iterateur--;
				return trace;
			}
			if( !arrayIterateur.hasNext() ) throw new NoSuchElementException() ;
			trace = arrayIterateur.next();
			this.iterateur = leSet.get(trace);
			this.iterateur --;
			return trace;
		}

	}

	public HashMap<T, Integer> getSet() {
		return this.leSet;
	}

	@Override
	public Iterator iterator() {
		return new HashMultiSetIterator<T>(this);
	}

	@Override
	public List<T> elements() {
		List<T> retour = new ArrayList<T>();
		Set<T> array = this.getSet().keySet();
		retour.addAll(array);
		int[] tabNombre = new int[retour.size()];
		for( int i = 0 ; i < tabNombre.length ; i++ ) tabNombre[i] = this.count(retour.get(i));
		this.trie( retour, tabNombre , 0 , tabNombre.length - 1);
		for( int i = 0 ; i < tabNombre.length ; i++ ){
			System.out.println( tabNombre[i] );
		}
		return retour;
	}

	private void trie(List<T> retour, int[] tabNombre, int premier, int dernier) {
		// TODO Auto-generated method stub
		
	}
	
}
