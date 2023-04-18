/**
 * 
 */
package news;

/**
 * @author agonzalez
 *
 */
public enum TArea {
	SPORT ("Sports news"), ECONOMY ("Economy news"), SCIENCE ("Science & Technologies news"),
	LOCAL ("Local news"), WORLD ("World news"), POLITICS ("Polotics news");
	/**
	 * Contiene el nombre del enumerasdo
	 */
	private final String nombre;
	/**
	 * El constructor es usado por java para crear cada uno de los eumerados
	 * @param nombre
	 */
	private TArea(String nombre) {// TArea
		this.nombre = nombre;
	}// TArea
	
	/**
	 * Este método retorna el String asociado al enumerado
	 */
	public String toString() {// toString
		return nombre;
	}// toString
	
}
