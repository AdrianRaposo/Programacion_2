
public class Cuenta {
	
	private String nombre;
	private double saldo;
	private static double gastosApertura=10;
	
	public Cuenta(String nombre, double saldoInicial) throws ExcepcionSaldoInicialInsuficiente {
		this.nombre=nombre;
		if(saldoInicial>gastosApertura) {
			this.saldo=saldoInicial-gastosApertura;
		}else {
			throw new ExcepcionSaldoInicialInsuficiente();
		}
		
		
	}
	public void sacarDinero(double cantidad) throws SaldoInsuficiente {
		if (cantidad>saldo)
			throw new SaldoInsuficiente();
		saldo-=cantidad;
	}
	public void ingresarDinero(double cantidad) {
		saldo+=cantidad;
	}

}
