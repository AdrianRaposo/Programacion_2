package banco.excepciones;
/**
 * Excepción de cuenta incorrecta
 * 
 * @version Octubre 2013
 * @author Manuel Collado
 */
public class ExcepcionCuentaNoValida extends Exception {
	private static final long serialVersionUID = 1L;

	public ExcepcionCuentaNoValida(String string) {
		super(string);
	}

}
