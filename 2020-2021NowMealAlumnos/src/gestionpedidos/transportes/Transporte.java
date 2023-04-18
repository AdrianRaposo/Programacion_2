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

public abstract class Transporte {
	private String codigo;
	private Mapa mapa;
	
	protected Transporte (String codigo, Mapa mapa) {
		this.codigo=codigo;
		this.mapa=mapa;
	}
	public double coste(String codPosDestino) {
		return coste(codigo,codPosDestino);
	}
	public abstract double coste(String codPosOrigen, String codPosDestino);
	
	public Mapa getMapa() {
		return mapa;
	}
	public String getCodigo() {
		return codigo;
	}
}
