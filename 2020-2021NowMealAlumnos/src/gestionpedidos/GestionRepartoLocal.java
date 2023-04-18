package gestionpedidos;

import anotacion.Programacion2;
import gestionpedidos.excepciones.PedidoSinTransporteAsignado;
import gestionpedidos.pedido.Pedido;
import gestionpedidos.transportes.Furgoneta;
import gestionpedidos.transportes.Moto;
import gestionpedidos.transportes.Transporte;
import list.ArrayList;
import list.IList;
import queues.IQueue;
import queues.exceptions.EmptyQueueException;
import queues.CircularQueue;

@Programacion2(nombreAutor1 = "Álvaro", apellidoAutor1 = "López Martínez", emailUPMAutor1 = "alvaro.lopez@alumnos.upm.es", nombreAutor2 = "Adrián", apellidoAutor2 = "Raposo Pozuelo", emailUPMAutor2 = "adrian.raposo@alumnos.upm.es")

public class GestionRepartoLocal {
	// CÓDIGO DE APOYO
	private IList<Moto> motosDisponibles;
	private IList<Furgoneta> furgonetasDisponibles;

	private IQueue<Pedido> pedidosEsperandoMoto;
	private IQueue<Pedido> pedidosEsperandoFurgoneta;

	// CÓDIGO DE APOYO
	public String getDisponibles() {
		return "Motos Disponibles:"
				+ GestionRepartoLocalFuncionesAuxiliares
						.myArrayListToString(GestionRepartoLocalFuncionesAuxiliares.getCodList(motosDisponibles))
				+ System.lineSeparator() + "Furgonetas Disponibles:"
				+ GestionRepartoLocalFuncionesAuxiliares
						.myArrayListToString(GestionRepartoLocalFuncionesAuxiliares.getCodList(furgonetasDisponibles))
				+ System.lineSeparator();

	}

	// CÓDIGO DE APOYO
	public String getEsperando() {
		return "Pedidos esperando moto:"
				+ GestionRepartoLocalFuncionesAuxiliares.myArrayListToString(
						GestionRepartoLocalFuncionesAuxiliares.getClienteRestauranteList(pedidosEsperandoMoto))
				+ System.lineSeparator() + "Pedidos esperando furgoneta:"
				+ GestionRepartoLocalFuncionesAuxiliares.myArrayListToString(
						GestionRepartoLocalFuncionesAuxiliares.getClienteRestauranteList(pedidosEsperandoFurgoneta))
				+ System.lineSeparator();
	}

	// CÓDIGO DE APOYO
	public IList<String> getCodMotosDisponibles() {
		return GestionRepartoLocalFuncionesAuxiliares.getCodList(motosDisponibles);
	}

	// CÓDIGO DE APOYO
	public IList<String> getCodFurgoDisponibles() {
		return GestionRepartoLocalFuncionesAuxiliares.getCodList(furgonetasDisponibles);

	}

	// CÓDIGO DE APOYO
	public IList<String[]> getCodEsperandoMoto() {
		return GestionRepartoLocalFuncionesAuxiliares.getClienteRestauranteList(pedidosEsperandoMoto);
	}

	public IList<String[]> getCodEsperandoFurgo() {
		return GestionRepartoLocalFuncionesAuxiliares.getClienteRestauranteList(pedidosEsperandoFurgoneta);
	}

	private static final double PESO_MAX_MOTO = 20;

	// CÓDIGO DE APOYO
	public GestionRepartoLocal() {

		this.motosDisponibles = new ArrayList<>();
		this.furgonetasDisponibles = new ArrayList<>();

		this.pedidosEsperandoFurgoneta = new CircularQueue<>();
		this.pedidosEsperandoMoto = new CircularQueue<>();
	}

	public void add(Transporte transporte) {
		// TO-DO
		if (transporte instanceof Moto) {
			motosDisponibles.add(motosDisponibles.size(), (Moto) transporte);
		} else {
			furgonetasDisponibles.add(furgonetasDisponibles.size(), (Furgoneta) transporte);
		}
	}

	public void asignarPedido(Pedido pedido) {
		// TO-DO
		if (pedido.getPeso() <= PESO_MAX_MOTO) {
			if (motosDisponibles.size() != 0) {
				pedido.setTransporte(mejorCoste(pedido, motosDisponibles));
				motosDisponibles.remove((Moto) mejorCoste(pedido, motosDisponibles));
			} else {
				pedidosEsperandoMoto.add(pedido);
			}
		} else {
			if (furgonetasDisponibles.size() != 0) {
				pedido.setTransporte(mejorCoste(pedido, furgonetasDisponibles));
				furgonetasDisponibles.remove((Furgoneta) mejorCoste(pedido, furgonetasDisponibles));
			} else {
				pedidosEsperandoFurgoneta.add(pedido);
			}
		}
	}
	//Método auxiliar para calcular mejor coste
	/**
	 * 
	 * @param pedido
	 * @param transportesDisponibles
	 * @return
	 */
	private Transporte mejorCoste(Pedido pedido, IList<?> transportesDisponibles) {
		Transporte mejorTransporte = (Transporte) transportesDisponibles.get(0);
		for (int i = 1; i < transportesDisponibles.size(); i++) {
			mejorTransporte = pedido.coste((Transporte) transportesDisponibles.get(i)) <= pedido.coste(mejorTransporte)
					? (Transporte) transportesDisponibles.get(i)
					: mejorTransporte;
		}
		return mejorTransporte;
	}

	// PRE: el pedido tiene asignado un transporte
	public void notificarEntregaPedido(Pedido pedido) throws PedidoSinTransporteAsignado {
		if (pedido.getTransporte() == null) {
			throw new PedidoSinTransporteAsignado("El pedido no tiene asignado un transporte :(");
		}
		if (pedido.getTransporte() instanceof Moto) {
			actualizarPedidosEsperando(pedidosEsperandoMoto, pedido);
		} else {
			actualizarPedidosEsperando(pedidosEsperandoFurgoneta, pedido);
		}
	}
	//Metodo auxiliar para actualizar los pedidos esperando
	/**
	 * 
	 * @param pedidosEsperandoTransporte
	 * @param pedido
	 */
	private void actualizarPedidosEsperando(IQueue<Pedido> pedidosEsperandoTransporte, Pedido pedido) {

		if (pedidosEsperandoTransporte.isEmpty()) {
			add(pedido.getTransporte());
		} else {
			try {
				pedidosEsperandoTransporte.poll().setTransporte(pedido.getTransporte());
			} catch (EmptyQueueException e) {
				//Rama inalcanzable
			}
		}
	}
}