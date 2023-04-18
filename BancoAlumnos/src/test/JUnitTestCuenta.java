package test;

import static org.junit.Assert.assertTrue;

//import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.HashSet;

import org.junit.Test;

//import anotacion.Programacion2;
//import banco.Banco;
import banco.cuentas.Cuenta;

public class JUnitTestCuenta {
	
	
	
	/**
	 * Test que comprueba la correcta implementaci�n de la clase CuentaAhorro
	 *    comprueba que se haya implementado el m�todo abstracto y ninguno de los que no son 
	 *    necesarios sobre-escribir 
	 */
	@Test
    public final void testComprobarMetodosImplementadosCuenta()
    {
        Class<Cuenta> clase= Cuenta.class;
        Method[] metodos= clase.getDeclaredMethods();
        HashSet<String> nombreMetodos = new HashSet<String>();	        
        for (int i=0;i<metodos.length;i++)
        	nombreMetodos.add(metodos[i].getName());	        
        assertTrue("La Cuenta deber�a tener el m�todo toString",nombreMetodos.contains("toString"));
        assertTrue("La Cuenta deber�a tener el m�todo getTitular",nombreMetodos.contains("getTitular"));
        assertTrue("La Cuenta deber�a tener el m�todo getNumero",nombreMetodos.contains("getNumero"));
        assertTrue("La Cuenta deber�a tener el m�todo getSaldo",nombreMetodos.contains("getSaldo"));
        assertTrue("La Cuenta deber�a tener el m�todo getFechaApertura",nombreMetodos.contains("getFechaApertura"));
        assertTrue("La Cuenta deber�a tener el m�todo ingresar",nombreMetodos.contains("ingresar"));
        assertTrue("La Cuenta deber�a tener el m�todo retirar",nombreMetodos.contains("retirar"));
        assertTrue("La Cuenta deber�a tener el m�todo disponibilidad",nombreMetodos.contains("disponibilidad"));        
    }
}
