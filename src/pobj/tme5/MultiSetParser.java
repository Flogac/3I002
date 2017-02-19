package pobj.tme5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Cette classe represente un objet MultiSetParser, qui a partir d'un fichier lu, cree un multiSet<String>
 * elle n'a besoin que d'une methode static parse qui va se charger de faire la lecture du fichier tout en gerant les
 * multiple exceptions qui peuvent avoir lieu
 */
public class MultiSetParser {

	public static MultiSet<String> parse(String fileName) throws InvalidMultiSetFormat{
		MultiSet<String> ms = new HashMultiSet<String>();
		BufferedReader buffer;
		try{
			buffer = new BufferedReader(new FileReader(fileName));
			String str = buffer.readLine();
			String[] parsedStr;
			int occurence;
			while(str != null) {
				if(str.contains(":") == false) {
					buffer.close();
					throw new InvalidMultiSetFormat("format du fichier non reconnu, un mot et son nombre d'occurence doivent être séparer par un : et sans espace");
				}
				parsedStr = str.split(":");
				occurence = Integer.decode(parsedStr[1]);
				if(occurence <= 0) {
					buffer.close();
					throw new InvalidMultiSetFormat("On doit associer une occurence strictement positif pour un mot");
				}
				ms.add(parsedStr[0], occurence);
				str = buffer.readLine();
			}
			buffer.close();
			return ms;
		} catch (FileNotFoundException e) {
			throw new InvalidMultiSetFormat("Fichier non existant", e);
		} catch (IOException e) {
			throw new InvalidMultiSetFormat("Erreur d'entrée/sortie", e);
		} catch (NumberFormatException e) {
			throw new InvalidMultiSetFormat("l'occurence associer au mot n'est pas un entier", e);
		}
	}
}
