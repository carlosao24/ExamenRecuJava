package com.ecoenvio.modelo;

import java.util.Arrays;
import java.util.List;

public class Envio {
    private String idEnvio;
    private double pesoKg;
    private double distanciaKm;
    private String metodoPago;
    private Dimensiones dimensiones;
    private Cliente cliente;

    public Envio(String idEnvio, double pesoKg, double distanciaKm, String metodoPago, Dimensiones dimensiones, Cliente cliente) {
        this.idEnvio = idEnvio;
        this.pesoKg = pesoKg;
        this.distanciaKm = distanciaKm;
        this.metodoPago = metodoPago;
        this.dimensiones = dimensiones;
        this.cliente = cliente;
    }

    public String getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(String idEnvio) {
        this.idEnvio = idEnvio;
    }

    public double getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(double pesoKg) {
        this.pesoKg = pesoKg;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public void setDistanciaKm(double distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public Dimensiones getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(Dimensiones dimensiones) {
        this.dimensiones = dimensiones;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double calcularCostoTotal() {
        List<String> metodosValidos = Arrays.asList("EFECTIVO", "TARJETA", "TRANSFERENCIA");
        List<String> membresiasValidas = Arrays.asList("REGULAR", "PREMIUM", "VIP");

        if (pesoKg <= 0 || distanciaKm <= 0) {
            return 0.0;
        }
        if (dimensiones.getAlto() <= 0 || dimensiones.getAncho() <= 0 || dimensiones.getLargo() <= 0) {
            return 0.0;
        }
        if (!metodosValidos.contains(metodoPago)) {
            throw new IllegalArgumentException();
        }
        if (!membresiasValidas.contains(cliente.getTipoMembresia())) {
            throw new IllegalArgumentException();
        }

        double costoTotal = 0.0;

        if (pesoKg <= 5) {
            costoTotal = 5.00 + (0.50 * distanciaKm);
        } else if (pesoKg <= 20) {
            costoTotal = 10.00 + (0.80 * distanciaKm);
        } else {
            costoTotal = 20.00 + (1.20 * distanciaKm);
        }

        if (dimensiones.calcularVolumen() > 50000) {
            costoTotal += 15.00;
        }

        if ("PREMIUM".equals(cliente.getTipoMembresia())) {
            costoTotal *= 0.90;
        } else if ("VIP".equals(cliente.getTipoMembresia())) {
            costoTotal *= 0.80;
        }

        if ("TRANSFERENCIA".equals(metodoPago)) {
            costoTotal *= 0.95;
        } else if ("TARJETA".equals(metodoPago)) {
            costoTotal *= 1.03;
        }

        return costoTotal;
    }
}