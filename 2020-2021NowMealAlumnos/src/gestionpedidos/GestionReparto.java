package gestionpedidos;

import anotacion.Programacion2;
import gestionpedidos.excepciones.PedidoSinTransporteAsignado;
import gestionpedidos.mapa.Mapa;
import gestionpedidos.mapa.PosicionXY;
import gestionpedidos.pedido.Pedido;
import gestionpedidos.transportes.Transporte;

@Programacion2 (
		nombreAutor1 ="Álvaro",
		apellidoAutor1 = "López Martínez",
		emailUPMAutor1 = "alvaro.lopez@alumnos.upm.es",
		nombreAutor2 = "Adrián",
		apellidoAutor2 = "Raposo Pozuelo", 
		emailUPMAutor2 = "adrian.raposo@alumnos.upm.es"
	)
public class GestionReparto {
	// CÓDIGO DE APOYO
	private GestionRepartoLocal[] gestoresLocales;
	private Mapa mapa;

	//CÓDIGO DE APOYO
	public Mapa getMapa() {
		return mapa;
	}

	// CÓDIGO DE APOYO
	public String getEstadoGestorLocal(int i){
		return this.gestoresLocales[i].getDisponibles() + this.gestoresLocales[i].getEsperando();
	}

	// CÓDIGO DE APOYO
	public String getEstadoGestorLocalNum(int i){
		return this.gestoresLocales[i].getCodMotosDisponibles().size() + ";" +
				this.gestoresLocales[i].getCodFurgoDisponibles().size() + ";" +
				this.gestoresLocales[i].getCodEsperandoMoto().size() + ";" +
				this.gestoresLocales[i].getCodEsperandoFurgo().size() ;
	}
	
	public GestionReparto(Mapa mapa){
		//TO-DO
		this.mapa=mapa;
		gestoresLocales=new GestionRepartoLocal[4];
		for (int i=0;i<gestoresLocales.length;i++) {
			gestoresLocales[i]=new GestionRepartoLocal();
		}
	}
	public void addTransporteLocalidad(Transporte transporte) {
		//TO-DO
		PosicionXY pos =mapa.getPosicion(transporte.getCodigo());
		gestoresLocales[seleccionarLocalidad(pos)].add(transporte);		
	}
	
	private int seleccionarLocalidad(PosicionXY pos) {
		// TO-DO
		int zona;
		if (pos.getX() <= mapa.getMaxCoordX() / 2) {
			if (pos.getY() <= mapa.getMaxCoordY() / 2) {
				zona = 0;
			} else {
				zona = 2;
			}
		} else {
			if (pos.getY() <= mapa.getMaxCoordY() / 2) {
				zona= 1;
			} else {
				zona = 3;
			}
		}
		return zona;
	}

	public void asignarPedido(Pedido pedido) {
		//TO-DO
		PosicionXY pos =mapa.getPosicion(pedido.getCliente().getCodigo());
		gestoresLocales[seleccionarLocalidad(pos)].asignarPedido(pedido);	
	}

	public void notificarEntregaPedido(Pedido pedido) throws PedidoSinTransporteAsignado{
		//TO-DO
		PosicionXY pos =mapa.getPosicion(pedido.getCliente().getCodigo());
		gestoresLocales[seleccionarLocalidad(pos)].notificarEntregaPedido(pedido);
	}
}
