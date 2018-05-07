package tcom.bnf;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.antlr.v4.runtime.*;

import static tcom.bnf.BnfLexer.*;

/**
 * Parser de descenso recursivo para el lenguaje Lbnf
 * @author bvargas
 *
 */
public class BnfParser extends BasicParser{
	
	
	/**
	 * Conecta el parser con el analizador lexico (clase BnfLexer)
	 * @param archName Nombre del archivo para analizar
	 * @throws ParseException Si detecta cualquier error
	 */

	public  void analizar(String archName) throws ParseException {
		String eol = System.getProperty("line.separator");
		InputStream is = BnfParser.class.getResourceAsStream("/"+archName);
		try {
			
			ANTLRInputStream input = new ANTLRInputStream(is);
			lexer = new BnfLexer(input);
			parse();	
			
		} catch (UnsupportedEncodingException e) {
			//Problemas con la codificacion de los caracteres
			throw new ParseException("UnsupportedEncodingException");
		} catch (IOException e) {
			throw new ParseException("Complicacion de IO:"+e.getMessage());
		} catch (LexerNoViableAltException e) {
			throw new ParseException("Error Lexico"+eol);
		} catch (RecognitionException e) {
			throw new ParseException("RecognitionException");
		} catch (ParseException e) {
			//Error detectado explicitamente por el parser
			StringBuilder sb = new StringBuilder();
			sb.append(e.getMessage());
			sb.append(eol);
			sb.append(eol);
			
			throw new ParseException(sb.toString());
		} 

	}

	/**
	 * Inicio del proceso de parsing
	 * @throws ParseException
	 */
	
	public void parse() throws ParseException{
		sigToken();
		//Modificación BNF
		ini();		
		expect(EOF);
	}
	
	
	public void ini() throws ParseException{
		while (token.getType()!=EOF){
			if(tokenReadableName(token.getType())=="terminal"){		
				
				if(token.getText().equals("'if'")) {
					System.out.print("\n"+ token.getText());				
					sigToken();
					bool();				
				}
				
				else if(token.getText().equals("'while'")) {
					System.out.print("\n"+token.getText());					
					sigToken();
					bool();	
				}
				
				else if(token.getText().equals("'stop'")) {
					System.out.print("\n"+token.getText());					
				}	
				
				else if(token.getText().equals("'a'") || token.getText().equals("'b'")) {
					System.out.print("\n"+token.getText());					
					var();
				}
			}			
			sigToken();
		}
	}		
	
	public void bool() throws ParseException{
		if(token.getText().equals("'a'") || token.getText().equals("'b'")) {
			System.out.print("\n"+token.getText());	
			sigToken();
			if(token.getText().equals("'<'") || token.getText().equals("'>'")) {
				System.out.print("\n"+token.getText());	
				sigToken();
				if(token.getText().equals("'a'") || token.getText().equals("'b'")) {
					System.out.print("\n"+token.getText());	
					sigToken();
				}
				else {
					throw new ParseException("Error Sintactico, se esperaba 'a' o 'b' y se recibió"+ token.getText());
				}
			}
			else {
				throw new ParseException("Error Sintactico, se esperaba '>' o '>' y se recibió"+ token.getText());
			}
		}
		else if(token.getText().equals("'not'")){
			
		}
		else {
			//throw new ParseException("Error Sintactico");
		}
	}
	
	
	public void var() throws ParseException{
		
	}
	
	private void test(){
		//Este ciclo no sigue las convenciones del descenso recursivo.
		//Sirve para verificar que la conexion entre el parser y el lexer
		//funciona correctamente!!
		while (token.getType()!=EOF){ 
			System.out.println(getReadableLocation(token)+
					" "+tokenReadableName(token.getType())+
					" "+token.getText());
			sigToken();
		}
	}
	
	

}
