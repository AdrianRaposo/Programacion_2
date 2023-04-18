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

public class Moto extends Transporte{
	private double eurosPKm;
	public static final double TARIFA_MIN=10;
	
	public Moto(String codigo, Mapa mapa) {
		super(codigo, mapa);
		eurosPKm=2;
	}
	@Override
	public double coste(String codPosOrigen, String codPosDestino) {
		return getMapa().distancia(codPosOrigen,codPosDestino)*eurosPKm+ TARIFA_MIN;
	}
	public double getEurosPKm() {
		return eurosPKm;
	}
	public void setEurosPKm(double eurosPKm) {
		this.eurosPKm = eurosPKm;
	}
	public static double getTarifaMin() {
		return TARIFA_MIN;
	}

}
