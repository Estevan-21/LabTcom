package tcom.bnf;
import static tcom.bnf.BnfLexer.*;
import org.antlr.v4.runtime.Token;
/**
 * Implementa los servicios basicos de un parser de descenso recursivo
 * segun las convenciones del curso.
 * @author rolopezb
 *
 */
public class BasicParser {
	protected Token token; //Token vigente en el proceso
	protected BnfLexer lexer; //Analizador lexico
	
	protected void sigToken() {
		token = lexer.nextToken();
	}
	
	/**
	 * Verifica que su unico argumento coincide con el tipo del token vigente.
	 * 
	 * @param tokType    Tipo esperado del token vigente
	 * @throws ParseException
	 */
	protected void expect(int tokType) throws ParseException{
		if(token.getType()!=tokType){
			throw new ParseException(getReadableLocation(token)+
					": Se esperaba "+tokenReadableName(tokType)+" y aparece "+token.getText());
		}
		token=lexer.nextToken();
	}
	
	/**
	 * Produce una version legible de la ubicacion del token
	 * @param tok El token
	 * @return Ubicacion en formato [linea,columna]
	 */
	public String getReadableLocation(Token tok){
		StringBuffer sbf=new StringBuffer();
		
		sbf.append('[');
		sbf.append(tok.getLine());
		sbf.append(',');
		sbf.append(tok.getCharPositionInLine());
		sbf.append(']');
		return sbf.toString();
	}

	/**
	 * Produce un nombre legible para un token
	 * @param tokType tipo del token
	 * @return Una version legible del token
	 */
	protected String tokenReadableName(int tokType) {
		String resp = null;
		switch (tokType) {
		case NONTERMINAL:
			resp = "no terminal";
			break;
		case TERMINAL:
			resp = "terminal";
            break;
            
        case LPAR:
        	resp ="parentesis izquierdo";
        	break;
        	
        case RPAR:
        	resp = "parentesis derecho";
        	break;
        	
        case LCURL:
        	resp = "corchete izquierdo";
        	break;
        	
        case RCURL:
        	resp = "corchete derecho";
        	break;
        	
        case LBRACK:
        	resp = "bracket izquierdo";
        	break;
        case RBRACK:
        	resp = "bracket derecho";
        	break;
        	
        case EOF:
        	resp = "EOF";
        	break;
        	
        case BAR:
        	resp = "Barra vertical";
        	break;
        	
        case BNFEQ:
        	resp = "cuatro puntos igual";
        	break;
        	
        case SEMICOLON:
        	resp = "punto y coma";
        	break;
        	
		default:
			break;
		}
		return resp;
	}

}
