/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.proyecto;

/**
 *
 * @author garayac
 */
import java.util.Scanner;
public class UtilEntrada {

    // Valida que la cadena sea un entero (opcionalmente con signo -) y convierte manualmente.
    public static int leerEntero(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            if (esEntero(s)) {
                return parseEnteroSeguro(s);
            } else {
                System.out.println("Ingresa un numero entero valido.");
            }
        }
    }

    // Lee una linea no vacia.
    public static String leerNoVacio(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            if (s.length() > 0) return s;
            System.out.println("No puede estar vacio.");
        }
    }

    // Verifica si la cadena representa un entero (ej: "0", "-12", "345").
    private static boolean esEntero(String s) {
        if (s == null || s.isEmpty()) return false;
        int i = 0;
        if (s.charAt(0) == '-') {
            if (s.length() == 1) return false; // solo "-" no es valido
            i = 1;
        }
        for (; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c < '0' || c > '9') return false;
        }
        return true;
    }

    // Convierte una cadena valida a entero sin usar Integer.parseInt (evita excepciones).
    private static int parseEnteroSeguro(String s) {
        boolean negativo = false;
        int i = 0;
        if (s.charAt(0) == '-') {
            negativo = true;
            i = 1;
        }
        int valor = 0;
        for (; i < s.length(); i++) {
            int dig = s.charAt(i) - '0';   // entre 0 y 9
            valor = valor * 10 + dig;
        }
        return negativo ? -valor : valor;
    }
}