package banco.cuentas;

import banco.Cliente;
import banco.excepciones.ExcepcionClienteNoValido;
import banco.excepciones.ExcepcionImporteNoValido;

public class CuentaDeposito extends Cuenta{
	
	private boolean vencida;

	public CuentaDeposito(Cliente titular, double ingresoInicial)
			throws ExcepcionClienteNoValido, ExcepcionImporteNoValido {
		super(titular, ingresoInicial);
		this.vencida=false;
	
	}
	
	public String toString() {
		return super.toString() +" " + vencida;
		
	}

	@Override
	public boolean opRetiradaPermitida() {
		boolean res = false;
		if (vencida)
			res = true;
		return res;
	}

	@Override
	public boolean opIngresarPermitida() {
		return false;
	}

	@Override
	public double disponibilidad() {
		double res = 0;
		if (vencida) {
			res= getSaldo();
		}
		return res;
	}
	public void marcarVencido() {
		vencida = true;
	}
	

}
