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

public class FurgonetaPropia extends Furgoneta {
	private double velocidadMedia;
	private static final double EUROS_P_HORA=40;
	public FurgonetaPropia(String codigo, Mapa mapa, double tara) {
		super(codigo, mapa, tara);
		setVelocidadMedia(30);
	}
	public double getVelocidadMedia() {
		return velocidadMedia;
	}
	public void setVelocidadMedia(double velocidadMedia) {
		this.velocidadMedia = velocidadMedia;
	}
	@Override
	public double coste(String codPosOrigen, String codPosDestino) {
		double coste=getMapa().distancia(codPosOrigen,codPosDestino)*EUROS_P_HORA/velocidadMedia;
		if (getTara()>=500) {
			coste*=1.10;
		}
		return coste;
	}

}
