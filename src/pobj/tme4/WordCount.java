package pobj.tme4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import pobj.util.Chrono;

public class WordCount {
	public static void main (String [] arg){
		Chrono chrono = new Chrono(); 
		HashMultiSet<String> ms = new HashMultiSet<String>();
		try {
			wordcount( ms );
		} catch (IOException e) {
			e.printStackTrace();
		}
		chrono.stop(); 
		Chrono chrono2 = new Chrono();
		NaiveMultiSet<String> ms2 = new NaiveMultiSet<String>();
		try {
			wordcount( ms2 );
		} catch (IOException e) {
			e.printStackTrace();
		}
		chrono2.stop();
	}
		
	public static void	wordcount(MultiSet<String> ms) throws IOException{
		String file = "WarAndPeace.txt";
		BufferedReader br = new BufferedReader( new FileReader(file));
		String line;
		while ( ( line = br.readLine() )!= null	) {
			for	(String word : line.split("\\P{L}+")) {
				ms.add( word );
			}
		}
		ms.elements();
		br.close();
	}
}

