package test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

//import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashSet;

import org.junit.Test;

//import anotacion.Programacion2;
//import banco.Banco;
import banco.Cliente;
import banco.cuentas.Cuenta;
import banco.cuentas.CuentaDeposito;
import banco.excepciones.ExcepcionClienteNoValido;
import banco.excepciones.ExcepcionImporteNoValido;
import banco.excepciones.ExcepcionOperacionNoPermitida;
import banco.excepciones.ExcepcionSaldoInsuficiente;

public class JUnitTestCuentaDeposito {
	

	
	/**
	 * Test que comprueba el correcto funcionamiento del constructor
	 * y que la clase CuentaDeposito sea hija de la clase Cuenta
	 * @throws ExcepcionClienteNoValido
	 */
	@Test
	public void testHerenciaCuentas() throws ExcepcionClienteNoValido  {
		try {
			@SuppressWarnings("unused")
			Cuenta cuentaCorriente = new CuentaDeposito(new Cliente("Juan", "3098765A"),100.0);
			cuentaCorriente = new CuentaDeposito(new Cliente("Juan", "3098765A"),100.0);
		} catch (ExcepcionImporteNoValido e) {
			fail("La excepcion ExcepcionImporteNoValido no debe producirse ");
		}
		
	}

	
	/**
	 * 	Test que comprueba el correcto funcionamiento del constructor
	 *  se intenta crear la cuenta con un cliente null / invalido
	 * @throws ExcepcionClienteNoValido 
	 */
	@Test(expected = ExcepcionClienteNoValido.class)
	public void testConstructorClienteNoValido() throws ExcepcionClienteNoValido {
		try{
			@SuppressWarnings("unused")
			Cuenta cuentaCorriente = new CuentaDeposito(null,1000.0);
		} catch (ExcepcionImporteNoValido e) {
			fail("La excepcion ExcepcionImporteNoValido no debe producirse ");
		}		
	}
	
	/**
	 *  Test que comprueba el correcto funcionamiento del constructor
	 *  se intenta crear la cuenta con saldo 0
	 * @throws ExcepcionImporteNoValido
	 */
	@Test(expected = ExcepcionImporteNoValido.class)
	public void testConstructorCantidadNoValida() throws ExcepcionImporteNoValido {
		try{
			@SuppressWarnings("unused")
			Cuenta cuentaCorriente = new CuentaDeposito(new Cliente("Juan", "3098765A"),0.0);
		} catch (ExcepcionClienteNoValido e) {
			fail("La excepcion ExcepcionClienteNoValido no debe producirse ");
		}
	}

	/**
	 *  Test que comprueba el correcto funcionamiento del constructor
	 *  se intenta crear la cuenta con saldo negativo
	 * @throws ExcepcionImporteNoValido 
	 */
	@Test(expected = ExcepcionImporteNoValido.class)
	public void testConstructorCantidadNoValida2() throws ExcepcionImporteNoValido {
		try{
			@SuppressWarnings("unused")
			Cuenta cuentaCorriente = new CuentaDeposito(new Cliente("Juan", "3098765A"),-100.0);
		} catch (ExcepcionClienteNoValido e) {
			fail("La excepcion ExcepcionClienteNoValido no debe producirse ");
		} 
	}
	
	/**
	 * Test que comprueba el correcto funcionamiento del constructor
	 *    se comprueba que se inicialicen correctamente los atributos
	 * PRE: Los getters de la clase estan implementados
	 */
	@Test
	public void testConstructorInicializacion()  {
		try{
			Cliente clientePrueba = new Cliente("Juan", "3098765A");
			double cantidadInicial = 100.0;
			Cuenta cuentaCorriente = new CuentaDeposito(clientePrueba,cantidadInicial);
			int numeroCuentaCorriente = cuentaCorriente.getNumero();
			assertTrue("Atributo saldo mal inicializado", cuentaCorriente.getSaldo()==cantidadInicial);
			assertTrue("Atributo cliente mal inicializado", cuentaCorriente.getTitular()==clientePrueba);
			assertTrue("Atributo fecha apertura mal inicializado", cuentaCorriente.getFechaApertura() instanceof Date);
			Cuenta cuentaCorriente2 = new CuentaDeposito(clientePrueba,cantidadInicial);
			assertTrue("Atributo numero de cuenta no se actualiza correctamente", numeroCuentaCorriente - cuentaCorriente2.getNumero()==-1);
		} catch (ExcepcionClienteNoValido e) {
			fail("La excepcion ExcepcionClienteNoValido no debe producirse ");
		} catch (ExcepcionImporteNoValido e) {
			fail("La excepcion ExcepcionImporteNoValido no debe producirse ");
		}
	}
	
	/**
	 * Test que comprueba el correcto funcionamiento del método ingresar
	 *    no se permite ingresar en cuentas de tipo depósito
	 * @throws ExcepcionOperacionNoPermitida
	 */
	@Test  (expected = ExcepcionOperacionNoPermitida.class)
	public void testIngresar() throws ExcepcionOperacionNoPermitida  {
		try{
			Cliente clientePrueba = new Cliente("Juan", "3098765A");
			
			double cantidadInicial = 100.0;
			Cuenta cuentaCorriente = new CuentaDeposito(clientePrueba,cantidadInicial);
			double cantidadIngresar = 1000.0;
			cuentaCorriente.ingresar(cantidadIngresar);
		} catch (ExcepcionClienteNoValido e) {
			fail("La excepcion ExcepcionClienteNoValido no debe producirse ");
		} catch (ExcepcionImporteNoValido e) {
			fail("La excepcion ExcepcionImporteNoValido no debe producirse ");
		}
	}	
	
	/**
	 * Test que comprueba el correcto funcionamiento del método disponibilidad
	 *   la disponibilidad es 0 hasta que se marca la cuenta depósito como vencida
	 *   entonces la disponibilidad debe ser igual al saldo
	 */
	@Test
	public void testDisponibilidad()   {
		try{
			Cliente clientePrueba = new Cliente("Juan", "3098765A");		
			double cantidadInicial = 100.0;
			Cuenta cuentaCorriente = new CuentaDeposito(clientePrueba,cantidadInicial);
			assertTrue("Metodo disponibilidad cuenta deposito mal implementado", cuentaCorriente.disponibilidad()==0);
			((CuentaDeposito)cuentaCorriente).marcarVencido();
			assertTrue("Metodo disponibilidad cuenta deposito mal implementado", cuentaCorriente.disponibilidad()==cuentaCorriente.getSaldo());
		} catch (ExcepcionClienteNoValido e) {
			fail("La excepcion ExcepcionClienteNoValido no debe producirse ");
		} catch (ExcepcionImporteNoValido e) {
			fail("La excepcion ExcepcionImporteNoValido no debe producirse ");
		}
	}		

	/**
	 * Test que comprueba el correcto funcionamiento del método Retirar
	 *   no se permite retirar hasta que se marca la cuenta depósito como vencida
	 * @throws ExcepcionOperacionNoPermitida 
	 */
	@Test (expected = ExcepcionOperacionNoPermitida.class)
	public void testRetirarExcepcion() throws ExcepcionOperacionNoPermitida  {
		try{
			Cliente clientePrueba = new Cliente("Juan", "3098765A");
			double cantidadInicial = 100.0;
			Cuenta cuentaCorriente = new CuentaDeposito(clientePrueba,cantidadInicial);
			double cantidadRetirar = 10.0;
			cuentaCorriente.retirar(cantidadRetirar);
		} catch (ExcepcionClienteNoValido e) {
			fail("La excepcion ExcepcionClienteNoValido no debe producirse ");
		} catch (ExcepcionImporteNoValido e) {
			fail("La excepcion ExcepcionImporteNoValido no debe producirse ");
		} catch (ExcepcionSaldoInsuficiente e) {
			fail("La excepcion ExcepcionSaldoInsuficiente no debe producirse ");
		}
	}	
	
	/**
	 * Test que comprueba el correcto funcionamiento del método retirar
	 *   se permite cuando se marca la cuenta depósito como vencida
	 *   da error si se trata de sacar más dinero del que hay en la cuenta
	 * @throws ExcepcionSaldoInsuficiente
	 */
	@Test (expected = ExcepcionSaldoInsuficiente.class)
	public void testRetirarExcepcion2() throws ExcepcionSaldoInsuficiente  {
		try{
			Cliente clientePrueba = new Cliente("Juan", "3098765A");		
			double cantidadInicial = 100.0;
			Cuenta cuentaCorriente = new CuentaDeposito(clientePrueba,cantidadInicial);
			double cantidadRetirar = 1000.0;
			((CuentaDeposito)cuentaCorriente).marcarVencido();
			cuentaCorriente.retirar(cantidadRetirar);
		} catch (ExcepcionClienteNoValido e) {
			fail("La excepcion ExcepcionClienteNoValido no debe producirse ");
		} catch (ExcepcionImporteNoValido e) {
			fail("La excepcion ExcepcionImporteNoValido no debe producirse ");
		} catch (ExcepcionOperacionNoPermitida e) {
			fail("La excepcion ExcepcionOperacionNoPermitida no debe producirse ");
		} 
	}

	/**
	 * Test que comprueba el correcto funcionamiento del método retirar
	 *   se permite cuando se marca la cuenta depósito como vencida
	 *   da error si se trata de sacar una cantidad negativa
	 * @throws ExcepcionImporteNoValido
	 */
	@Test (expected = ExcepcionImporteNoValido.class)
	public void testRetirarExcepcion3() throws ExcepcionImporteNoValido  {
		try{
			Cliente clientePrueba = new Cliente("Juan", "3098765A");
			double cantidadInicial = 100.0;
			Cuenta cuentaCorriente = new CuentaDeposito(clientePrueba,cantidadInicial);
			double cantidadRetirar = -1000.0;
			((CuentaDeposito)cuentaCorriente).marcarVencido();
			cuentaCorriente.retirar(cantidadRetirar);
		} catch (ExcepcionClienteNoValido e) {
			fail("La excepcion ExcepcionClienteNoValido no debe producirse ");
		}  catch (ExcepcionOperacionNoPermitida e) {
			fail("La excepcion ExcepcionOperacionNoPermitida no debe producirse ");
		} catch (ExcepcionSaldoInsuficiente e) {
			fail("La excepcion ExcepcionSaldoInsuficiente no debe producirse ");
		} 
	}	
	
	/**
	 * Test que comprueba el correcto funcionamiento del método retirar
	 *   se permite cuando se marca la cuenta depósito como vencida
	 *   se comprueba que se decremente correctamente el saldo de la cuenta
	 */
	@Test 
	public void testRetirar() {
		try{
			Cliente clientePrueba = new Cliente("Juan", "3098765A");
			double cantidadInicial = 100.0;
			Cuenta cuentaCorriente = new CuentaDeposito(clientePrueba,cantidadInicial);
			double cantidadRetirar = 10.0;
			((CuentaDeposito)cuentaCorriente).marcarVencido();
			cuentaCorriente.retirar(cantidadRetirar);
			assertTrue("Metodo retirar cuenta deposito mal implementado", cuentaCorriente.getSaldo()==cantidadInicial-cantidadRetirar);
		} catch (ExcepcionClienteNoValido e) {
			fail("La excepcion ExcepcionClienteNoValido no debe producirse ");
		}  catch (ExcepcionOperacionNoPermitida e) {
			fail("La excepcion ExcepcionOperacionNoPermitida no debe producirse ");
		} catch (ExcepcionSaldoInsuficiente e) {
			fail("La excepcion ExcepcionSaldoInsuficiente no debe producirse ");
		} catch (ExcepcionImporteNoValido e) {
			fail("La excepcion ExcepcionImporteNoValido no debe producirse ");
		} 
	}
	
	/**
	 * Test que comprueba el correcto funcionamiento del método toString
	 */
	@Test
	public void testToString() {
		try{
			Cliente clientePrueba = new Cliente("Juan", "3098765A");
			double cantidadInicial = 100.0;
			CuentaDeposito cuentaCorriente = new CuentaDeposito(clientePrueba,cantidadInicial);
			cuentaCorriente.marcarVencido();
			assertTrue("Método toString mal implementado", cuentaCorriente.toString().contains("Juan"));
			assertTrue("Método toString mal implementado", cuentaCorriente.toString().contains("3098765A"));
			assertTrue("Método toString mal implementado", cuentaCorriente.toString().contains("100"));
			assertTrue("Método toString mal implementado", cuentaCorriente.toString().contains("true"));
			
		} catch (ExcepcionClienteNoValido e) {
			fail("La excepcion ExcepcionClienteNoValido no debe producirse ");
		} catch (ExcepcionImporteNoValido e) {
			fail("La excepcion ExcepcionImporteNoValido no debe producirse ");
		}
	}
}
