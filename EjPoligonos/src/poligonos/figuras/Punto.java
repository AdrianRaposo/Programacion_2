package poligonos.figuras;

public class Punto {

	private int x;
	private int y;

	public Punto(int X, int Y) {
		this.x = X;
		this.y = Y;
	}

	// Constructor de copia
	public Punto(Punto puntoACopiar) {
		this.x = puntoACopiar.getX();
		this.y = puntoACopiar.getY();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	

	public String toString() {
	 return "[" + x + "," + y + "]";
	}

	public boolean equals(Object obj) {
	    Punto otroPunto = (Punto) obj;
		return this.x == otroPunto.getX()
				&& this.y == otroPunto.getY();
	}

	public double calculaDistancia(Punto v2) {
		// TODO Auto-generated method stub
		return Math.sqrt(Math.pow((this.x - v2.x), 2) +
				Math.pow((this.y - v2.y), 2));
	}
}
