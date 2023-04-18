package banco.cuentas;

import banco.Cliente;
import banco.excepciones.ExcepcionClienteNoValido;
import banco.excepciones.ExcepcionImporteNoValido;

public class CuentaAhorro extends Cuenta{

	public CuentaAhorro(Cliente titular, double ingresoInicial)
			throws ExcepcionClienteNoValido, ExcepcionImporteNoValido {
		super(titular, ingresoInicial);
	}
	
	public double disponibilidad() {
		return getSaldo() ;
	}
	public boolean opRetiradaPermitida() {
		return true;
	}
	public boolean opIngresarPermitida() {
		return true;
	}


	
	

}
