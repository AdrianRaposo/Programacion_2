package gestionpedidos.transportes;
import anotacion.Programacion2;
import gestionpedidos.mapa.Mapa;

@Programacion2 (
		nombreAutor1 ="Álvaro",
		apellidoAutor1 = "López Martínez",
		emailUPMAutor1 = "alvaro.lopez@alumnos.upm.es",
		nombreAutor2 = "Adrián",
		apellidoAutor2 = "Raposo Pozuelo", 
		emailUPMAutor2 = "adrian.raposo@alumnos.upm.es"
	)
public abstract class Furgoneta extends Transporte{
	
	private double tara;	
	
	protected Furgoneta(String codigo, Mapa mapa,double tara) {
		super(codigo, mapa);
		this.tara=tara;
	}
	
	public double getTara() {
		return tara;
	}
}
