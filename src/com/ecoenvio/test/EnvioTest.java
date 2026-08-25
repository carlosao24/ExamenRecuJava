package com.ecoenvio.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.ecoenvio.modelo.Cliente;
import com.ecoenvio.modelo.Dimensiones;
import com.ecoenvio.modelo.Envio;

public class EnvioTest {

    @Test
    public void testCalculoEstandar() {
        Dimensiones dim = new Dimensiones(10.0, 10.0, 10.0);
        Cliente cli = new Cliente("Juan", "REGULAR");
        Envio envio = new Envio("E001", 3.0, 10.0, "EFECTIVO", dim, cli);
        double esperado = 5.00 + (0.50 * 10.0);
        assertEquals(esperado, envio.calcularCostoTotal(), 0.001);
    }

    @Test
    public void testVolumenExcedido() {
        Dimensiones dim = new Dimensiones(100.0, 100.0, 10.0);
        Cliente cli = new Cliente("Ana", "REGULAR");
        Envio envio = new Envio("E002", 3.0, 10.0, "EFECTIVO", dim, cli);
        double esperado = (5.00 + (0.50 * 10.0)) + 15.00;
        assertEquals(esperado, envio.calcularCostoTotal(), 0.001);
    }

    @Test
    public void testMembresiasEspeciales() {
        Dimensiones dim = new Dimensiones(10.0, 10.0, 10.0);
        
        Cliente cliPremium = new Cliente("Pedro", "PREMIUM");
        Envio envioPremium = new Envio("E003", 3.0, 100.0, "EFECTIVO", dim, cliPremium);
        double basePremium = 5.00 + (0.50 * 100.0);
        double esperadoPremium = basePremium * 0.90;
        assertEquals(esperadoPremium, envioPremium.calcularCostoTotal(), 0.001);

        Cliente cliVip = new Cliente("Maria", "VIP");
        Envio envioVip = new Envio("E004", 3.0, 100.0, "EFECTIVO", dim, cliVip);
        double baseVip = 5.00 + (0.50 * 100.0);
        double esperadoVip = baseVip * 0.80;
        assertEquals(esperadoVip, envioVip.calcularCostoTotal(), 0.001);
    }

    @Test
    public void testMetodosDePago() {
        Dimensiones dim = new Dimensiones(10.0, 10.0, 10.0);
        Cliente cli = new Cliente("Luis", "REGULAR");
        double base = 5.00 + (0.50 * 100.0);

        Envio envioTransferencia = new Envio("E005", 3.0, 100.0, "TRANSFERENCIA", dim, cli);
        double esperadoTransferencia = base * 0.95;
        assertEquals(esperadoTransferencia, envioTransferencia.calcularCostoTotal(), 0.001);

        Envio envioTarjeta = new Envio("E006", 3.0, 100.0, "TARJETA", dim, cli);
        double esperadoTarjeta = base * 1.03;
        assertEquals(esperadoTarjeta, envioTarjeta.calcularCostoTotal(), 0.001);
    }
    
    @Test
    public void testValidacionesExcepciones() {
        Dimensiones dim = new Dimensiones(10.0, 10.0, 10.0);
        Cliente cli = new Cliente("Luis", "REGULAR");
        
        Envio envioPesoCero = new Envio("E007", 0.0, 10.0, "EFECTIVO", dim, cli);
        assertEquals(0.0, envioPesoCero.calcularCostoTotal(), 0.001);
        
        Envio envioPagoInvalido = new Envio("E008", 3.0, 10.0, "CHEQUE", dim, cli);
        assertThrows(IllegalArgumentException.class, () -> envioPagoInvalido.calcularCostoTotal());
    }
}