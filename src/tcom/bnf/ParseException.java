package tcom.bnf;

/**
 * Excepciones lanzadas por el parser
 * @author rlopez
 *
 */
@SuppressWarnings("serial")
public class ParseException extends Exception{
	public ParseException(String msg){
		super(msg);
	}
	
}
