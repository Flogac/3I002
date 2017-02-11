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
	
	public class HashMultiSetIterator<G> implements Iterator<G>{
		
		private int iterateur = 0; //Pour compter le nombre de trace
		
		private G trace; // Pour mÃ©moriser l'objet
		
		private Set<G> array;  //Pour passer Ã  l'objet suivant.
		
		private HashMap<G, Integer> leSet; // Le Set qu'on itÃ¨re.
		
		private Iterator<G> arrayIterateur; // L'itÃ©rateur pour itÃ©rer le set qui permet de passer Ã  l'objet suivant.
		
		public HashMultiSetIterator(HashMultiSet<G> hashMultiSet) {
			array = hashMultiSet.getSet().keySet();
			leSet = hashMultiSet.getSet();
			arrayIterateur = array.iterator();
		}

		/**
		 * VÃ©rifie la nextitude
		 * @return un bool si la nextitude existe
		 */
		@Override
		public boolean hasNext() {
			if( iterateur != 0 ) return true;
			return arrayIterateur.hasNext();
		}

		/**
		 * VÃ©rifie le suivant
		 * @return
		 */
		@Override
		public G next() {
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
	public Iterator<T> iterator() {
		return new HashMultiSetIterator<T>(this);
	}

	/**
	 * Renvoie une liste des éléments par ordre de fréquence.
	 * Le plus fréquent est à l'indice 0, et le moins fréquent est en queue de liste.
	 */
	@Override
	public List<T> elements() {
		List<T> tempo = new ArrayList<T>();
		List<T> retour = new ArrayList<T>();
		Set<T> array = this.getSet().keySet();
		tempo.addAll(array);
		int[] tabNombre = new int[tempo.size()];
		int[] tabIndex = new int[tempo.size()];
		for( int i = 0 ; i < tabNombre.length ; i++ ) {
			tabNombre[i] = this.count(tempo.get(i));
			tabIndex[i] = i;
		}
		this.trie(tabIndex, tabNombre, 0 , tabIndex.length - 1);
		for( int i = 0 ; i < tabIndex.length ; i++ ) {
			retour.add(tempo.get(tabIndex[tabIndex.length - 1 - i]));
		}
		return retour;
	}
	
	/**
	 * Quicksort
	 * @param tabIndex:tableau qui contient les index de tabNombre, le tableau que l'on trie
	 * @param tabNombre:les données, il ne bouge pas
	 * @param premier: premier indice traité
	 * @param dernier: dernier indice traité
	 */
	private void trie(int[] tabIndex, int[] tabNombre, int premier, int dernier) {
		if( premier < dernier ){
			int p = this.partition( tabIndex , tabNombre , premier, dernier );
			this.trie( tabIndex , tabNombre , premier , p - 1 );
			this.trie( tabIndex, tabNombre, p + 1 , dernier);
		}
	}
	
	/**
	 * Partition qui quicksort
	 * @param tabIndex:tableau qui contient les index de tabNombre, le tableau que l'on trie
	 * @param tabNombre:les données, il ne bouge pas
	 * @param premier: premier indice traité
	 * @param dernier: dernier indice traité
	 * @return le prochain point à partir duquel on va couper le tableau en deux.
	 */
	private int partition(int[] tabIndex, int[] tabNombre, int premier, int dernier) {
		int pivot = tabNombre[tabIndex[dernier]];
		int i = premier;
		for ( int j = premier ; j < dernier ; j++ ){
			if( tabNombre[tabIndex[j]] < pivot ){
				this.echanger( tabIndex , i , j );
				i++;
			}
		}
		this.echanger( tabIndex , i , dernier );
		return i;
	}

	/**
	 * Fonction qui échange les valeurs de deux cases d'un tableau de int
	 * @param tabIndex : la tableau dont on échange les valeurs
	 * @param i: le premier indice
	 * @param j: le second indice
	 */
	private void echanger(int[] tabIndex , int i, int j) {
		if( tabIndex[i] == tabIndex[j]) return;
		tabIndex[i] += tabIndex[j];// i = i + j , j = j
		tabIndex[j] = tabIndex[i] - tabIndex[j]; // i = i+j , j = i
		tabIndex[i] -= tabIndex[j];
	}

	public String toString(){
		return leSet.toString();
	}
	
}
