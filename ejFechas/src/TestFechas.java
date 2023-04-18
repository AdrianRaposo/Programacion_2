import fechas.Fecha;

public class TestFechas {

	public static void main(String[] args) {
		Fecha ob1, ob2;
		ob1 = new Fecha(12, 4, 96);
		ob2 = new Fecha("12/4/96");
		System.out.println("La primera fecha es " + ob1); 
		System.out.println("La segunda fecha es " + ob2); 
		System.out.println(ob1 == ob2); // false
		System.out.println(ob1.equals(ob2)); // true
	}

}

