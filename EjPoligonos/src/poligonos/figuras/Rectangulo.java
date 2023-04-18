package poligonos.figuras;

import list.IList;

public class Rectangulo extends Poligono {
	//PRE: vertices.size() == 4
	public Rectangulo(IList<Punto> vertices){
		super(vertices);
	}

	@Override
	public String getTipo() {
		return (getLado(0)==getLado(1))?  "Cuadrado": "Rectangulo"; 
	}
	
	
	
}
