package banco;
import banco.cuentas.Cuenta;
import banco.cuentas.CuentaAhorro;
import banco.cuentas.CuentaDeposito;
import banco.excepciones.ExcepcionClienteNoValido;
import banco.excepciones.ExcepcionCuentaNoValida;
import banco.excepciones.ExcepcionImporteNoValido;
import banco.excepciones.ExcepcionOperacionNoPermitida;
import banco.excepciones.ExcepcionSaldoInsuficiente;
import list.ArrayList;
import list.IList;

public class Banco {

	private IList<Cuenta> cuentas;
	public Banco() {
		cuentas = new ArrayList<>();
	}

	/*public int abrirCuenta(TipoCuenta tipo, Cliente cliente, double saldoInicial) throws ExcepcionCuentaNoValida,
			IndexOutOfBoundsException, ExcepcionClienteNoValido, ExcepcionImporteNoValido {
		if (tipo == null || (tipo != TipoCuenta.AHORRO && tipo != TipoCuenta.DEPOSITO))
			throw new ExcepcionCuentaNoValida(
					"ERROR:El tipo de cuenta " + tipo + "  seleccionado no existe o nos es valido.");
		if (tipo == TipoCuenta.AHORRO)
			cuentas.add(0, new CuentaAhorro(cliente, saldoInicial));
		else
			cuentas.add(0, new CuentaDeposito(cliente, saldoInicial));

		return cuentas.get(0).getNumero();
	}*/
	//OTRA FORMA
	
	public int abrirCuenta(TipoCuenta tipo, Cliente cliente, double saldoInicial) throws ExcepcionCuentaNoValida,
			IndexOutOfBoundsException, ExcepcionClienteNoValido, ExcepcionImporteNoValido {
		Cuenta nueva;
		if (tipo == null) {
			throw new ExcepcionCuentaNoValida("ERROR:El tipo de cuenta es nulo");
		} else {
			switch (tipo) {
			case AHORRO:
				nueva = new CuentaAhorro(cliente, saldoInicial);
				break;
			case DEPOSITO:
				nueva = new CuentaDeposito(cliente, saldoInicial);
				break;
			default:
				throw new ExcepcionCuentaNoValida(
						"ERROR:El tipo de cuenta " + tipo + "  seleccionado no existe o nos es valido.");
			}
			cuentas.add(cuentas.size(), nueva);
			return nueva.getNumero();
		}
	}

	public Cuenta buscarCuenta(int numero) {
		Cuenta res=null;
		for(int i=0; i<cuentas.size()&& res==null;i++) {
			if(cuentas.get(i).getNumero()==numero) {
				res=cuentas.get(i);
			}
		}
		return res;
	}

	public void ingresar(int numero, double dinero)
			throws ExcepcionCuentaNoValida, ExcepcionOperacionNoPermitida, ExcepcionImporteNoValido {
		if (buscarCuenta(numero) == null)
			throw new ExcepcionCuentaNoValida("ERROR:Cuenta " + numero + " no encontrada.");
		buscarCuenta(numero).ingresar(dinero);
	}

	public void retirar(int numero, double dinero) throws ExcepcionCuentaNoValida, ExcepcionSaldoInsuficiente,
			ExcepcionImporteNoValido, ExcepcionOperacionNoPermitida {
		if (buscarCuenta(numero) == null)
			throw new ExcepcionCuentaNoValida("ERROR:Cuenta " + numero + " no encontrada.");
		buscarCuenta(numero).retirar(dinero);
	}

	public double getSaldo(int numero) throws ExcepcionCuentaNoValida {
		if (buscarCuenta(numero) == null)
			throw new ExcepcionCuentaNoValida("ERROR:Cuenta " + numero + " no encontrada.");
		return buscarCuenta(numero).getSaldo();

	}
	
	public void marcarVencido(int numero) throws ExcepcionCuentaNoValida {
		if (!(buscarCuenta(numero) instanceof CuentaDeposito))
			throw new ExcepcionCuentaNoValida("ERROR:Cuenta " + numero + " no encontrada.");
		else {
			((CuentaDeposito)buscarCuenta(numero)).marcarVencido();
			
		}
	}
	
	
}
