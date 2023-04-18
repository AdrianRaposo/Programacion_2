package poligonos.figuras;

import list.IList;

public class Triangulo extends Poligono {
	//PRE: vertices.length == 3
	public Triangulo(IList<Punto> vertices){
		// TO-DO
		super(vertices);
	}

	@Override
	public String getTipo() {
		// TO-DO
		if(getLado(0)==getLado(1))
			if(getLado(1)==getLado(2))
				return "Equilátero";
			else
				return"Isósceles";
		else 
			if (getLado(0)==getLado(2))
				return"Isósceles";
			else
				return "Escaleno";
	}
	
}





