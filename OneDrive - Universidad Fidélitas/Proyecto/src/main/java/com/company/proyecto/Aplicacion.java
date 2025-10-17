/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.proyecto;

/**
 *
 * @author garayac
 */
public class Aplicacion {
    public static void main(String[] args) {
        Juego juego = new Juego(
                "V 1.0.0",
                new String[]{"Equipo", "Integrante 1", "Integrante 2"}
        );
        juego.ejecutarSoloInscripcionYPilas();
    }
}