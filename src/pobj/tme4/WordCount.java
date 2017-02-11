package pobj.tme4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WordCount {
		
	public static void main(String[] argz ){
		HashMultiSet<String> ms = new HashMultiSet<String>();
		try {
			wordcount( ms );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void	wordcount(MultiSet<String> ms) throws IOException{
		String file = "Truc.txt";
		BufferedReader br = new BufferedReader( new FileReader(file));
		String line;
		while ( ( line = br.readLine() )!= null	) {
			for	(String word : line.split("\\P{L}+")) {
				System.out.println( word );
				ms.add( word );
			}
		}
		System.out.print(ms.elements());
		br.close();
	}
	
}
