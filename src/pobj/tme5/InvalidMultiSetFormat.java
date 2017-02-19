package pobj.tme5;

/**
 * Cette classe decrit les exceptions qui peuvent avoir lieu lors de la creation d'un multiSet via lecture
 * d'un fichier
 */
public class InvalidMultiSetFormat extends Exception{
	private static final long serialVersionUID = 1L;

	public InvalidMultiSetFormat(String msg){
		super(msg);
	}
	
	public InvalidMultiSetFormat(String msg, Throwable cause){
		super(msg,cause);
	}

}
