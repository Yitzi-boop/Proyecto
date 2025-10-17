/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company.proyecto;

/**
 *
 * @author garayac
 */
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class Juego {

    private final Queue<Jugador> colaListos = new LinkedList<>();
    private final Stack<CartaAccion> pilaPremios = new Stack<>();
    private final Stack<CartaAccion> pilaCastigos = new Stack<>();
    private final Scanner sc = new Scanner(System.in);
    private final Random rnd = new Random();

    private final String version;
    private final String[] desarrolladores;

    private static final int MAX_JUGADORES = 4;

    public Juego(String version, String[] desarrolladores) {
        this.version = version;
        this.desarrolladores = desarrolladores;
        cargarCartasEjemploSegunEnunciado();
    }

    private void cargarCartasEjemploSegunEnunciado() {
        // Pila de Premios (ejemplos del enunciado)
        pilaPremios.push(new CartaAccion(Operacion.SUMA, 2, "Suma dos posiciones."));
        pilaPremios.push(new CartaAccion(Operacion.SUMA, 8, "Suma ocho posiciones."));
        pilaPremios.push(new CartaAccion(Operacion.SUMA, 0, "Se queda en la posición actual."));

        // Pila de Castigos (ejemplos del enunciado)
        pilaCastigos.push(new CartaAccion(Operacion.RESTA, 3, "Resta tres posiciones."));
        pilaCastigos.push(new CartaAccion(Operacion.IGUAL, 1, "Se debe ir a la posición 1."));
        pilaCastigos.push(new CartaAccion(Operacion.RESTA, 5, "Resta cinco posiciones."));
    }

    /** Menú limitado SOLO a: 1) inscripción y 2) mantenimiento de pilas */
    public void ejecutarSoloInscripcionYPilas() {
        int opcion;
        do {
            System.out.println("====== MVP (Solo Inscripción y Pilas) ======");
            System.out.println("1) Inscribir jugadores (cola preparados-listos, máx. " + MAX_JUGADORES + ")");
            System.out.println("2) Mantenimiento de Pilas (Premios/Castigos)");
            System.out.println("3) Mostrar estado rápido (cola y pilas)");
            System.out.println("0) Salir");
            System.out.println("============================================");
            opcion = UtilEntrada.leerEntero(sc, "Elige una opción: ");

            switch (opcion) {
                case 1 -> inscribirJugadores();
                case 2 -> mantenimientoPilas();
                case 3 -> mostrarEstadoRapido();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida.");
            }
            System.out.println();
        } while (opcion != 0);
    }

    // ========== (1) Inscripción de jugadores ==========
    private void inscribirJugadores() {
        int actuales = colaListos.size();
        int disponibles = MAX_JUGADORES - actuales;
        if (disponibles <= 0) {
            System.out.println("La cola ya tiene " + MAX_JUGADORES + " jugadores.");
            return;
        }
        int n = UtilEntrada.leerEntero(sc, "¿Cuántos jugadores deseas inscribir? (disponibles: " + disponibles + "): ");
        if (n < 1) return;
        if (n > disponibles) n = disponibles;

        for (int i = 1; i <= n; i++) {
            String nombre = UtilEntrada.leerNoVacio(sc, "Nombre del jugador " + i + ": ");
            colaListos.offer(new Jugador(nombre));
        }
        System.out.println("Inscripción completa. En cola: " + colaListos.size());
    }

    // ========== (2) Mantenimiento de Pilas ==========
    private void mantenimientoPilas() {
        System.out.println("---- MANTENIMIENTO DE PILAS ----");
        System.out.println("1) Premios");
        System.out.println("2) Castigos");
        int cual = UtilEntrada.leerEntero(sc, "Elige pila: ");
        Stack<CartaAccion> pila = (cual == 1) ? pilaPremios : (cual == 2 ? pilaCastigos : null);
        if (pila == null) {
            System.out.println("Pila inválida.");
            return;
        }

        System.out.println("1) Agregar elemento");
        System.out.println("2) Eliminar elemento (cima)");
        System.out.println("3) Modificar elemento por índice");
        System.out.println("4) Mostrar elementos de la pila");
        int op = UtilEntrada.leerEntero(sc, "Opción: ");

        switch (op) {
            case 1 -> agregarElemento(pila);
            case 2 -> eliminarElemento(pila);
            case 3 -> modificarElemento(pila);
            case 4 -> mostrarPila(pila);
            default -> System.out.println("Opción inválida.");
        }
    }

    private void agregarElemento(Stack<CartaAccion> pila) {
        System.out.println("Operación: 1) + (SUMA)  2) - (RESTA)  3) = (IGUAL)");
        int o = UtilEntrada.leerEntero(sc, "Elige operación: ");
        Operacion op = switch (o) {
            case 1 -> Operacion.SUMA;
            case 2 -> Operacion.RESTA;
            case 3 -> Operacion.IGUAL;
            default -> null;
        };
        if (op == null) { System.out.println("Operación inválida."); return; }

        int numero = UtilEntrada.leerEntero(sc, "Número: ");
        String desc = UtilEntrada.leerNoVacio(sc, "Descripción: ");

        pila.push(new CartaAccion(op, numero, desc));
        System.out.println("Elemento agregado (en la cima).");
    }

    private void eliminarElemento(Stack<CartaAccion> pila) {
        if (pila.isEmpty()) {
            System.out.println("La pila está vacía.");
            return;
        }
        CartaAccion top = pila.pop();
        System.out.println("Eliminado de la cima: " + top);
    }

    private void modificarElemento(Stack<CartaAccion> pila) {
        if (pila.isEmpty()) {
            System.out.println("La pila está vacía.");
            return;
        }
        mostrarPila(pila);
        int idx = UtilEntrada.leerEntero(sc, "Índice a modificar (0 = base ... " + (pila.size() - 1) + " = cima): ");
        if (idx < 0 || idx >= pila.size()) {
            System.out.println("Índice inválido.");
            return;
        }
        System.out.println("Operación: 1) + (SUMA)  2) - (RESTA)  3) = (IGUAL)");
        int o = UtilEntrada.leerEntero(sc, "Elige operación: ");
        Operacion op = switch (o) {
            case 1 -> Operacion.SUMA;
            case 2 -> Operacion.RESTA;
            case 3 -> Operacion.IGUAL;
            default -> null;
        };
        if (op == null) { System.out.println("Operación inválida."); return; }

        int numero = UtilEntrada.leerEntero(sc, "Número: ");
        String desc = UtilEntrada.leerNoVacio(sc, "Descripción: ");

        pila.set(idx, new CartaAccion(op, numero, desc));
        System.out.println("Elemento modificado.");
    }

    private void mostrarPila(Stack<CartaAccion> pila) {
        if (pila.isEmpty()) { System.out.println("[Vacía]"); return; }
        System.out.println("Elementos (0 = base ... " + (pila.size() - 1) + " = cima):");
        for (int i = 0; i < pila.size(); i++) {
            System.out.println("[" + i + "] " + pila.get(i));
        }
    }

    // Utilidad para que el equipo vea cómo va quedando (no es parte del enunciado)
    private void mostrarEstadoRapido() {
        System.out.println("-- Cola (frente -> final) --");
        if (colaListos.isEmpty()) System.out.println("[Cola vacía]");
        int i = 1;
        for (Jugador j : colaListos) {
            System.out.println(i + ") " + j.getNombre() + " (pos=" + j.getPosicion() + ")");
            i++;
        }
        System.out.println("\n-- Pila Premios --");  mostrarPila(pilaPremios);
        System.out.println("-- Pila Castigos --");  mostrarPila(pilaCastigos);
    }
}