import stacks.BoundedStackInt;
import stacks.excepciones.EmptyStackExp;
import stacks.excepciones.FullStackExp;

public class TestPilas {

	public static void main(String[] args) {
		BoundedStackInt pilaEnteros = new BoundedStackInt(4);
		
		try {
			for(int i=0; i<4; i++)
				pilaEnteros.push(i);
			
			for(int i=0; i<4; i++)
				System.out.println(pilaEnteros.pop());			
			
		} catch (FullStackExp e) {
			e.printStackTrace();
		} catch (EmptyStackExp e) {
			e.printStackTrace();
		}


	}

}
