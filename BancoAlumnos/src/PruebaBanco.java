import banco.*;

public class PruebaBanco {

    public static void main(String[] args) {

        Banco banco = new Banco();
        int cuenta1 = 0;
        int cuenta2 = 0;
        int cuenta3 = 0;
        try {
            cuenta1 = banco.abrirCuenta(TipoCuenta.AHORRO, new Cliente("Luis",
                    "37A"), 100);
            System.out.println(banco.buscarCuenta(cuenta1));
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        try {
            cuenta2 = banco.abrirCuenta(TipoCuenta.DEPOSITO, new Cliente(
                    "Juan", "55B"), 999);
            System.out.println(banco.buscarCuenta(cuenta2));
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        try {
            cuenta3 = banco.abrirCuenta(null, new Cliente(
                    "Juan", "77X"), 0);
            System.out.println(banco.buscarCuenta(cuenta3));
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        try {
            banco.retirar(cuenta1, 33);
            System.out.println(banco.buscarCuenta(cuenta1));
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        try {
            banco.retirar(cuenta1, 222);
            System.out.println(banco.buscarCuenta(cuenta1));
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        try {
            banco.retirar(cuenta2, 55);
            System.out.println(banco.buscarCuenta(cuenta2));
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        try {
            banco.marcarVencido(cuenta2);
            banco.retirar(cuenta2, 55);
            System.out.println(banco.buscarCuenta(cuenta2));
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        try {
            banco.marcarVencido(cuenta1);
            System.out.println(banco.buscarCuenta(cuenta1));
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        try {
            banco.retirar(999, 99);
            System.out.println(banco.buscarCuenta(999));
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
}
