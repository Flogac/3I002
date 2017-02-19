package pobj.tme5;

public class MultiSetParserTest {
	 public static void main (String [] args) throws InvalidMultiSetFormat{
		 MultiSetParser parser = new MultiSetParser();
		 MultiSet<String> ms;
		 ms = parser.parse("test.txt"); //contient [oui:23; java:23; c'est:23; top:23; hello:22; ok:39; hola:29; bonjour:2]
		 System.out.println(ms);
		 
	 }
}
