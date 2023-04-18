package test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.junit.Before;
import org.junit.Test;

import banco.Banco;
import banco.Cliente;
import banco.TipoCuenta;
import banco.cuentas.Cuenta;
import banco.cuentas.CuentaAhorro;
import banco.cuentas.CuentaDeposito;
import banco.excepciones.ExcepcionClienteNoValido;
import banco.excepciones.ExcepcionCuentaNoValida;
import banco.excepciones.ExcepcionImporteNoValido;
import banco.excepciones.ExcepcionOperacionNoPermitida;
import banco.excepciones.ExcepcionSaldoInsuficiente;

public class JUnitTestBanco {
	
	private Banco banco;
	
	@Before
	public void setup() {
		banco = new Banco();
	}
	
	/**
	 * Test que comprueba el constructor de la clase Banco
	 * debe lanzarse una excepcion si el tipo de cuenta es null
	 * @throws ExcepcionCuentaNoValida 
	 */
	@Test (expected=ExcepcionCuentaNoValida.class)
	public void testAbrirCuentaExcepcion() throws ExcepcionCuentaNoValida  {
		
		try {
			banco.abrirCuenta(null, new Cliente("Luis", "37A"), 100);
		} catch (ExcepcionClienteNoValido e) {
			fail("La excepcion ExcepcionClienteNoValido no debe producirse ");
		} catch (ExcepcionImporteNoValido e) {
			fail("La excepcion ExcepcionImporteNoValido no debe producirse ");
		}
	}
	
	/**
	 * Test que comprueba el funcionamiento del metodo abrirCuenta
	 */
	@Test
	public void testAbrirCuenta() {	
		
		
		// creamos 4 cuentas en el banco y guardamos los indices que devuelve el banco 
		//    para dichas cuentas 
		try {
			int numCuentaIncial = this.getSiguienteNumeroCuenta();
			int cuenta = banco.abrirCuenta(TipoCuenta.AHORRO, new Cliente("Luis","93443N"), 100);
			assertTrue("Abrir cuenta no devuelve id de cuenta 1 cuando al crear la primera cuenta", (cuenta-numCuentaIncial)==1);
			cuenta=banco.abrirCuenta(TipoCuenta.AHORRO, new Cliente("Antonio","783642A"), 100);
			assertTrue("Abrir cuenta no devuelve id de cuenta 2 cuando al crear la segunda cuenta", (cuenta-numCuentaIncial)==2);
			cuenta=banco.abrirCuenta(TipoCuenta.DEPOSITO, new Cliente("Pedro","656879A"), 100);
			assertTrue("Abrir cuenta no devuelve id de cuenta 3 cuando al crear la tercera cuenta", (cuenta-numCuentaIncial)==3);
			int cuenta4 = banco.abrirCuenta(TipoCuenta.AHORRO, new Cliente("Sara","983422Y"), 100);
	
		} catch (ExcepcionCuentaNoValida e) {
			fail("La excepcion ExcepcionCuentaNoValida no debe producirse ");
		} catch (ExcepcionClienteNoValido e) {
			fail("La excepcion ExcepcionClienteNoValido no debe producirse ");
		} catch (ExcepcionImporteNoValido e) {
			fail("La excepcion ExcepcionImporteNoValido no debe producirse ");
		}
	}

	/**
	 * Test que comprueba el funcionamiento del metodo buscarCuenta
	 * excepcion se intenta buscar una cuenta que no existe en un banco lleno
	 */
	@Test 
	public void testBuscarCuentaNoExiste() {	
		try{
			
			
			// obtenemos los objetos de cuenta desde los indices que devolvio
			// el metodo abrir cuenta, y comprobamos el saldo y tipo de las cuentas creadas
			Cuenta cuentaCorrientePrueba1 = banco.buscarCuenta(12);
			assertTrue("Buscar cuenta no devuelve null cuando la cuenta no existe (el banco esta vacio)", cuentaCorrientePrueba1==null );
			
			int cuenta1 = banco.abrirCuenta(TipoCuenta.AHORRO, new Cliente("Luis","93443N"), 100);		
			int cuenta2 = banco.abrirCuenta(TipoCuenta.AHORRO, new Cliente("Antonio","783642A"), 100);
			int cuenta3 = banco.abrirCuenta(TipoCuenta.DEPOSITO, new Cliente("Pedro","656879A"), 100);
			
			
			cuentaCorrientePrueba1 = banco.buscarCuenta(cuenta1+cuenta2+cuenta3);
			assertTrue("Buscar cuenta no devuelve null cuando la cuenta no existe", cuentaCorrientePrueba1==null);
		}catch (ExcepcionCuentaNoValida e) {
			fail("La excepcion ExcepcionCuentaNoValida no debe producirse ");
		} catch (ExcepcionClienteNoValido e) {
			fail("La excepcion ExcepcionClienteNoValido no debe producirse ");
		} catch (ExcepcionImporteNoValido e) {
			fail("La excepcion ExcepcionImporteNoValido no debe producirse ");
		}
		
	}
	
	/**
	 * Test que comprueba el funcionamiento del metodo buscarCuenta
	 */
	@Test 
	public void testBuscarCuenta() {	
		try{
			
			
			// creamos 4 cuentas en el banco y guardamos los indices que devuelve el banco 
			//    para dichas cuentas 
			int cuenta1 = banco.abrirCuenta(TipoCuenta.AHORRO, new Cliente("Luis","93443N"), 100);
			int cuenta2 = banco.abrirCuenta(TipoCuenta.AHORRO, new Cliente("Antonio","783642A"), 100);
			int cuenta3 = banco.abrirCuenta(TipoCuenta.DEPOSITO, new Cliente("Pedro","656879A"), 100);			
			
			// obtenemos los objetos de cuenta desde los indices que devolvio
			// el metodo abrir cuenta, y comprobamos el saldo y tipo de las cuentas creadas
			Cuenta cuentaCorrientePrueba1 = banco.buscarCuenta(cuenta1);
			assertTrue("buscarCuenta no devuelve una cuenta correcta, al inicio", cuentaCorrientePrueba1!=null && cuentaCorrientePrueba1.getSaldo()==100 && (cuentaCorrientePrueba1 instanceof CuentaAhorro));
			Cuenta cuentaCorrientePrueba2 = banco.buscarCuenta(cuenta2);
			assertTrue("buscarCuenta no devuelve una cuenta correcta, en medio", cuentaCorrientePrueba2!=null && cuentaCorrientePrueba2.getSaldo()==100 && (cuentaCorrientePrueba2 instanceof CuentaAhorro));
			Cuenta cuentaCorrientePrueba3 = banco.buscarCuenta(cuenta3);
			assertTrue("buscarCuenta no devuelve una cuenta correcta, al final", cuentaCorrientePrueba3!=null && cuentaCorrientePrueba3.getSaldo()==100 && (cuentaCorrientePrueba3 instanceof CuentaDeposito));
		}catch (ExcepcionCuentaNoValida e) {
			fail("La excepcion ExcepcionCuentaNoValida no debe producirse ");
		} catch (ExcepcionClienteNoValido e) {
			fail("La excepcion ExcepcionClienteNoValido no debe producirse ");
		} catch (ExcepcionImporteNoValido e) {
			fail("La excepcion ExcepcionImporteNoValido no debe producirse ");
		}		
	}

	/**
	 * Test que comprueba el funcionamiento del metodo ingresar
	 * excepcion se intenta ingresar en una cuenta que no existe en un banco vacio
	 */
	@Test (expected=ExcepcionCuentaNoValida.class)
	public void testIngresarExcepcionCuentaNoValida() throws ExcepcionCuentaNoValida {	
		int importeIngresar = 100;
		try{
			
			
			
			// intentamos ingresar en una cuenta, estando el banco vacio
			int cuenta1 = 17;
			
			banco.ingresar(cuenta1, importeIngresar);
			
		} catch (ExcepcionImporteNoValido e) {
			fail("La excepcion ExcepcionImporteNoValido no debe producirse ");
		} catch (ExcepcionOperacionNoPermitida e) {
			fail("La excepcion ExcepcionOperacionNoPermitida no debe producirse ");
		}			
	}

	/**
	 * Test que comprueba el funcionamiento del metodo ingresar
	 * excepcion se intenta ingresar en una cuenta que no existe en un banco lleno
	 */
	@Test (expected=ExcepcionCuentaNoValida.class)
	public void testIngresarExcepcionCuentaNoValida2() throws ExcepcionCuentaNoValida {	
		int importeIngresar = 100;
		try{
			
			
			// creamos 4 cuentas en el banco y guardamos los indices que devuelve el banco 
			//    para dichas cuentas 
			int cuenta1 = banco.abrirCuenta(TipoCuenta.AHORRO, new Cliente("Luis","93443N"), 100);
			int cuenta2 = banco.abrirCuenta(TipoCuenta.AHORRO, new Cliente("Antonio","783642A"), 100);
			int cuenta3 = banco.abrirCuenta(TipoCuenta.DEPOSITO, new Cliente("Pedro","656879A"), 100);			
			
			banco.ingresar(cuenta1+cuenta2+cuenta3, importeIngresar);
		}catch (ExcepcionClienteNoValido e) {
			fail("La excepcion ExcepcionClienteNoValido no debe producirse ");
		} catch (ExcepcionImporteNoValido e) {
			fail("La excepcion ExcepcionImporteNoValido no debe producirse ");
		} catch (ExcepcionOperacionNoPermitida e) {
			fail("La excepcion ExcepcionOperacionNoPermitida no debe producirse ");
		}		
	}
	
	/**
	 * Test que comprueba el funcionamiento del metodo ingresar
	 */
	@Test 
	public void testIngresar() {	
		int importeIngresar = 100;
		try{
			
			
			// creamos 4 cuentas en el banco y guardamos los indices que devuelve el banco 
			//    para dichas cuentas 
			int cuenta1 = banco.abrirCuenta(TipoCuenta.AHORRO, new Cliente("Luis","93443N"), 100);
			int cuenta2 = banco.abrirCuenta(TipoCuenta.AHORRO, new Cliente("Antonio","783642A"), 100);
			banco.abrirCuenta(TipoCuenta.DEPOSITO, new Cliente("Pedro","656879A"), 100);			
			
			// obtenemos los objetos de cuenta desde los indices que devolvio
			// el metodo abrir cuenta, y comprobamos el saldo y tipo de las cuentas creadas
			Cuenta cuentaCorrientePrueba1 = banco.buscarCuenta(cuenta1);
			Cuenta cuentaCorrientePrueba2 = banco.buscarCuenta(cuenta2);			
			
			// comprobamos que se ingresa bien el dinero en una CuentaAhorro desde el banco
			banco.ingresar(cuenta1, importeIngresar);
			assertTrue("El metodo ingresar no devuelve una cuenta correcta para ingresar", cuentaCorrientePrueba1.getSaldo()==importeIngresar+100 );
			banco.ingresar(cuenta2, importeIngresar);
			assertTrue("El metodo ingresar no devuelve una cuenta correcta para ingresar", cuentaCorrientePrueba2.getSaldo()==importeIngresar+100 );
			
		} catch (ExcepcionCuentaNoValida e) {
			fail("La excepcion ExcepcionCuentaNoValida no debe producirse ");
		} catch (ExcepcionClienteNoValido e) {
			fail("La excepcion ExcepcionClienteNoValido no debe producirse ");
		} catch (ExcepcionImporteNoValido e) {
			fail("La excepcion ExcepcionImporteNoValido no debe producirse ");
		} catch (ExcepcionOperacionNoPermitida e) {
			fail("La excepcion ExcepcionOperacionNoPermitida no debe producirse ");
		}			
	}

	/**
	 * Test que comprueba el funcionamiento del metodo retirar
	 * excepcion se intenta retirar de una cuenta que no existe en un banco vacio
	 */
	@Test (expected=ExcepcionCuentaNoValida.class)
	public void testRetirarExcepcionCuentaNoValida() throws ExcepcionCuentaNoValida {	
		int importeRetirar = 70;
		try{
			
			
			
			// intentamos retirar en una cuenta, estando el banco vacio
			int cuenta1 = 17;
			
			banco.retirar(cuenta1, importeRetirar);
			
		} catch (ExcepcionImporteNoValido e) {
			fail("La excepcion ExcepcionImporteNoValido no debe producirse ");
		} catch (ExcepcionOperacionNoPermitida e) {
			fail("La excepcion ExcepcionOperacionNoPermitida no debe producirse ");
		} catch (ExcepcionSaldoInsuficiente e) {
			fail("La excepcion ExcepcionSaldoInsuficiente no debe producirse ");
		}			
	}

	/**
	 * Test que comprueba el funcionamiento del metodo retirar
	 * excepcion se intenta retirar de una cuenta que no existe en un banco lleno
	 */
	@Test (expected=ExcepcionCuentaNoValida.class)
	public void testRetirarExcepcionCuentaNoValida2() throws ExcepcionCuentaNoValida {	
		int importeRetirar = 70;
		try{
			
			
			// creamos 4 cuentas en el banco y guardamos los indices que devuelve el banco 
			//    para dichas cuentas 
			int cuenta1 = banco.abrirCuenta(TipoCuenta.AHORRO, new Cliente("Luis","93443N"), 100);
			int cuenta2 = banco.abrirCuenta(TipoCuenta.AHORRO, new Cliente("Antonio","783642A"), 100);
			int cuenta3 = banco.abrirCuenta(TipoCuenta.DEPOSITO, new Cliente("Pedro","656879A"), 100);			
			
			banco.retirar(cuenta1+cuenta2+cuenta3, importeRetirar);
		}catch (ExcepcionClienteNoValido e) {
			fail("La excepcion ExcepcionClienteNoValido no debe producirse ");
		} catch (ExcepcionImporteNoValido e) {
			fail("La excepcion ExcepcionImporteNoValido no debe producirse ");
		} catch (ExcepcionOperacionNoPermitida e) {
			fail("La excepcion ExcepcionOperacionNoPermitida no debe producirse ");
		} catch (ExcepcionSaldoInsuficiente e) {
			fail("La excepcion ExcepcionSaldoInsuficiente no debe producirse ");
		}		
	}
	
	/**
	 * Test que comprueba el funcionamiento del metodo retirar
	 */
	@Test 	
	public void testRetirar() {	
		int importeRetirar = 70;
		try{
			
			
			// creamos 4 cuentas en el banco y guardamos los indices que devuelve el banco 
			//    para dichas cuentas 
			int cuenta1 = banco.abrirCuenta(TipoCuenta.AHORRO, new Cliente("Luis","93443N"), 100);
			int cuenta2 = banco.abrirCuenta(TipoCuenta.AHORRO, new Cliente("Antonio","783642A"), 100);
			int cuenta3 = banco.abrirCuenta(TipoCuenta.DEPOSITO, new Cliente("Pedro","656879A"), 100);			
			
			// obtenemos los objetos de cuenta desde los indices que devolvio
			// el metodo abrir cuenta, y comprobamos el saldo y tipo de las cuentas creadas
			Cuenta cuentaCorrientePrueba1 = banco.buscarCuenta(cuenta1);
			Cuenta cuentaCorrientePrueba2 = banco.buscarCuenta(cuenta2);
			banco.buscarCuenta(cuenta3);
			
			// comprobamos que se retira bien el dinero en una CuentaAhorro desde el banco			
			banco.retirar(cuenta1, importeRetirar);
			assertTrue("El metodo retirar no devuelve una cuenta correcta para retirar", cuentaCorrientePrueba1.getSaldo()==100-importeRetirar );
			banco.retirar(cuenta2, importeRetirar);
			assertTrue("El metodo retirar no devuelve una cuenta correcta para retirar", cuentaCorrientePrueba2.getSaldo()==100-importeRetirar );
			
		} catch (ExcepcionCuentaNoValida e) {
			fail("La excepcion ExcepcionCuentaNoValida no debe producirse ");
		} catch (ExcepcionClienteNoValido e) {
			fail("La excepcion ExcepcionClienteNoValido no debe producirse ");
		} catch (ExcepcionImporteNoValido e) {
			fail("La excepcion ExcepcionImporteNoValido no debe producirse ");
		} catch (ExcepcionOperacionNoPermitida e) {
			fail("La excepcion ExcepcionOperacionNoPermitida no debe producirse ");
		} catch (ExcepcionSaldoInsuficiente e) {
			fail("La excepcion ExcepcionSaldoInsuficiente no debe producirse ");
		}			
	}	

	/**
	 * Test que comprueba el funcionamiento del metodo getSaldo
	 * excepcion se intenta obtener saldo de una cuenta que no existe en un banco vacio
	 */
	@Test (expected=ExcepcionCuentaNoValida.class)
	public void testGetSaldoCuentaNoValida() throws ExcepcionCuentaNoValida {	
		
		
		
		// intentamos obtener el saldo en una cuenta, estando el banco vacio
		int cuenta1 = 17;
		
		banco.getSaldo(cuenta1);		
	}

	/**
	 * Test que comprueba el funcionamiento del metodo getSaldo
	 * excepcion se intenta obtener saldo de una cuenta que no existe en un banco lleno
	 */
	@Test (expected=ExcepcionCuentaNoValida.class)
	public void testGetSaldoCuentaNoValida2() throws ExcepcionCuentaNoValida {	
		try{
			
			
			// creamos 4 cuentas en el banco y guardamos los indices que devuelve el banco 
			//    para dichas cuentas 
			int cuenta1 = banco.abrirCuenta(TipoCuenta.AHORRO, new Cliente("Luis","93443N"), 100);
			int cuenta2 = banco.abrirCuenta(TipoCuenta.AHORRO, new Cliente("Antonio","783642A"), 100);
			int cuenta3 = banco.abrirCuenta(TipoCuenta.DEPOSITO, new Cliente("Pedro","656879A"), 100);			
			
			banco.getSaldo(cuenta1+cuenta2+cuenta3);
		}catch (ExcepcionClienteNoValido e) {
			fail("La excepcion ExcepcionClienteNoValido no debe producirse ");
		} catch (ExcepcionImporteNoValido e) {
			fail("La excepcion ExcepcionImporteNoValido no debe producirse ");
		} 
	}
	
	/**
	 * Test que comprueba el funcionamiento del metodo getSaldo
	 */
	@Test 
	public void testGetSaldo() {	
		try{
			
			
			// creamos 4 cuentas en el banco y guardamos los indices que devuelve el banco 
			//    para dichas cuentas 
			int cuenta1 = banco.abrirCuenta(TipoCuenta.AHORRO, new Cliente("Luis","93443N"), 100);
			int cuenta2 = banco.abrirCuenta(TipoCuenta.AHORRO, new Cliente("Antonio","783642A"), 100);
			int cuenta3 = banco.abrirCuenta(TipoCuenta.DEPOSITO, new Cliente("Pedro","656879A"), 100);			
			
			// obtenemos los objetos de cuenta desde los indices que devolvio
			// el metodo abrir cuenta, y comprobamos el saldo y tipo de las cuentas creadas
			Cuenta cuentaCorrientePrueba1 = banco.buscarCuenta(cuenta1);
			Cuenta cuentaCorrientePrueba2 = banco.buscarCuenta(cuenta2);
			Cuenta cuentaCorrientePrueba3 = banco.buscarCuenta(cuenta3);
			
			// comprobamos que se consulta el saldo bien desde el banco						
			assertTrue("El metodo getSaldo no devuelve una cuenta correcta", banco.getSaldo(cuenta1) == cuentaCorrientePrueba1.getSaldo() );
			assertTrue("El metodo getSaldo no devuelve una cuenta correcta", banco.getSaldo(cuenta2) == cuentaCorrientePrueba2.getSaldo() );
			assertTrue("El metodo getSaldo no devuelve una cuenta correcta", banco.getSaldo(cuenta3) == cuentaCorrientePrueba3.getSaldo() );
			
		} catch (ExcepcionCuentaNoValida e) {
			fail("La excepcion ExcepcionCuentaNoValida no debe producirse ");
		} catch (ExcepcionClienteNoValido e) {
			fail("La excepcion ExcepcionClienteNoValido no debe producirse ");
		} catch (ExcepcionImporteNoValido e) {
			fail("La excepcion ExcepcionImporteNoValido no debe producirse ");
		} 
	}

	/**
	 * Test que comprueba el funcionamiento del metodo marcarVencido
	 * excepcion se intenta marcar vencido una cuenta que no existe en un banco vacio
	 */
	@Test (expected=ExcepcionCuentaNoValida.class)
	public void testMarcarVencidoCuentaNoValida() throws ExcepcionCuentaNoValida {	
		
		
		
		// intentamos marcar vencido en una cuenta, estando el banco vacio
		int cuenta1 = 17;
		
		banco.marcarVencido(cuenta1);		
	}

	/**
	 * Test que comprueba el funcionamiento del metodo marcarVencido
	 * excepcion se intenta marcar vencido una cuenta que no existe en un banco lleno
	 */
	@Test (expected=ExcepcionCuentaNoValida.class)
	public void testMarcarVencidoCuentaNoValida2() throws ExcepcionCuentaNoValida {	
		try{
			
			
			// creamos 4 cuentas en el banco y guardamos los indices que devuelve el banco 
			//    para dichas cuentas 
			int cuenta1 = banco.abrirCuenta(TipoCuenta.AHORRO, new Cliente("Luis","93443N"), 100);
			int cuenta2 = banco.abrirCuenta(TipoCuenta.AHORRO, new Cliente("Antonio","783642A"), 100);
			int cuenta3 = banco.abrirCuenta(TipoCuenta.DEPOSITO, new Cliente("Pedro","656879A"), 100);			
			
			banco.marcarVencido(cuenta1+cuenta2+cuenta3);
		}catch (ExcepcionClienteNoValido e) {
			fail("La excepcion ExcepcionClienteNoValido no debe producirse ");
		} catch (ExcepcionImporteNoValido e) {
			fail("La excepcion ExcepcionImporteNoValido no debe producirse ");
		} 
	}

	/**
	 * Test que comprueba el funcionamiento del metodo marcarVencido
	 * excepcion se intenta marcar vencido una cuentaAhorro
	 */
	@Test (expected=ExcepcionCuentaNoValida.class)
	public void testMarcarVencidoCuentaNoValida3() throws ExcepcionCuentaNoValida {	
		try{
			
			
			// creamos 4 cuentas en el banco y guardamos los indices que devuelve el banco 
			//    para dichas cuentas 
			int cuenta1 = banco.abrirCuenta(TipoCuenta.AHORRO, new Cliente("Luis","93443N"), 100);
			banco.abrirCuenta(TipoCuenta.AHORRO, new Cliente("Antonio","783642A"), 100);
			banco.abrirCuenta(TipoCuenta.DEPOSITO, new Cliente("Pedro","656879A"), 100);			
			
			banco.marcarVencido(cuenta1);
		}catch (ExcepcionClienteNoValido e) {
			fail("La excepcion ExcepcionClienteNoValido no debe producirse ");
		} catch (ExcepcionImporteNoValido e) {
			fail("La excepcion ExcepcionImporteNoValido no debe producirse ");
		} 
	}
	
	/**
	 * Test que comprueba el funcionamiento del metodo marcarVencido
	 */
	@Test 
	public void testMarcarVencido() {	
		try{
			
			
			// creamos 4 cuentas en el banco y guardamos los indices que devuelve el banco 
			//    para dichas cuentas 
			banco.abrirCuenta(TipoCuenta.AHORRO, new Cliente("Luis","93443N"), 100);
			banco.abrirCuenta(TipoCuenta.AHORRO, new Cliente("Antonio","783642A"), 100);
			int cuenta3 = banco.abrirCuenta(TipoCuenta.DEPOSITO, new Cliente("Pedro","656879A"), 100);			
			
			// obtenemos los objetos de cuenta desde los indices que devolvio
			// el metodo abrir cuenta, y comprobamos el saldo y tipo de las cuentas creadas
			Cuenta cuentaCorrientePrueba3 = banco.buscarCuenta(cuenta3);
		
			assertTrue("El metodo buscarCuenta no devuelve una cuenta correcta", cuentaCorrientePrueba3.disponibilidad() == 0 );
			
			banco.marcarVencido(cuenta3);
			
			// comprobamos que se consulta el saldo bien desde el banco						
			assertTrue("El metodo marcarVencido no modifica la cuenta correcta", banco.getSaldo(cuenta3) == cuentaCorrientePrueba3.disponibilidad());
			
		} catch (ExcepcionCuentaNoValida e) {
			fail("La excepcion ExcepcionCuentaNoValida no debe producirse ");
		} catch (ExcepcionClienteNoValido e) {
			fail("La excepcion ExcepcionClienteNoValido no debe producirse ");
		} catch (ExcepcionImporteNoValido e) {
			fail("La excepcion ExcepcionImporteNoValido no debe producirse ");
		} 
	}
	
//Auxiliar
	/**
	 * Se busca un atributo en cuanta de calse que debe representar el número de cuetnas creadas y debería indicar o el siguiente número de cuenta válido o el último número de cuenta válido
	 * @return
	 */
    private int getSiguienteNumeroCuenta() {
    	int singNumeroCuenta = 0;
    	Class<?> secretClass =Cuenta.class;
     
        Field campos[];
		try {
			campos = secretClass.getDeclaredFields(); //Se optinene todos los atributos
			boolean encontrado = false;
			for (int i=0;!encontrado && i<campos.length;i++){//For
			 int modificadores = campos[i].getModifiers();
			 if (Modifier.isStatic(modificadores) ) //Sólo se analizan los atributos de clase
			 {//IF
				 @SuppressWarnings("deprecation")
				boolean accesible = campos[i].isAccessible();
				 campos[i].setAccessible(true);
				 Object objeto = campos[i].get(null);
				 encontrado = objeto instanceof Integer; //Se busca uno que sea de tipo entero. ¡Sólo debería haber uno!
				 if (encontrado)
					 singNumeroCuenta = (int)campos[i].get(null);
				 campos[i].setAccessible(accesible); 
			 }//IF
			 
			
			}//For
		} catch ( SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return singNumeroCuenta;
    }
}
