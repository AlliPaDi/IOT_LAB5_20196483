package com.example.calorimetro.model;

public class ActividadFisica {
    private String nombre;
    private float caloriasQuemadas;

    public ActividadFisica(String nombre, float caloriasQuemadas) {
        this.nombre = nombre;
        this.caloriasQuemadas = caloriasQuemadas;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getCaloriasQuemadas() {
        return caloriasQuemadas;
    }

    public void setCaloriasQuemadas(int caloriasQuemadas) {
        this.caloriasQuemadas = caloriasQuemadas;
    }
}
