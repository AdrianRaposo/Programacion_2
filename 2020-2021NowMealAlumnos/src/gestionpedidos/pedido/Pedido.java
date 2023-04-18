package gestionpedidos.pedido;

import anotacion.Programacion2;
import gestionpedidos.transportes.Transporte;

@Programacion2 (
		nombreAutor1 ="�lvaro",
		apellidoAutor1 = "L�pez Mart�nez",
		emailUPMAutor1 = "alvaro.lopez@alumnos.upm.es",
		nombreAutor2 = "Adri�n",
		apellidoAutor2 = "Raposo Pozuelo", 
		emailUPMAutor2 = "adrian.raposo@alumnos.upm.es"
	)

public class Pedido {
	// C�DIGO DE APOYO
	private Cliente cliente;
	private PlatoComida[] comidas;
	private Restaurante restaurante;
	private double importe;	
	private Transporte transporte;
	private double peso;
	
	public Pedido(Cliente cliente, PlatoComida[] comidas, Restaurante restaurante) {		
		//TO-DO
		this.cliente=cliente;
		this.comidas=comidas;
		this.restaurante=restaurante;
		peso=calcularPeso();
		importe=calcularImporte();
	}

	
	private double calcularPeso(){		
		double pesoTotal = 0;
		for (int i=0;i<comidas.length;i++) {
			pesoTotal+=comidas[i].getPeso();
		}
		return pesoTotal;
	}
	public double getPeso() {
		//TO-DO
		return peso;
	}
	
	
	public double coste(Transporte transporte){
		//TO-DO
		double coste=getImporte()+transporte.coste(restaurante.getCodigo())+transporte.coste(restaurante.getCodigo(),cliente.getCodigo());
		if (getImporte()>=100) {
			coste*=1.10;
		}
		return coste;
	}
	private double calcularImporte() {
		double importeTotal=0;
		for (int i=0;i<comidas.length;i++) {
			importeTotal+=comidas[i].getPrecio();
		}
		return importeTotal;
	}
	// C�DIGO DE APOYO
	public double getImporte(){
		return importe;
	}	

	// C�DIGO DE APOYO
	public Transporte getTransporte() {
		return transporte;
	}

	// C�DIGO DE APOYO
	public void setTransporte(Transporte transporte) {
		this.transporte = transporte;
	}
	
	// C�DIGO DE APOYO
	public Cliente getCliente(){
		return cliente;
	}
	
	// C�DIGO DE APOYO
	public Restaurante getRestaurante(){
		return restaurante;
	}
}
