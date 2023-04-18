
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
		Cuenta cuenta =new Cuenta("Pepe", 1000);
		cuenta.sacarDinero(100);
		}catch(ExcepcionSaldoInicialInsuficiente e) {
			System.out.println("Error: El saldo inicial es insuficiente ");
		}catch(SaldoInsuficiente e) {
			System.out.println("Error: El saldo  es insuficiente ");
		}

	}

}
