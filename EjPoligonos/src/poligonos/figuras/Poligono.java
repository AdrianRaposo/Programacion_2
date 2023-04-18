package poligonos.figuras;

import list.ArrayList;
import list.IList;

public abstract class Poligono {
    // vertices recorridos en el sentido de las agujas del reloj
    private IList<Punto> vertices;
    
    // asigna al atributo una lista con copias de los vértices
    // del parametro de entrada
    public Poligono(IList<Punto> vertices){
        //TO-DO
    	this.vertices= new ArrayList<Punto>();
    	for(int i=0; i<vertices.size();i++) {
    		this.vertices.add(i, new Punto(vertices.get(i)));
    	}
    	
    }
    
    public double getPerimetro(){
        //TO-DO
    	double total=0;
    	for(int i =0; i<vertices.size();i++) {
    		total+=getLado(i);
    	}
    	return total;
    }
    
    public double getLado(int i){
        return vertices.get(i).calculaDistancia
        		(vertices.get((i+1) % vertices.size()));
    }
    
    public String toString(){
        String salida = "-";
        for(int i=0; i<vertices.size(); i++)
            salida += vertices.get(i).toString() + "-";
        return salida;
    }
    
    abstract public String getTipo();
    
}





