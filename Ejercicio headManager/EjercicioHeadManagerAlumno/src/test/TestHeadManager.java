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
	 * M�todo que se encarega de incializar las instancia headManager
	 * sobre la que se van a pasar los test.
	 * Este m�todo es llamado una vez por cada prueba.
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
	 * Esta prueba a�ade una noticia y verifica que se retorna el valor adecuado
	 */
	@Test
	public void testAddHeadline() {
		assertTrue("Tras insertar una notica el n�mero de titulares debe ser 1. "
				+ "Verifique el funcionamiento del m�todo addHeadline",
				headManager.addHeadline(heads[0]) == 1);
	}

	/**
	 * Se prueba a llenar de noticias
	 */
	@Test
	public void testAddHeadline2() {
		for (int i =0; i < heads.length;i++){
			assertTrue("Tras insertar "+ (i+1) +" noticas el n�mero de titulares deber�a ser "+ (i+1)+ " . "
					+ "Verifique el funcionamiento del m�todo addHeadline",
					headManager.addHeadline(heads[i]) == i+1);
		}
		assertTrue("Tras insertar m�s noticias de las que caben el valor retornado debe ser -1. "
				+ "Verifique el funcionamiento del m�todo addHeadline",
				headManager.addHeadline(heads[2]) == -1);
	}
	
	/**
	 * Comprueba como va variando el n�mero de titualres al a�adir noticias
	 */
	@Test
	public void testGetNTitulares2() {
		for (int i = 0; i < heads.length; i++){
			headManager.addHeadline(heads[i]);
			assertTrue("Tras insertar "+(i+1)+" noticas el n�mero de titulares deber�a ser "+ (i+1) +" . "
					+ "Si la prueba testAddHeadline2 funcion� correctramente verifique getNTitulares",
					 headManager.getNTitulares()== (i+1));	
		}
		headManager.addHeadline(heads[2]);
		assertTrue("El n�mero de titulares no debe cambiar al intentar insertar un titular, estando lleno"
				+ "verifique addHeadline",
				headManager.getNTitulares() == 3);
	}
	
	/**
	 * Prueba a pedir noticas estando vac�o
	 */
	@Test
	public void testGetHeadline(){
		assertTrue ("Cuando la posici�n de la notica que se pide "
				+ "est� fuera de rango se debe retonar null",
				headManager.getHeadline(0) == null);
		assertTrue ("Cuando la posici�n de la notica que se pide "
				+ "est� fuera de rango se debe retonar null",
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
		assertTrue ("No se ha recuperado la notica correctamente cuando s�lo "
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
					" correctamente cuando est� lleno" , 
					 headline != null && headline.equals(heads[i]));	
		}
	}
	
	/**
	 * Se intenta borrar por posicion cuando no hay nada
	 */
	@Test
	public void testdeleteHeadline(){
		assertTrue ("Cuando se intenta borrar una posici�n no v�lida se debe retornar -1",
				headManager.deleteHeadline(1) == -1);
	}
	
	/**
	 * Se intenta borrar por posici�n y se intenta borrar una posici�n no v�lida
	 */
	@Test
	public void testdeleteHeadline2(){
		headManager.addHeadline(heads[0]);
		headManager.addHeadline(heads[1]);
		assertTrue ("Cuando se intenta borrar una posici�n no v�lida se de retornar -1",
				headManager.deleteHeadline(3) == -1);
	}
	
	/**
	 * Se intenta borrar por posici�n el segundo elemento de tres
	 */
	@Test
	public void testdeleteHeadline3(){
		headManager.addHeadline(heads[0]);
		headManager.addHeadline(heads[1]);
		headManager.addHeadline(heads[2]);
		assertTrue ("Cuando se borrar un elemento se debe retonar el n�mero de elementos que quedan",
				headManager.deleteHeadline(2) == 2 && 
				headManager.getNTitulares() == 2);
		Headline head1 = headManager.getHeadline(1);
		Headline head2 = headManager.getHeadline(2);
		Headline headNull = headManager.getHeadline(3);
		assertTrue ("Tras borrar el segundo art�culo algo no ha quedado bien!!!", headNull == null &&
					(head1 != null && head1.equals(heads[0]))
					&& 
					(head2 != null && head2.equals(heads[2])));
	}
	
	/**
	 * Se intenta borrar por t�tulo un headline que no existe 
	 */
	@Test
	public void testdeleteHeadline4(){
		assertTrue ("Al borrar por t�tulo sin que haya noticas se "
				+ "debe retornar -1", 
				headManager.deleteHeadline("No existe") == -1);
		headManager.addHeadline(heads[0]);
		headManager.addHeadline(heads[1]);
		headManager.addHeadline(heads[2]);
		assertTrue ("Al borrar por t�tulo una noticia que no existe "
				+ "debe retornar -1", 
				headManager.deleteHeadline("No existe") == -1);
		for (int i = 0; i < heads.length; i++ ){
			Headline headline = headManager.getHeadline(i+1);
			assertTrue ("No se ha recuperado la notica "+(i+1)+
					" correctamente cuando se intent� borrar una noticia inexistente" , 
					 headline != null && headline.equals(heads[i]));	
		}		
	}
	
	/**
	 * Borra la �ltima noticia por t�tulo
	 */
	@Test
	public void testdeleteHeadline5(){
		headManager.addHeadline(heads[0]);
		headManager.addHeadline(heads[1]);
		headManager.addHeadline(heads[2]);
		assertTrue ("Al borrar por t�tulo una noticia que existe "
				+ "debe retornar el n�mero de titulares que quedan", 
				headManager.deleteHeadline(new String(heads[2].getTitulo())) == 2 &&
				headManager.getNTitulares() == 2);
		for (int i = 0; i < heads.length-1; i++ ){
			Headline headline = headManager.getHeadline(i+1);
			assertTrue ("No se ha recuperado la notica "+(i+1)+
					" correctamente cuando se borrar por t�tulo la �ltima noticia" , 
					 headline != null && headline.equals(heads[i]));	
		}
	}
	
	/**
	 * Borra la primera noticia por t�tulo
	 */
	@Test
	public void testdeleteHeadline6(){
		headManager.addHeadline(heads[0]);
		headManager.addHeadline(heads[1]);
		headManager.addHeadline(heads[2]);
		assertTrue ("Al borrar por t�tulo una noticia que existe "
				+ "debe retornar el n�mero de titulares que quedan", 
				headManager.deleteHeadline(new String (heads[0].getTitulo())) == 2 &&
				headManager.getNTitulares() == 2);
		Headline headline1 = headManager.getHeadline(1);
		Headline headline2 = headManager.getHeadline(2);
		//Estamos trabajando con un gestor que no le interesa el orden es decir cuando se borrar
		//el primer elemento el �ltimo pasa a la posici�n del primero
		assertTrue ("No se ha recuperado la notica "+1+
				" correctamente cuando se borrar por t�tulo la primera noticia" , 
				 headline1 != null && headline1.equals(heads[2]));
		assertTrue ("No se ha recuperado la notica "+2+
				" correctamente cuando se borrar por t�tulo la primera noticia" , 
				 headline2 != null && headline2.equals(heads[1]));
	}
	
	/**
	 * M�todo auxiliar que crea las instancias de noticas con las que se va a trabajar en las pruebas
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
