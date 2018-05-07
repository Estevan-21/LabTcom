package tcom.bnf;

/**
 * Lanza el parser verificando argumentos del proceso.
 * @author rolopezb
 *
 */
public class BnfMain {

	public static void main(String[] args) {
		BnfParser parser = new BnfParser();
		try {
			if (args.length!=1){
				System.out.println("Se requiere un nombre de archivo como argumento");
				System.exit(0);
			}
			parser.analizar(args[0]);
			System.out.println("El archivo "+args[0]+" esta sintacticamente correcto");
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}

	}

}
