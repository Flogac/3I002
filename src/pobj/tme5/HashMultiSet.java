package pobj.tme5;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.NoSuchElementException;


/**
 * Cette classe est une representation d'un HashMultiSet generique T.
 * Chaque objet T que l'on appelera key, sera associe a un Integer qui representera son nombre d'occurence.
 * Cette classe peut ainsi representer un dictionnaire. Ces objets T et leur occurences seront stocke dans un
 * HashMap<T,Integer>. 
 * @param <T>
 */
public class HashMultiSet<T> extends AbstractCollection<T> implements MultiSet<T>{
	private HashMap<T,Integer> hash = new HashMap<T,Integer>();
	private int size = 0;
	private KeyComparator<T> comp = new KeyComparator<T>(this);
	
	public HashMultiSet(){}
	
	public HashMultiSet(Collection<T> col){
		Iterator<T> iter = col.iterator();
		while(iter.hasNext() == true){
			this.add(iter.next());
		}
	}
	
	@Override
	public boolean add(T e, int count){
		if(count <= 0) throw new IllegalArgumentException("Il faut entrer un nombre strictement positif");
		if(hash.containsKey(e) == true)
			hash.put(e, hash.get(e) + count);
		else 
			hash.put(e, count);
		size = size + count;
		return true;
	}
	
	@Override
	public boolean add(T e){
		if(hash.containsKey(e) == false){
			hash.put(e,1);  
		} else {
			hash.put(e, 1 + hash.get(e));
		}
		size++;
		return true;
	}

	@Override
	public boolean remove(Object e){
		if(hash.containsKey(e) == true){
			if(hash.get(e) == 1)
				hash.remove(e);
			else
				hash.put((T)e, hash.get(e) - 1);
			size--;
		}
		return false; //on n'a donc pas modifie nos donnees
	}
	
	@Override
	public boolean remove(Object e, int count){
		if(count <= 0) throw new IllegalArgumentException("Il faut entrer un nombre strictement positif");
		if(hash.containsKey(e) == true){
			int prev_value = hash.get(e);
			int new_value = prev_value - count;
			if(new_value <= 0)
				size = size - hash.remove(e);
			else{
				hash.put((T)e, new_value);
				size = size - count;
			}
		}
		return false; //on n'a donc pas modifie nos donnes
	}
	
	@Override
	public int count(T o){
		if(hash.containsKey(o) == true)
			return hash.get(o);
		return 0;
	}
	
	@Override
	public void clear(){ hash.clear(); size = 0; }
		
	@Override
	public int size(){ return size; }
	
	public HashMap<T,Integer> getHashMap(){ return hash;}
	
	public Iterator<T> iterator() {
		return new HashMultiSetIterator<T>(this);
	}
	
	public List<T> elements(){
		List<T> l = new ArrayList<T>(hash.keySet());
		l.sort(comp);
		for(int i = 0; i < 10; i++)
			System.out.print(l.get(i)+" ");
		System.out.print("\n");
		return l;
	}
	
	public String toString(){
		StringBuilder b = new StringBuilder();
		Iterator<Entry<T,Integer>> iter; // on utilise un iterator pour parcourir chaque element de hashmap l'ajouter au StringBuilder.
		Entry<T,Integer> currentEntry; // on utilise l'iterator du dessus sur un Entry<T,Integer>.
		iter = hash.entrySet().iterator();
		b.append("[");
		try{
			currentEntry = iter.next();
		}catch (NoSuchElementException e){
			b.append("]");
			return b.toString();
		}
		while(iter.hasNext() == true){
			b.append(currentEntry.getKey()+":"+currentEntry.getValue()+"; ");
			currentEntry = iter.next();
		}
		b.append(currentEntry.getKey()+":"+currentEntry.getValue()+"]");
		return b.toString();
	}
	
	/**
	 * Verifie la coherence de la structure interne de notre classe HashMultiSet
	 * Deux conditions sont a verifier :
	 * - l'attribut size soit egale au nombre d'occurence totale
	 * - les occurences sont forcement positif
	 * On utilise pour cela un iterator sur une collection d'integers.
	 */
	public boolean isConsistent(){
		Iterator<Integer> iter; // on utilise un iterator pour parcourir chaque element de hashmap pour pouvoir verifier les conditions
		Collection<Integer> values = hash.values(); // on utilise l'iterator du dessus sur une collection d'entier extraite de hashMap.
		iter = values.iterator();
		try{
			int currentValue = iter.next();
			int size = 0;
			while(iter.hasNext() == true){
				if(currentValue <= 0){
					return false;
				}
				size = size + currentValue;
				currentValue = iter.next();
				
			}
			//lorsque l'on sort de la boucle, on n'a pas teste la dernier valeur, il faut donc le faire apres
			if(currentValue <= 0){
				return false;
			}
			size = size + currentValue;
			if(size == this.size){
				return true;
			}
			return false;
		} catch (NoSuchElementException e){ //la liste de values est vide, donc le dictionnaire aussi
			if (this.size == 0) 
				return true;
			else
				return false;
		}
	}
	

	/**
	 * Representation d'un HashMultiSetIterator. Pour parcourir notre objet de type HashMultiSet
	 * Nous avons besoin de definir un Iterator specifique a cette classe car il doit "iterer" en fonction de 
	 * la structure interne de HashMultiSet, il est donc judicieux de creer cette nouvelle classe en interne
	 * pour que l'on puisse directement acceder au attribut de HashMultiSet
	 * Il parcourt en principe un objet de type generique T autant de fois qu'il existe d'occurence qui lui est associer
	 * avant de passer au suivant.
	 */
	public class HashMultiSetIterator<T> implements Iterator<T>{
		private int traveled = 0; // int qui va stocker notre parcours total (des occurences)
		private int index = 0;	//int qui va stocker l'occurence en cours d'un objet key
		private int currentObjectOccurence; // int qui va stocker le nombre d'occurence total pour un objet Key donne, que l'on comparera a index
		private T currentObject; // stock la key en cours
		private Entry<T,Integer> currentEntry;
		private HashMap<T,Integer> hashMap;
		private Iterator<Entry<T,Integer>> internIter;
		
		public HashMultiSetIterator(HashMultiSet<T> hash){
			hashMap = hash.getHashMap(); // on stock notre hash
			internIter = hashMap.entrySet().iterator(); // on cree un iterator sur des paires <Key,Value>
			currentEntry = internIter.next(); // on stock la premiere paire <Key,Value> dans un entry
			currentObjectOccurence = currentEntry.getValue(); // on stock le nombre d'occurence de Key 
			currentObject = currentEntry.getKey(); // on stock Key
			traveled = 1; // on est au premier element, premiere occurence
			index = 1; // premier occurence de currentObject
		}
		@Override
		public boolean hasNext() {
			if(traveled <= size()){
				return true;
			}
			return false;
		}
			

		@Override
		/**
		 * Methode next de notre iterator :
		 * On utilise l'iterateur interne que l'on a cree et on l'appel seulement quand on a itere autant de fois qu'il le fallait (
		 * correspondant au nombre d'occurence) sur l'objet T precedent.
		 */
		public T next() {
			if(index <= currentObjectOccurence){ // on est donc toujours dans la meme key
				index++;
				traveled++;
				return currentObject;
			}
			currentEntry = internIter.next(); // on passe donc a la paire <Key,Value> suivante
			currentObject = currentEntry.getKey(); //et on change nos attributs.
			currentObjectOccurence = currentEntry.getValue();
			index = 1;
			traveled++;
			return currentObject;
			
		}
		
	}
}
