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

public class FurgonetaSubcontratada extends Furgoneta {
	private double eurosPKm;
	public FurgonetaSubcontratada(String codigo, Mapa mapa, double tara) {
		super(codigo, mapa, tara);
		setEurosPKm(1);
	}

	@Override
	public double coste(String codPosOrigen, String codPosDestino) {
		double coste=getMapa().distancia(codPosOrigen,codPosDestino)*eurosPKm;
		if (getTara()>=1000) {
			coste*=1.10;
		}
		return coste;
	}

	public double getEurosPKm() {
		return eurosPKm;
	}

	public void setEurosPKm(double eurosPKm) {
		this.eurosPKm = eurosPKm;
	}
	
}
