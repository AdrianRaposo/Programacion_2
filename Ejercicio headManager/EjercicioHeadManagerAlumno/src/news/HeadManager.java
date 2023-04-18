/**
 * 
 */
package news;

import anotacion.*; 
  @Programacion2 (
	nombreAutor1 = "Adri�n",                 // (del alumno 1)
	apellidoAutor1 = "Raposo Pozuelo",  // (del alumno 1)
	emailUPMAutor1 = "adrian.raposo@alumnos.upm.es"   // (del alumno 1)	
  )

/**
 * @author agonzalez
 *
 */
public class HeadManager {
 //TODO: definir los atributos necesarios
	  
	  private Headline [] headManager;
	  private int NTitulares;
	  
 
 public HeadManager (int max){
	//TODO
	 headManager = new Headline[max];
	 NTitulares=0; 
 }
 
 public int getNTitulares (){
	 return this.NTitulares; //TODO
 }
 
 /**
  * Este método inserta una referencia al titular al final de los titulares existentes
  * @param headline
  * @return
  */
 public	int addHeadline (Headline headline){//addHeadLine
	if (NTitulares<headManager.length) {
		headManager[NTitulares]= headline;
		NTitulares++;
		return NTitulares;
	}
	 return -1; //TODO
 }//addHeadLine
 
 /**
  * Retorna el headline de la posición dada. Si la posición es incorrecta reotornará null
  * @param pos posición de la que se quiere el artículo debe estar entre 1 y getNTitulares()
  * @return si la posición es correcta se retorna referencia al titular en otro caso se retorna null
  */
 public Headline getHeadline (int pos){
	 if (0<pos &&  pos <= headManager.length) {
		 return headManager[pos-1];
	 }
	 return null; //TODO
 }
 
 /**
  * Este método elimina un titular. Dado que el orden en el que estén es irrelevante
  * se movera la notica de la última posición a la posición dada para sacar el contenido de esta
  * @param pos posición de la que se quiere borrar el artículo debe estar entre 1 y getNTitulares()
  * @return
  */
 public int deleteHeadline (int pos){
	 if(0<pos && pos<=NTitulares) {
		 headManager[pos-1]=null;
		 /*for(int i = pos; i<NTitulares ;i++) {
			 headManager[pos-1]= headManager[pos];
		 }*/
		 NTitulares--;
		 if(pos-1!=NTitulares) {
			 headManager[pos-1]= headManager[NTitulares];
			 headManager[NTitulares]=null;
		 }
		 return NTitulares;
	 }
	 return -1; //TODO
 }
 
 /**
  * Este método elimina el primer titular con el título dado. Dado que el orden en el que estén es irrelevante
  * se movera la notica de la última posición a la posición en la que se encuetre el título para sacar el contenido de esta
  * @param pos posición de la que se quiere borrar el artículo debe estar entre 1 y getNTitulares()
  * @return
  */
 public int deleteHeadline (String title){
	 //TODO
	 for(int i=0; i< this.NTitulares; i++) {
		 
		 if(headManager[i].getTitulo().equals(title)) {
			 headManager[i]=headManager[NTitulares-1];
			 headManager[NTitulares-1]=null;
			 NTitulares--;
			 return NTitulares;
		 }
	 }

	 return -1; 
	 
 } 
}
