package test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

//import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Date;

import org.junit.Test;

//import anotacion.Programacion2;
import banco.Cliente;
import banco.cuentas.Cuenta;
import banco.cuentas.CuentaAhorro;
import banco.cuentas.CuentaDeposito;
import banco.excepciones.ExcepcionClienteNoValido;
import banco.excepciones.ExcepcionImporteNoValido;
import banco.excepciones.ExcepcionOperacionNoPermitida;
import banco.excepciones.ExcepcionSaldoInsuficiente;

public class JUnitTestCuentaAhorro {
	

	
	/**
	 * Test que comprueba la correcta implementación de la clase CuentaAhorro
	 *    comprueba que se haya implementado el método abstracto y ninguno de los que no son 
	 *    necesarios sobre-escribir 
	 */
	@Test
    public final void testComprobarMetodosImplementados()
    {
        Class<CuentaAhorro> clase= CuentaAhorro.class;
        Method[] metodos= clase.getDeclaredMethods();
        HashSet<String> nombreMetodos = new HashSet<String>();	        
        for (int i=0;i<metodos.length;i++)
        	nombreMetodos.add(metodos[i].getName());	        
        assertTrue("La CuentaAhorro debería tener el método disponibilidad",nombreMetodos.contains("disponibilidad"));
        
        assertFalse("La CuentaAhorro no debería tener el método ingresar, debe estar implementado en la clase abstracta",nombreMetodos.contains("ingresar"));
        assertFalse("La CuentaAhorro no debería tener el método retirar, debe estar implementado en la clase abstracta",nombreMetodos.contains("retirar"));	        
        assertFalse("La CuentaAhorro no debería tener el método CuentaAhorro, debe estar implementado en la clase abstracta",nombreMetodos.contains("marcarVencido"));
    }
	
	/**
	 * Test que comprueba el correcto funcionamiento del constructor
	 * y que la clase CuentaDeposito sea hija de la clase Cuenta
	 * @throws ExcepcionClienteNoValido
	 */
	@Test
	public void testHerenciaCuentas() throws ExcepcionClienteNoValido  {
		try {
			@SuppressWarnings("unused")
			Cuenta cuentaCorriente = new CuentaAhorro(new Cliente("Juan", "3098765A"),100.0);
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
			Cuenta cuentaCorriente = new CuentaAhorro(null,1000.0);
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
			Cuenta cuentaCorriente = new CuentaAhorro(new Cliente("Juan", "3098765A"),0.0);
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
			Cuenta cuentaCorriente = new CuentaAhorro(new Cliente("Juan", "3098765A"),-100.0);
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
			Cuenta cuentaCorriente = new CuentaAhorro(clientePrueba,cantidadInicial);
			int numeroCuentaCorriente = cuentaCorriente.getNumero();
			assertTrue("Atributo saldo mal inicializado", cuentaCorriente.getSaldo()==cantidadInicial);
			assertTrue("Atributo cliente mal inicializado", cuentaCorriente.getTitular()==clientePrueba);
			assertTrue("Atributo fecha apertura mal inicializado", cuentaCorriente.getFechaApertura() instanceof Date);
			Cuenta cuentaCorriente2 = new CuentaAhorro(clientePrueba,cantidadInicial);
			assertTrue("Atributo numero de cuenta no se actualiza correctamente", numeroCuentaCorriente - cuentaCorriente2.getNumero()==-1);
		} catch (ExcepcionClienteNoValido e) {
			fail("La excepcion ExcepcionClienteNoValido no debe producirse ");
		} catch (ExcepcionImporteNoValido e) {
			fail("La excepcion ExcepcionImporteNoValido no debe producirse ");
		}
	}
	
	/**
	 * Test que comprueba el correcto funcionamiento del método ingresar
	 *    se intenta ingresar una cantidad negativa
	 * @throws ExcepcionImporteNoValido
	 */
	@Test (expected = ExcepcionImporteNoValido.class)
	public void testIngresarExcepcion() throws ExcepcionImporteNoValido  {
		try{
			Cliente clientePrueba = new Cliente("Juan", "3098765A");
			double cantidadInicial = 100.0;
			double cantidadIngresar = -1000.0;
			Cuenta cuentaCorriente = new CuentaAhorro(clientePrueba,cantidadInicial);
			cuentaCorriente.ingresar(cantidadIngresar);
		}catch (ExcepcionClienteNoValido e) {
			fail("La excepcion ExcepcionClienteNoValido no debe producirse ");
		} catch (ExcepcionOperacionNoPermitida e) {
			fail("La excepcion ExcepcionOperacionNoPermitida no debe producirse ");
		} 
	}	
	
	/**
	 * 	 Test que comprueba el correcto funcionamiento del método ingresar
	 *   se comprueba que se incremente correctamente el saldo de la cuenta
	 */
	@Test
	public void testIngresar()  {
		try{
			Cliente clientePrueba = new Cliente("Juan", "3098765A");
			double cantidadInicial = 100.0;
			double cantidadIngresar = 100.0;
			Cuenta cuentaCorriente = new CuentaAhorro(clientePrueba,cantidadInicial);
			cuentaCorriente.ingresar(cantidadIngresar);
			assertTrue("Metodo ingresar mal implementado", cuentaCorriente.getSaldo()==cantidadInicial+cantidadIngresar);
		}catch (ExcepcionClienteNoValido e) {
			fail("La excepcion ExcepcionClienteNoValido no debe producirse ");
		} catch (ExcepcionOperacionNoPermitida e) {
			fail("La excepcion ExcepcionOperacionNoPermitida no debe producirse ");
		} catch (ExcepcionImporteNoValido e) {
			fail("La excepcion ExcepcionImporteNoValido no debe producirse ");
		} 
		
	}	
	
	/**
	 * Test que comprueba el correcto funcionamiento del método retirar
	 *    se intenta retirar una cantidad negativa
	 * @throws ExcepcionImporteNoValido
	 */
	@Test (expected = ExcepcionImporteNoValido.class)
	public void testRetirarExcepcion() throws ExcepcionImporteNoValido  {
		try{
			Cliente clientePrueba = new Cliente("Juan", "3098765A");
			double cantidadInicial = 100.0;
			double cantidadRetirar = -1000.0;
			Cuenta cuentaCorriente = new CuentaAhorro(clientePrueba,cantidadInicial);
			cuentaCorriente.retirar(cantidadRetirar);
		}catch (ExcepcionOperacionNoPermitida e) {
			fail("La excepcion ExcepcionOperacionNoPermitida no debe producirse ");
		} catch (ExcepcionSaldoInsuficiente e) {
			fail("La excepcion ExcepcionSaldoInsuficiente no debe producirse ");
		} catch (ExcepcionClienteNoValido e) {
			fail("La excepcion ExcepcionClienteNoValido no debe producirse ");
		}
	}	
	
	/**
	 * Test que comprueba el correcto funcionamiento del método retirar
	 *    se intenta retirar más dinero del que hay en la cuenta
	 */
	@Test (expected = ExcepcionSaldoInsuficiente.class)
	public void testRetirarExcepcion2() throws ExcepcionSaldoInsuficiente  {
		try{
			Cliente clientePrueba = new Cliente("Juan", "3098765A");
			double cantidadInicial = 100.0;
			double cantidadRetirar = 1000.0;
			Cuenta cuentaCorriente = new CuentaAhorro(clientePrueba,cantidadInicial);
			cuentaCorriente.retirar(cantidadRetirar);
		}catch (ExcepcionOperacionNoPermitida e) {
			fail("La excepcion ExcepcionOperacionNoPermitida no debe producirse ");
		} catch (ExcepcionClienteNoValido e) {
			fail("La excepcion ExcepcionClienteNoValido no debe producirse ");
		} catch (ExcepcionImporteNoValido e) {
			fail("La excepcion ExcepcionImporteNoValido no debe producirse ");
		}
	}	
	
	/**
	 * Test que comprueba el correcto funcionamiento del método retirar
	 *   se comprueba que se decremente correctamente el saldo de la cuenta 
	 */
	@Test
	public void testRetirar() {
		try{
			Cliente clientePrueba = new Cliente("Juan", "3098765A");
			double cantidadInicial = 100.0;
			double cantidadRetirar = 10.0;
			Cuenta cuentaCorriente = new CuentaAhorro(clientePrueba,cantidadInicial);
			cuentaCorriente.retirar(cantidadRetirar);
			assertTrue("Metodo retirar mal implementado", cuentaCorriente.getSaldo()==cantidadInicial-cantidadRetirar);
			cuentaCorriente.retirar(cantidadInicial-cantidadRetirar);
			assertTrue("Metodo retirar mal implementado", cuentaCorriente.getSaldo()==0);
		}catch (ExcepcionOperacionNoPermitida e) {
			fail("La excepcion ExcepcionOperacionNoPermitida no debe producirse ");
		} catch (ExcepcionClienteNoValido e) {
			fail("La excepcion ExcepcionClienteNoValido no debe producirse ");
		} catch (ExcepcionImporteNoValido e) {
			fail("La excepcion ExcepcionImporteNoValido no debe producirse ");
		} catch (ExcepcionSaldoInsuficiente e) {
			fail("La excepcion ExcepcionSaldoInsuficiente no debe producirse ");
		}
		
	}	
	
	/**
	 * Test que comprueba el correcto funcionamiento del método disponibilidad
	 */
	@Test
	public void testDisponibilidad() {
		try{
			Cliente clientePrueba = new Cliente("Juan", "3098765A");
			double cantidadInicial = 100.0;
			Cuenta cuentaCorriente = new CuentaAhorro(clientePrueba,cantidadInicial);
			
			assertTrue("Método disponibilidad mal implementado", cuentaCorriente.getSaldo()==cuentaCorriente.disponibilidad());
		} catch (ExcepcionClienteNoValido e) {
			fail("La excepcion ExcepcionClienteNoValido no debe producirse ");
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
			Cuenta cuentaCorriente = new CuentaAhorro(clientePrueba,cantidadInicial);
			
			assertTrue("Método toString mal implementado", cuentaCorriente.toString().contains("Juan"));
			assertTrue("Método toString mal implementado", cuentaCorriente.toString().contains("3098765A"));
			assertTrue("Método toString mal implementado", cuentaCorriente.toString().contains("100"));
			
		} catch (ExcepcionClienteNoValido e) {
			fail("La excepcion ExcepcionClienteNoValido no debe producirse ");
		} catch (ExcepcionImporteNoValido e) {
			fail("La excepcion ExcepcionImporteNoValido no debe producirse ");
		}
	}
	

}
