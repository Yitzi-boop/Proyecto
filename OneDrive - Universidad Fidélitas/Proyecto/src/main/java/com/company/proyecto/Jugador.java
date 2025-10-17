/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.proyecto;

/**
 *
 * @author garayac
 */
public class Jugador {
    private final String nombre;
    private int posicion;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.posicion = 0; // todos inician en 0 (aunque no se juega aún)
    }

    public String getNombre() { return nombre; }
    public int getPosicion() { return posicion; }
    public void setPosicion(int posicion) { this.posicion = posicion; }
}