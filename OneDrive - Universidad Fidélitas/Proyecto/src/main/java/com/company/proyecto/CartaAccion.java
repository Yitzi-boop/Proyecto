/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.proyecto;

/**
 *
 * @author garayac
 */
public class CartaAccion {
    private final Operacion operacion; // + - =
    private final int numero;          // valor
    private final String descripcion;  // texto

    public CartaAccion(Operacion operacion, int numero, String descripcion) {
        this.operacion = operacion;
        this.numero = numero;
        this.descripcion = descripcion;
    }

    public Operacion getOperacion() { return operacion; }
    public int getNumero() { return numero; }
    public String getDescripcion() { return descripcion; }

    @Override
    public String toString() {
        String op = switch (operacion) {
            case SUMA -> "+";
            case RESTA -> "-";
            case IGUAL -> "=";
        };
        return op + " " + numero + " -> " + descripcion;
    }
}