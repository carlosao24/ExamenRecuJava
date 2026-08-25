package com.ecoenvio.modelo;

public class Cliente {
    private String nombre;
    private String tipoMembresia;

    public Cliente(String nombre, String tipoMembresia) {
        this.nombre = nombre;
        this.tipoMembresia = tipoMembresia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoMembresia() {
        return tipoMembresia;
    }

    public void setTipoMembresia(String tipoMembresia) {
        this.tipoMembresia = tipoMembresia;
    }
}