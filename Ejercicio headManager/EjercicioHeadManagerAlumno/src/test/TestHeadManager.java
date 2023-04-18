package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fecha.Fecha;
import fecha.excepciones.FechaIncorrecta;
import news.HeadManager;
import news.Headline;
import news.TArea;

public class TestHeadManager {
	private HeadManager headManager;
	private Headline []heads;
	/**
	 * Método que se encarega de incializar las instancia headManager
	 * sobre la que se van a pasar los test.
	 * Este método es llamado una vez por cada prueba.
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		crearHeadlines();
		this.headManager = new HeadManager (heads.length);
	}

	/**
	 * Esta prueba verifica que un gestor sin titulares indique 
	 * que tiene 0 titulares
	 */
	@Test
	public void testGetNTitulares() {
		assertTrue("Al principio debe haber 0 noticias",
				headManager.getNTitulares() == 0); 
	}
	
	/**
	 * Esta prueba añade una noticia y verifica que se retorna el valor adecuado
	 */
	@Test
	public void testAddHeadline() {
		assertTrue("Tras insertar una notica el número de titulares debe ser 1. "
				+ "Verifique el funcionamiento del método addHeadline",
				headManager.addHeadline(heads[0]) == 1);
	}

	/**
	 * Se prueba a llenar de noticias
	 */
	@Test
	public void testAddHeadline2() {
		for (int i =0; i < heads.length;i++){
			assertTrue("Tras insertar "+ (i+1) +" noticas el número de titulares debería ser "+ (i+1)+ " . "
					+ "Verifique el funcionamiento del método addHeadline",
					headManager.addHeadline(heads[i]) == i+1);
		}
		assertTrue("Tras insertar más noticias de las que caben el valor retornado debe ser -1. "
				+ "Verifique el funcionamiento del método addHeadline",
				headManager.addHeadline(heads[2]) == -1);
	}
	
	/**
	 * Comprueba como va variando el número de titualres al añadir noticias
	 */
	@Test
	public void testGetNTitulares2() {
		for (int i = 0; i < heads.length; i++){
			headManager.addHeadline(heads[i]);
			assertTrue("Tras insertar "+(i+1)+" noticas el número de titulares debería ser "+ (i+1) +" . "
					+ "Si la prueba testAddHeadline2 funcionó correctramente verifique getNTitulares",
					 headManager.getNTitulares()== (i+1));	
		}
		headManager.addHeadline(heads[2]);
		assertTrue("El número de titulares no debe cambiar al intentar insertar un titular, estando lleno"
				+ "verifique addHeadline",
				headManager.getNTitulares() == 3);
	}
	
	/**
	 * Prueba a pedir noticas estando vacío
	 */
	@Test
	public void testGetHeadline(){
		assertTrue ("Cuando la posición de la notica que se pide "
				+ "está fuera de rango se debe retonar null",
				headManager.getHeadline(0) == null);
		assertTrue ("Cuando la posición de la notica que se pide "
				+ "está fuera de rango se debe retonar null",
				headManager.getHeadline(1) == null);	
	}
	
	/**
	 * Se prueba a pedir noticas cuando hay una notica
	 */
	@Test
	public void testGetHeadline2(){
		Headline headline;
		headManager.addHeadline(heads[0]);
		headline = headManager.getHeadline(1);
		assertTrue ("No se ha recuperado la notica correctamente cuando sólo "
				+ "hay una notica", headline != null && headline.equals(heads[0]));
	}
	
	/**
	 * Se prueba a pedir noticas cuando hay mas de una notica
	 */
	@Test
	public void testGetHeadline3(){
		Headline headline;
		for (int i =0; i < heads.length; i++){
		 headManager.addHeadline(heads[i]);
		}
		for (int i = 0; i < heads.length; i++ ){
			headline = headManager.getHeadline(i+1);
			assertTrue ("No se ha recuperado la notica "+(i+1)+
					" correctamente cuando está lleno" , 
					 headline != null && headline.equals(heads[i]));	
		}
	}
	
	/**
	 * Se intenta borrar por posicion cuando no hay nada
	 */
	@Test
	public void testdeleteHeadline(){
		assertTrue ("Cuando se intenta borrar una posición no válida se debe retornar -1",
				headManager.deleteHeadline(1) == -1);
	}
	
	/**
	 * Se intenta borrar por posición y se intenta borrar una posición no válida
	 */
	@Test
	public void testdeleteHeadline2(){
		headManager.addHeadline(heads[0]);
		headManager.addHeadline(heads[1]);
		assertTrue ("Cuando se intenta borrar una posición no válida se de retornar -1",
				headManager.deleteHeadline(3) == -1);
	}
	
	/**
	 * Se intenta borrar por posición el segundo elemento de tres
	 */
	@Test
	public void testdeleteHeadline3(){
		headManager.addHeadline(heads[0]);
		headManager.addHeadline(heads[1]);
		headManager.addHeadline(heads[2]);
		assertTrue ("Cuando se borrar un elemento se debe retonar el número de elementos que quedan",
				headManager.deleteHeadline(2) == 2 && 
				headManager.getNTitulares() == 2);
		Headline head1 = headManager.getHeadline(1);
		Headline head2 = headManager.getHeadline(2);
		Headline headNull = headManager.getHeadline(3);
		assertTrue ("Tras borrar el segundo artículo algo no ha quedado bien!!!", headNull == null &&
					(head1 != null && head1.equals(heads[0]))
					&& 
					(head2 != null && head2.equals(heads[2])));
	}
	
	/**
	 * Se intenta borrar por título un headline que no existe 
	 */
	@Test
	public void testdeleteHeadline4(){
		assertTrue ("Al borrar por título sin que haya noticas se "
				+ "debe retornar -1", 
				headManager.deleteHeadline("No existe") == -1);
		headManager.addHeadline(heads[0]);
		headManager.addHeadline(heads[1]);
		headManager.addHeadline(heads[2]);
		assertTrue ("Al borrar por título una noticia que no existe "
				+ "debe retornar -1", 
				headManager.deleteHeadline("No existe") == -1);
		for (int i = 0; i < heads.length; i++ ){
			Headline headline = headManager.getHeadline(i+1);
			assertTrue ("No se ha recuperado la notica "+(i+1)+
					" correctamente cuando se intentó borrar una noticia inexistente" , 
					 headline != null && headline.equals(heads[i]));	
		}		
	}
	
	/**
	 * Borra la última noticia por título
	 */
	@Test
	public void testdeleteHeadline5(){
		headManager.addHeadline(heads[0]);
		headManager.addHeadline(heads[1]);
		headManager.addHeadline(heads[2]);
		assertTrue ("Al borrar por título una noticia que existe "
				+ "debe retornar el número de titulares que quedan", 
				headManager.deleteHeadline(new String(heads[2].getTitulo())) == 2 &&
				headManager.getNTitulares() == 2);
		for (int i = 0; i < heads.length-1; i++ ){
			Headline headline = headManager.getHeadline(i+1);
			assertTrue ("No se ha recuperado la notica "+(i+1)+
					" correctamente cuando se borrar por título la última noticia" , 
					 headline != null && headline.equals(heads[i]));	
		}
	}
	
	/**
	 * Borra la primera noticia por título
	 */
	@Test
	public void testdeleteHeadline6(){
		headManager.addHeadline(heads[0]);
		headManager.addHeadline(heads[1]);
		headManager.addHeadline(heads[2]);
		assertTrue ("Al borrar por título una noticia que existe "
				+ "debe retornar el número de titulares que quedan", 
				headManager.deleteHeadline(new String (heads[0].getTitulo())) == 2 &&
				headManager.getNTitulares() == 2);
		Headline headline1 = headManager.getHeadline(1);
		Headline headline2 = headManager.getHeadline(2);
		//Estamos trabajando con un gestor que no le interesa el orden es decir cuando se borrar
		//el primer elemento el último pasa a la posición del primero
		assertTrue ("No se ha recuperado la notica "+1+
				" correctamente cuando se borrar por título la primera noticia" , 
				 headline1 != null && headline1.equals(heads[2]));
		assertTrue ("No se ha recuperado la notica "+2+
				" correctamente cuando se borrar por título la primera noticia" , 
				 headline2 != null && headline2.equals(heads[1]));
	}
	
	/**
	 * Método auxiliar que crea las instancias de noticas con las que se va a trabajar en las pruebas
	 * 
	 */
	private void crearHeadlines(){
		heads = new Headline [3];
		try {
			heads[0] = new Headline("News 1", new Fecha("10/02/2018"), TArea.ECONOMY);
			heads[1] = new Headline("News 2", new Fecha("15/03/2018"), TArea.LOCAL);
			heads[2] = new Headline("News 3", new Fecha("25/03/2018"), TArea.LOCAL);
		} catch (FechaIncorrecta e) {
			e.printStackTrace();
		}
	}
}
