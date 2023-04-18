/**
 * 
 */
package news;

import fecha.Fecha;

/**
 * @author agonzalez
 * Esta clase representa un titular de una noticia
 *
 */
public class Headline {//Headline
	private String title;
	private Fecha published;
	private TArea area;
	/**
	 * Consturye un titular de noticia para lo que recibe el título y la fecha de publicación
	 * @param title título de la noticia
	 * @param fecha fecha de publicación
	 */
	public Headline (String title, Fecha fecha, TArea area){
		this.title = title;
		published = new Fecha(fecha);
		this.area = area;
	}
	
	/**
	 * Se retorna un String que estará formado por la fecha de publicación y el título
	 */
	public String toString(){
		return published + ": "+title;
	}
	
	/**
	 * Permite consultar el área al que pertenece este headline
	 * @return
	 */
	public TArea getArea(){
		return area;
	}
	
	/**
	 * Permite consultar la fecha de publicación de este headline
	 * @return se retorna una copia de la fecha de publicación
	 */
	public Fecha getPublished()
	{
		return new Fecha(published);
	}
	
	/**
	 * Permite consultar el título del headline
	 * @return
	 */
	public String getTitulo(){
		return title;
	}


	
	public boolean esIgual (Headline other){
		return this.equals(other);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((area == null) ? 0 : area.hashCode());
		result = prime * result + ((published == null) ? 0 : published.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || !(obj instanceof Headline))
			return false;
		Headline other = (Headline) obj;
		return area == other.area && //Al ser un enumerado se considera como un tipo básico
				published.equals(other.published) &&
				title.equals(other.title);
	}
	
}//Headline
