package banco;
/**
 * Cliente de una entidad bancaria
 * 
 * @version Octubre 2013
 * @author Manuel Collado
 */
public class Cliente {
	private String nombre;
	private String nif;

	// Constructor
	public Cliente(String nombre, String nif) {
		this.nombre = nombre;
		this.nif = nif;
	}

	// Getters
	public String getNombre() {
		return nombre;
	}
	public String getNif() {
		return nif;
	}
	
}
