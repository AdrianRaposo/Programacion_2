package banco.cuentas;

import java.util.Date;

import banco.Cliente;
import banco.excepciones.*;


public abstract class Cuenta {
    private static int siguienteNumero = 0;
    private int numero;
    private Cliente titular;
    private double saldo;
    private Date fechaApertura;

    /**
     * Constructor de cuenta
     * 
     * PRE: titular != null y ingresoInicial > 0
     * 
     * @exception ExcepcionClienteNoValido
     *            si el titular es null
     * @exception ExcepcionImporteNoValido
     *            si el ingreso inicial es cero o negativo
     */
    public Cuenta(Cliente titular, double ingresoInicial)
            throws ExcepcionClienteNoValido, ExcepcionImporteNoValido {
        if (titular == null)
        	throw new ExcepcionClienteNoValido();
        if(ingresoInicial<=0)
        	throw new ExcepcionImporteNoValido();
        this.titular =titular;
        this.saldo = ingresoInicial;
        this.numero = ++siguienteNumero;
        this.fechaApertura = new Date();
    }

    // Getters
    public int getNumero() {
        return numero;
    }
    public Cliente getTitular() {
        return titular;
    }
    public double getSaldo() {
        return saldo;
    }   
    public Date getFechaApertura() {
        return this.fechaApertura;
    }

    // Datos de la cuenta
    public String toString() {
        return numero + ": " + disponibilidad() + "€ (" + titular.getNif() + ") "
                + titular.getNombre() + " " + fechaApertura;
    }

    /**
     * PRE: el ingreso es una operación permitida para este tipo de cuenta
     * y el importe > 0
     * 
     * Ingresar dinero en la cuenta
     * 
     * @exception ExcepcionOperacionNoPermitida
     *            si la operación no es aceptable, según tipo de cuenta
     * @exception ExcepcionImporteNoValido
     *            si el importe es cero o negativo
     */
    public void ingresar(double importe) throws ExcepcionOperacionNoPermitida,
    ExcepcionImporteNoValido {
    	if (!opIngresarPermitida())
    		throw new ExcepcionOperacionNoPermitida("En la cuenta " + this.toString() + 
    				" no se puede ingresar dinero");
        if (importe <= 0.0) {
            throw new ExcepcionImporteNoValido();
        }
        saldo = saldo + importe;
    }

    /**
     * PRE: la retirada es una operación permitida para este tipo de cuenta
     * y el importe > 0 y la disponibilidad >= importe
     * 
     * Retirar dinero, según tipo de cuenta
     * 
     * @exception ExcepcionOperacionNoPermitida
     *            si la operación no es aceptable, según tipo de cuenta
     * @exception ExcepcionImporteNoValido
     *            si el importe es cero o negativo
     * @exception ExcepcionSaldoInsuficiente
     *            si no hay saldo suficiente, según tipo de cuenta
     */
    public void retirar(double importe) throws ExcepcionOperacionNoPermitida,
    ExcepcionImporteNoValido, ExcepcionSaldoInsuficiente {
        	if (!opRetiradaPermitida())
        		throw new ExcepcionOperacionNoPermitida("En la cuenta " + this.toString() + 
        				" no se puede retirrar dinero");
            if (importe <= 0.0) {
                throw new ExcepcionImporteNoValido ();
            }
            if(disponibilidad()<importe)
            	throw new ExcepcionSaldoInsuficiente ();
            saldo-=importe;
    }

    public abstract boolean opRetiradaPermitida();
    
    public abstract boolean opIngresarPermitida();

	/**
     * Máximo importe que puede ser retirado de la cuenta, o cero si no se
     * pueden hacer retiradas
     */
    public abstract double disponibilidad();
    
    public void setSaldo(double saldo){
        this.saldo = saldo;
    }

}
