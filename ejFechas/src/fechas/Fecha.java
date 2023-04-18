package fechas;

/** 
 * Representa una fecha como dia/mes/anio.
 */
public class Fecha {
  private int dia;
  private int mes;
  private int anio;
  
  public Fecha (int dia, int mes, int anio) {
    this.dia = dia;
    this.mes = mes;
    this.anio = anio;
  }
  
  public Fecha (String date) {
    String [] partes = date.split("/");
    this.dia = Integer.parseInt(partes[0]);
    this.mes = Integer.parseInt(partes[1]);
    this.anio = Integer.parseInt(partes[2]);
  }
  
  public int getDia () {
    return dia;
  }
  public int getMes () {
    return mes;
  }
  public int getAnio () {
    return anio;
  }
  
  public String toString() {
    return dia + "/" + mes + "/" + anio;
  }
  
  /*public boolean esIgual(Fecha f) {
    return 
      (dia == f.dia) && 
      (mes == f.mes) && 
      (anio == f.anio);
  }*/
  
  public boolean equals(Object o) {
	  if (o instanceof Fecha){
		  //downcasting
	  Fecha f = (Fecha) o;
	  return 
		      (dia == f.dia) && 
		      (mes == f.mes) && 
		      (anio == f.anio);
	  }else
		  return false;
  }
  
  /**
   * PRE: esFechaCorrecta() /\ f.esFechaCorrecta()
   * POST: Determina si esta fecha es menor o igual que f
   *       en sentido cronologico.
   */ 
  public boolean esMenorOIgual (Fecha f) {
    return 
      (anio < f.anio) ||
      (anio == f.anio && mes < f.mes) ||
      (anio == f.anio && mes == f.mes && dia <= f.dia);
  }

}
