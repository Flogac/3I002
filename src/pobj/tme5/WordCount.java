package pobj.tme5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import pobj.util.Chrono;

public class WordCount {
	public static void main (String [] arg){
		Chrono chrono = new Chrono(); 
		HashMultiSet<String> ms = new HashMultiSet<String>();
		MultiSetDecorator<String> checkedMS = new MultiSetDecorator<String>(ms);
		try {
			wordcount( ms );
		} catch (IOException e) {
			e.printStackTrace();
		} 
		System.out.println(ms);
		chrono.stop();
	}
		
	public static void	wordcount(MultiSet<String> ms) throws IOException{
		String file = "WarAndPeace.txt";
		BufferedReader br = new BufferedReader( new FileReader(file));
		String line;
		while ( ( line = br.readLine() )!= null	) {
			for	(String word : line.split("\\P{L}+")) {
				ms.add(word,1); // Usage de la methode avec l'argument 1 (count) pour tester les exceptions.
			}
		}
		br.close();
	}
}

