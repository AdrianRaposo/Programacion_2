package ee1;
import anotacion.Programacion2;
import fecha.Fecha;




@Programacion2 (
        nombreAutor1 = "Adrián",
		apellidoAutor1 = "Raposo Pozuelo",
		emailUPMAutor1 = "adrian.raposo@alumnos.upm.es"
)


public class Libro {
	
	private String titulo;
	private String autor;
	private Fecha fechaPublicacion;
	private int nVecesPrestado;
	
	public Libro(String titulo, String autor, Fecha fechaPublicacion) {
		this.titulo=titulo;
		this.autor= autor;
		this.fechaPublicacion= fechaPublicacion;
	}
	public Libro(Libro libro) {
		this.titulo=new String(libro.titulo);
		this.autor= new String(libro.autor);
		this.fechaPublicacion= new Fecha(libro.fechaPublicacion);
	}
	
	public void prestado() {
		nVecesPrestado++;
	}
	@Override
	public boolean equals(Object obj) {
		boolean res;
		if (obj == null|| this.getClass() != obj.getClass()) {
			res =false;
		}else {
			Libro libro = (Libro) obj;
				res = this.getAutor().equals(libro.getAutor())&&this.getTitulo().equals(libro.getTitulo())&&(this.getFechaPublicacion().equals(libro.getFechaPublicacion()));
		}
		return res;
	}
	
	
	

	public String getTitulo() {
		return titulo;
	}

	public String getAutor() {
		return autor;
	}

	public Fecha getFechaPublicacion() {
		return fechaPublicacion;
	}

	public int getNVecesPrestado() {
		return nVecesPrestado;
	}



}

