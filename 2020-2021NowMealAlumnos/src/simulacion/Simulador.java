package simulacion;

import gestionpedidos.GestionReparto;
import gestionpedidos.excepciones.PedidoSinTransporteAsignado;
import gestionpedidos.mapa.Mapa;
import gestionpedidos.mapa.PosicionXY;
import gestionpedidos.pedido.Cliente;
import gestionpedidos.pedido.PlatoComida;
import gestionpedidos.pedido.Pedido;
import gestionpedidos.pedido.Restaurante;
import list.ArrayList;

public class Simulador {
	private GestionReparto gestor;
	private Mapa mapa;
	private ArrayList<Pedido> pedidosPendientes;

	public Simulador(GestionReparto gestor, Mapa mapa){
		this.gestor = gestor;
		this.mapa = mapa;
		this.pedidosPendientes = new ArrayList<>();
	}

	// 0 1
	// 2 3
	public String getMapa(PosicionXY pos){
		if (pos.getX() <= mapa.getMaxCoordX() / 2)  {
			if (pos.getY() <= mapa.getMaxCoordY() / 2)
				// 0
				return mapa.getMapaLocalidad(new PosicionXY(0,0), 
						new PosicionXY(mapa.getMaxCoordX() / 2 ,mapa.getMaxCoordY() / 2 )) + 
						gestor.getEstadoGestorLocal(0);
			else
				// 2
				return mapa.getMapaLocalidad(new PosicionXY(0,mapa.getMaxCoordY() / 2 + 1 ), 
						new PosicionXY(mapa.getMaxCoordX() / 2 ,mapa.getMaxCoordY())) + 
						gestor.getEstadoGestorLocal(2);
		} else {
			if (pos.getY() <= mapa.getMaxCoordY() / 2)
				// 1
				return mapa.getMapaLocalidad(new PosicionXY(mapa.getMaxCoordX() / 2 + 1, 0), 
						new PosicionXY(mapa.getMaxCoordX(), mapa.getMaxCoordY() / 2 ))+ 
						gestor.getEstadoGestorLocal(1);
			else
				// 3
				return mapa.getMapaLocalidad(new PosicionXY(mapa.getMaxCoordX() / 2 + 1, 
						mapa.getMaxCoordY() / 2 + 1), 
						new PosicionXY(mapa.getMaxCoordX(), mapa.getMaxCoordY())) + 
						gestor.getEstadoGestorLocal(3);
		}		
	}

	public Pedido solicitarPedido(Cliente cliente, PlatoComida[] comidas, Restaurante restaurante){
		Pedido pedido = new Pedido(cliente, comidas, restaurante);
		gestor.asignarPedido(pedido);

		if (pedido.getTransporte() != null) {
			// colocamos el transporte en la pos del restaurante
			mapa.setPosicion(pedido.getTransporte().getCodigo(), 
					mapa.getPosicion(pedido.getRestaurante().getCodigo()));

			System.out.println("Pedido de cliente " + pedido.getCliente().getCodigo() + 
					" para restaurante " + pedido.getRestaurante().getCodigo() +
					" se asigna a " + pedido.getTransporte().getCodigo());
		} else {
			System.out.println("Pedido de cliente " + pedido.getCliente().getCodigo() + 
					" para restaurante " + pedido.getRestaurante().getCodigo() +
					" queda pendiente de asignación") ;
			pedidosPendientes.add(pedidosPendientes.size(), pedido);
		}


		System.out.println(getMapa(mapa.getPosicion(cliente.getCodigo())));
		return pedido;
	}

	public void notificarEntrega(Pedido pedido) throws PedidoSinTransporteAsignado{

		gestor.notificarEntregaPedido(pedido);			

		String cod = pedido.getTransporte().getCodigo();

		// colocamos el transporte en la pos del cliente
		mapa.setPosicion(cod, mapa.getPosicion(pedido.getCliente().getCodigo()));

		System.out.println("Pedido de cliente " + pedido.getCliente().getCodigo() + 
				" es entregado por " + cod);	
		int i;
		boolean fin = false;
		for(i=0; i<pedidosPendientes.size() && !fin ;)
			if (pedidosPendientes.get(i).getTransporte()!=null &&
					cod.equals(pedidosPendientes.get(i).getTransporte().getCodigo()))
				fin = true;
			else
				i++;

		if (i<pedidosPendientes.size())	{
			System.out.println("Pedido pendiente de cliente " + 
					pedidosPendientes.get(i).getCliente().getCodigo() + 
					" para restaurante " + pedidosPendientes.get(i).getRestaurante().getCodigo() + 
					" ha sido asignado a " + 
					pedidosPendientes.get(i).getTransporte().getCodigo());

			// colocamos el transporte para el pedido pendiente en la pos restaurante
			mapa.setPosicion(pedidosPendientes.get(i).getTransporte().getCodigo(), 
					mapa.getPosicion(pedidosPendientes.get(i).getRestaurante().getCodigo()));
			pedidosPendientes.removeElementAt(i);
		} 

		System.out.println(getMapa(mapa.getPosicion(pedido.getCliente().getCodigo())));	

	}
}
