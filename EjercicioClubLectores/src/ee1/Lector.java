package ee1;

import list.ArrayList;

/**
 * 
 */


public class Lector {
	
	private int nSocio;
	private String nombre;
	private ArrayList<Libro> historicoLectura = new ArrayList<>();
	
	public Lector(int nSocio, String nombre) {
		this.nSocio=nSocio;
		this.nombre=nombre;
		
	}
	public Libro getLibroLeido(int pos) {
		Libro res = null;
		if((pos<=historicoLectura.size() && pos>=1))
			res=historicoLectura.get(pos-1);
		return res;
	}
	public void leerLibro(Libro libro) {
		libro.prestado();
		if(historicoLectura.size()<10) {
			historicoLectura.add(historicoLectura.size(), libro);
		}
		else {
			historicoLectura.removeElementAt(0);
			historicoLectura.add(9,libro);
		}
	}
	@Override
	public boolean equals(Object obj) {
		boolean res;
		if (obj == null || this.getClass() != obj.getClass())
			res = false;
		else {
			Lector lector = (Lector) obj;
			res=lector.nSocio==this.nSocio && this.historicoLectura.equals(lector.historicoLectura);
		}
		return res;
	}

	public int getNSocio() {
		return nSocio;
	}

	public String getNombre() {
		return nombre;
	}
  
  
}
