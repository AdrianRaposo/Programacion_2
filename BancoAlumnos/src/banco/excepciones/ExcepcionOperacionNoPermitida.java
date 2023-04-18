package banco.excepciones;
/**
 * Excepci�n de operaci�n no permitida
 * 
 * @version Octubre 2013
 * @author Manuel Collado
 */
public class ExcepcionOperacionNoPermitida extends Exception {
    private static final long serialVersionUID = 1L;

    public ExcepcionOperacionNoPermitida(String string) {
        super(string);
    }

}
