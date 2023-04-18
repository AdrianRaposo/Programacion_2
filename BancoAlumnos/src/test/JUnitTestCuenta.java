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
	 * Test que comprueba la correcta implementación de la clase CuentaAhorro
	 *    comprueba que se haya implementado el método abstracto y ninguno de los que no son 
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
        assertTrue("La Cuenta debería tener el método toString",nombreMetodos.contains("toString"));
        assertTrue("La Cuenta debería tener el método getTitular",nombreMetodos.contains("getTitular"));
        assertTrue("La Cuenta debería tener el método getNumero",nombreMetodos.contains("getNumero"));
        assertTrue("La Cuenta debería tener el método getSaldo",nombreMetodos.contains("getSaldo"));
        assertTrue("La Cuenta debería tener el método getFechaApertura",nombreMetodos.contains("getFechaApertura"));
        assertTrue("La Cuenta debería tener el método ingresar",nombreMetodos.contains("ingresar"));
        assertTrue("La Cuenta debería tener el método retirar",nombreMetodos.contains("retirar"));
        assertTrue("La Cuenta debería tener el método disponibilidad",nombreMetodos.contains("disponibilidad"));        
    }
}
